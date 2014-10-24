package com.pitf.peras.task.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.pitf.peras.category.dao.domain.CategoryEntity;
import com.pitf.peras.category.dao.domain.LastTouchedCategoryViewEntity;

@Entity(name = "v_task")
public class TaskViewEntity {
	@Id
	@Column(name = "task_id")
	private Long taskId;
	@Column
	private String summary;
	@Column
	private Long estimation;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity categoryEntity;
	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private LastTouchedCategoryViewEntity lastTouchedCategoryViewEntity;
	@Column(name = "user_id")
	private Long userId;
	@Column
	private Long next;
	@Column
	private boolean done;
	@Column(name = "creation_date")
	private Date creationDate;
	@Column(name = "done_date")
	private Date doneDate;
	@Column(name = "start_date")
	private Date startDate;
	@Column
	private Date deadline;

	public Long getNext() {
		return next;
	}

	public void setNext(Long next) {
		this.next = next;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getEstimation() {
		return estimation;
	}

	public void setEstimation(Long estimation) {
		this.estimation = estimation;
	}

	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(Date doneDate) {
		this.doneDate = doneDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public LastTouchedCategoryViewEntity getLastTouchedCategoryViewEntity() {
		return lastTouchedCategoryViewEntity;
	}

	public void setLastTouchedCategoryViewEntity(
			LastTouchedCategoryViewEntity lastTouchedCategoryViewEntity) {
		this.lastTouchedCategoryViewEntity = lastTouchedCategoryViewEntity;
	}

}
