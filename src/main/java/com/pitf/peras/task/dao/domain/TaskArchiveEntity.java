package com.pitf.peras.task.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_task_archive")
public class TaskArchiveEntity {
	@Column(name = "task_id")
	@Id
	private Long taskId;
	@Column
	private String summary;
	@Column
	private Long estimation;
	@Column(name = "category_id")
	private Long categoryId;
	@Column(name = "category_name")
	private String category_name;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getNext() {
		return next;
	}

	public void setNext(Long next) {
		this.next = next;
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

}
