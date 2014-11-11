package com.pitf.peras.task.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pitf.peras.task.dao.domain.TaskViewEntity;

public interface RetrieveTaskDao extends CrudRepository<TaskViewEntity, Long>,
		JpaSpecificationExecutor<TaskViewEntity> {
	Iterable<TaskViewEntity> findByUserId(Long userId);

	@Query(value = "FROM v_task WHERE (-1 IN (:categoryIds) OR categoryEntity.categoryId IN (:categoryIds)) AND userId = :userId")
	List<TaskViewEntity> listTasks(
			@Param(value = "categoryIds") List<Long> categoryIds,
			@Param(value = "userId") Long userId);

	TaskViewEntity findByUserIdAndStartDateIsNotNull(Long userId);

	@Query(value = "SELECT taskId FROM v_task WHERE categoryEntity.categoryId = :categoryId AND recurring = true AND done = true")
	List<Long> findRecurringTasksToArchive(
			@Param(value = "categoryId") Long categoryId);

}
