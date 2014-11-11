package com.pitf.peras.category.dao;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.Specification;

import com.pitf.peras.category.dao.domain.CategoryEntity;
import com.pitf.peras.category.dao.domain.LastTouchedCategoryViewEntity;
import com.pitf.peras.category.domain.CategoryRetrievalParameters;
import com.pitf.peras.task.dao.domain.TaskEntity;

public class CategorySpecifications {
	public Specification<CategoryEntity> findAlertCategories(
			final CategoryRetrievalParameters categoryRetrievalParameters) {
		return new Specification<CategoryEntity>() {

			@Override
			public Predicate toPredicate(Root<CategoryEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<TaskEntity, CategoryEntity> join = root.join("taskEntity");
				Predicate result = cb.equal(root.get("userId"),
						categoryRetrievalParameters.getUserId());
				DateTime now = new DateTime();
				DateTime weekLater = now.plusWeeks(1);
				Path<Date> deadline = join.get("deadline");
				result = cb.and(result,
						cb.lessThan(deadline, new Date(weekLater.getMillis())));
				return result;
			}
		};
	}

	public Specification<CategoryEntity> findWarningCategories(
			final CategoryRetrievalParameters categoryRetrievalParameters) {
		return new Specification<CategoryEntity>() {

			@Override
			public Predicate toPredicate(Root<CategoryEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<TaskEntity, CategoryEntity> join = root.join("taskEntity");
				Predicate result = cb.equal(root.get("userId"),
						categoryRetrievalParameters.getUserId());
				DateTime now = new DateTime();
				Path<Date> deadline = join.get("deadline");
				result = cb.and(result, cb.lessThan(deadline, new Date(now
						.plusWeeks(2).getMillis())));
				result = cb.and(result, cb.greaterThanOrEqualTo(deadline,
						new Date(now.plusWeeks(1).getMillis())));
				return result;
			}
		};
	}

	public Specification<CategoryEntity> findShouldTouchCategories(
			final CategoryRetrievalParameters categoryRetrievalParameters) {
		return new Specification<CategoryEntity>() {

			@Override
			public Predicate toPredicate(Root<CategoryEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Subquery<Long> subquery = query.subquery(Long.class);
				Root<LastTouchedCategoryViewEntity> lastTouched = subquery
						.from(LastTouchedCategoryViewEntity.class);
				subquery.select(lastTouched.<Long> get("categoryId")).where(
						cb.and(cb.isNotNull(lastTouched
								.<Date> get("dangerDate")), cb
								.lessThanOrEqualTo(
										lastTouched.<Date> get("dangerDate"),
										cb.currentTimestamp())));
				Predicate result = cb.equal(root.get("userId"),
						categoryRetrievalParameters.getUserId());
				result = cb.and(result,
						cb.in(root.get("categoryId")).value(subquery));
				return result;
			}
		};
	}

	public Specification<CategoryEntity> findUntouchedCategories(
			final CategoryRetrievalParameters categoryRetrievalParameters) {
		return new Specification<CategoryEntity>() {

			@Override
			public Predicate toPredicate(Root<CategoryEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Subquery<Long> subquery = query.subquery(Long.class);
				Root<LastTouchedCategoryViewEntity> lastTouched = subquery
						.from(LastTouchedCategoryViewEntity.class);
				subquery.select(lastTouched.<Long> get("categoryId")).where(
						cb.isNull(lastTouched.<Date> get("dangerDate")));
				Predicate result = cb.equal(root.get("userId"),
						categoryRetrievalParameters.getUserId());
				result = cb.and(result,
						cb.in(root.get("categoryId")).value(subquery));
				return result;
			}
		};
	}
}
