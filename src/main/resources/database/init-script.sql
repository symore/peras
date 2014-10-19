CREATE SEQUENCE seq_category_id;
CREATE TABLE t_category
(
  category_id bigint NOT NULL,
  name character varying(255),
  user_id bigint,
  last_worked_on timestamp with time zone,
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
WITH RECURSIVE search_tasks(task_id, estimation, summary, user_id, category_id, next, done, creation_date, done_date, start_date, deadline, depth) AS (
        SELECT t.task_id, t.estimation, t.summary, t.user_id, t.category_id, t.next, t.done, t.creation_date, t.done_date, t.start_date, t.deadline, 1
        FROM t_task t
        WHERE t.next is null
      UNION ALL
        SELECT t.task_id, t.estimation, t.summary, t.user_id, t.category_id, t.next, t.done, t.creation_date, t.done_date, t.start_date, t.deadline, st.depth+1
        FROM t_task t, search_tasks st
        WHERE t.next = st.task_id
)
SELECT distinct row_number() over () as rownum, s.* FROM search_tasks s order by depth desc;


CREATE VIEW v_task_archive AS
SELECT t.task_id, t.estimation, t.summary, t.user_id, COALESCE ((SELECT name FROM t_category WHERE category_id = t.category_id), t.category_name), t.next, t.done, t.creation_date, t.done_date, t.start_date, t.deadline
FROM t_task_archive t