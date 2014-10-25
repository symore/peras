package com.pitf.peras.task.dao;

import java.sql.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pitf.peras.category.dao.domain.CategoryEntity;
import com.pitf.peras.category.dao.domain.LastTouchedCategoryViewEntity;
import com.pitf.peras.task.dao.domain.TaskViewEntity;
import com.pitf.peras.task.domain.TaskRetrievalParameters;

public class TaskSpecifications {
	public Specification<TaskViewEntity> getNextTask(
			final TaskRetrievalParameters taskRetrievalParameters) {
		return new Specification<TaskViewEntity>() {

			@Override
			public Predicate toPredicate(Root<TaskViewEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<TaskViewEntity, CategoryEntity> join = root
						.join("categoryEntity");
				Predicate result = cb.equal(root.get("userId"),
						taskRetrievalParameters.getUserId());
				if (taskRetrievalParameters.getCategoryIds() != null) {
					result = cb.and(result,
							join.in(taskRetrievalParameters.getCategoryIds()));
				}
				if (taskRetrievalParameters.getAvailableTime() != null) {
					result = cb.and(result, cb.lessThanOrEqualTo(
							root.<Long> get("estimation"),
							taskRetrievalParameters.getAvailableTime()));
				}
				return result;
			}
		};
	}

	public Specification<TaskViewEntity> getUrgentTasks(
			final TaskRetrievalParameters taskRetrievalParameters) {
		return new Specification<TaskViewEntity>() {

			@Override
			public Predicate toPredicate(Root<TaskViewEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate result = cb.equal(root.get("userId"),
						taskRetrievalParameters.getUserId());
				Join<TaskViewEntity, LastTouchedCategoryViewEntity> lastTouchedJoin = root
						.join("lastTouchedCategoryViewEntity");

				if (taskRetrievalParameters.getCategoryIds() != null) {
					result = cb.and(result, lastTouchedJoin
							.in(taskRetrievalParameters.getCategoryIds()));
				}
				result = cb.and(
						result,
						cb.lessThanOrEqualTo(
								lastTouchedJoin.<Date> get("dangerDate"),
								cb.currentTimestamp()));
				return result;
			}
		};
	}
}
