package com.pitf.peras.task.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.pitf.peras.category.dao.domain.CategoryEntity;

@Entity(name = "t_task")
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task_id")
	@SequenceGenerator(name = "seq_task_id", sequenceName = "seq_task_id")
	@Column(name = "task_id")
	private Long taskId;
	@Column
	private String summary;
	@Column
	private Long estimation;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity categoryEntity;
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
	@Column
	private boolean recurring;
	@Column(name = "recurrence_measure")
	private String recurrenceMeasure;
	@Column(name = "recurrence_value")
	private Integer recurrenceValue;

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

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	public String getRecurrenceMeasure() {
		return recurrenceMeasure;
	}

	public void setRecurrenceMeasure(String recurrenceMeasure) {
		this.recurrenceMeasure = recurrenceMeasure;
	}

	public Integer getRecurrenceValue() {
		return recurrenceValue;
	}

	public void setRecurrenceValue(Integer recurrenceValue) {
		this.recurrenceValue = recurrenceValue;
	}

}
