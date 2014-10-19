package com.pitf.peras.task.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pitf.peras.task.dao.domain.TaskEntity;

public interface TaskDao extends CrudRepository<TaskEntity, Long> {
	@Modifying
	@Query(value = "INSERT INTO t_task_archive(task_id, estimation, summary, user_id, category_id, next, done, creation_date, done_date, start_date, deadline, category_name) select t.*, c.name FROM t_task t, t_category c WHERE t.category_id = c.category_id AND t.category_id = ? AND t.done", nativeQuery = true)
	void archiveTasks(Long categoryId);

	@Modifying
	@Query(value = "UPDATE t_task AS t1 SET next = NULL WHERE category_id = ? AND EXISTS (SELECT 1 FROM t_task t2 WHERE t1.next = t2.task_id AND t2.next IS NULL AND t2.done)", nativeQuery = true)
	void eraseLastTaskNextReferenceWhenDone(Long categoryId);

	@Modifying
	@Query(value = "DELETE FROM t_task WHERE category_id = ? AND done = true", nativeQuery = true)
	void deleteDoneTasks(Long categoryId);

	TaskEntity findByNext(Long nextId);

}