package com.pitf.peras.task.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pitf.peras.task.dao.domain.TaskArchiveEntity;

public interface TaskArchiveDao extends CrudRepository<TaskArchiveEntity, Long> {
	@Query(value = "SELECT taskId FROM t_task_archive WHERE categoryId = :categoryId AND recurring = true")
	List<Long> findRecurringArchivedTasks(
			@Param(value = "categoryId") Long categoryId);
}
