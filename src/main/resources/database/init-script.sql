CREATE SEQUENCE seq_category_id;
CREATE TABLE t_category
(
  category_id bigint NOT NULL,
  name character varying(255),
  user_id bigint,
  warning_threshold bigint,
  danger_threshold bigint,
  CONSTRAINT t_category_pkey PRIMARY KEY (category_id)
);

CREATE SEQUENCE seq_task_id;
CREATE TABLE t_task
(
  task_id bigint NOT NULL,
  estimation bigint,
  summary character varying(255),
  user_id bigint,
  category_id bigint,
  next bigint,
  done boolean DEFAULT false,
  creation_date timestamp with time zone NOT NULL DEFAULT now(),
  done_date timestamp with time zone,
  start_date timestamp with time zone,
  deadline timestamp with time zone,
  recurring boolean DEFAULT false,
  recurrence_measure character varying(6),
  recurrence_value integer,
  CONSTRAINT t_task_pkey PRIMARY KEY (task_id),
  CONSTRAINT fk_task_category FOREIGN KEY (category_id)
      REFERENCES t_category (category_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE t_task_archive
(
  task_id bigint NOT NULL,
  estimation bigint,
  summary character varying(255),
  user_id bigint,
  category_id bigint,
  category_name character varying(255),
  next bigint,
  done boolean DEFAULT false,
  creation_date timestamp with time zone NOT NULL DEFAULT now(),
  done_date timestamp with time zone,
  start_date timestamp with time zone,
  deadline timestamp with time zone,  
  recurring boolean DEFAULT false,
  recurrence_measure character varying(6),
  recurrence_value integer,
  CONSTRAINT t_task_archive_pkey PRIMARY KEY (task_id)
);

CREATE SEQUENCE seq_user_id;
CREATE TABLE t_user
(
  user_id bigint NOT NULL DEFAULT nextval('seq_user_id'),
  username character varying(255) NOT NULL UNIQUE,
  password character varying(255),
  authority character varying(4) DEFAULT 'USER',
  enabled boolean DEFAULT true,
  CONSTRAINT t_user_pkey PRIMARY KEY (user_id)
);



INSERT INTO t_user (username, password) VALUES('abu', 'abu');
INSERT INTO t_user (username, password) VALUES('symore', 'symore');


CREATE VIEW v_task AS
SELECT task_order.*, task_archive.next_occurrence, date_trunc('day', (task_order.deadline - '1 day'::interval) - (task_order.estimation * 2 || ' minutes')::interval) danger_deadline
FROM (
	WITH RECURSIVE search_tasks(task_id, estimation, summary, user_id, category_id, next, done, creation_date, done_date, start_date, deadline, recurring, recurrence_measure, recurrence_value, depth) AS (
	        SELECT t.task_id, t.estimation, t.summary, t.user_id, t.category_id, t.next, t.done, t.creation_date, t.done_date, t.start_date, t.deadline, t.recurring, t.recurrence_measure, t.recurrence_value, 1
	        FROM t_task t
	        WHERE t.next is null
	      UNION ALL
	        SELECT t.task_id, t.estimation, t.summary, t.user_id, t.category_id, t.next, t.done, t.creation_date, t.done_date, t.start_date, t.deadline, t.recurring, t.recurrence_measure, t.recurrence_value, st.depth+1
	        FROM t_task t, search_tasks st
	        WHERE t.next = st.task_id
	)
	SELECT distinct row_number() over () as rownum, s.* FROM search_tasks s order by depth desc
) AS task_order 
    LEFT OUTER JOIN ( 
	    SELECT last_done_tasks.next_occurrence, arch.* FROM ( 
	        SELECT task_id, max(date_trunc('day', coalesce(done_date, now()-'1 day'::interval)) + (recurrence_value || ' ' || recurrence_measure)::interval) AS next_occurrence FROM t_task_archive
	        WHERE recurring = true
	        GROUP BY task_id
	    ) AS last_done_tasks, t_task_archive arch
        WHERE last_done_tasks.task_id = arch.task_id
    ) AS task_archive
    ON task_order.task_id = task_archive.task_id
order by depth desc;

CREATE VIEW v_task_archive AS
SELECT t.task_id, t.estimation, t.summary, t.user_id, COALESCE ((SELECT name FROM t_category WHERE category_id = t.category_id), t.category_name), t.next, t.done, t.creation_date, t.done_date, t.start_date, t.deadline, t.recurring, t.recurrence_measure, t.recurrence_value
FROM t_task_archive t



CREATE VIEW v_last_touched_category AS
SELECT  last_touched.*
    , date_trunc('day', last_touched.last_touched) + (warning_threshold || ' minutes')::interval as warning
    , date_trunc('day', last_touched.last_touched) + (danger_threshold || ' minutes')::interval as danger FROM t_category c, (
    SELECT c.name AS name, c.category_id AS category_id, max(t.done_date) AS last_touched FROM (
        SELECT * FROM (
            SELECT category_id, done_date FROM t_task_archive
            WHERE recurring = false
            UNION 
            SELECT category_id, done_date FROM t_task
            WHERE recurring = false
        ) AS u) AS t, t_category c
    WHERE t.category_id = c.category_id
    GROUP BY c.category_id, c.name
) AS last_touched
WHERE c.category_id = last_touched.category_id;