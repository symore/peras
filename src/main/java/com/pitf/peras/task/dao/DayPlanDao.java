package com.pitf.peras.task.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pitf.peras.task.dao.domain.DayPlanEntity;

public interface DayPlanDao extends CrudRepository<DayPlanEntity, Long> {
	@Modifying
	@Query(value = "INSERT INTO t_day_plan (task_id, user_id, date_added) SELECT task_id, user_id, now() FROM t_task WHERE task_id IN (?1) AND task_id NOT IN (SELECT task_id FROM t_day_plan)", nativeQuery = true)
	public void addTasksToDayPlan(Collection<Long> taskIds);

	public List<DayPlanEntity> findByUserId(Long userId);
}
