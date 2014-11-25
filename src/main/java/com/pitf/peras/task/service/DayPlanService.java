package com.pitf.peras.task.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pitf.peras.task.dao.DayPlanDao;
import com.pitf.peras.task.dao.domain.DayPlanEntity;

@Component
public class DayPlanService {
	private DayPlanDao dayPlanDao;

	@Autowired
	public DayPlanService(DayPlanDao dayPlanDao) {
		super();
		this.dayPlanDao = dayPlanDao;
	}

	public void addTaskToDayPlan(Long taskId, Long userId) {
		dayPlanDao.save(createDayPlanEntity(taskId, userId));
	}

	public void addTasksToDayPlan(Collection<Long> taskIds) {
		dayPlanDao.addTasksToDayPlan(taskIds);
	}

	private DayPlanEntity createDayPlanEntity(Long taskId, Long userId) {
		DayPlanEntity result = new DayPlanEntity();
		result.setTaskId(taskId);
		result.setUserId(userId);
		result.setAddedAt(new Date());
		return result;
	}

	public List<Long> listTaskIdsFromDayPlan(Long userId) {
		List<DayPlanEntity> dayPlan = dayPlanDao.findByUserId(userId);
		List<Long> result = new ArrayList<Long>();
		for (DayPlanEntity dayPlanEntity : dayPlan) {
			result.add(dayPlanEntity.getTaskId());
		}
		return result;
	}
}
