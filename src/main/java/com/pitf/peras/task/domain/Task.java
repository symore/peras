package com.pitf.peras.task.domain;

import java.util.Date;

import com.pitf.peras.category.domain.Category;

public class Task {
	private String summary;
	private Long estimation;
	private TimePortion estimationPortion;
	private Category category;
	private Long taskId;
	private Long userId;
	private Long next;
	private boolean done;
	private Date deadline;
	private boolean isDoable = true;
	private Date startDate;
	private boolean recurring;
	private String recurrenceMeasure;
	private Integer recurrenceValue;

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

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public TimePortion getEstimationPortion() {
		return estimationPortion;
	}

	public void setEstimationPortion(TimePortion estimationPortion) {
		this.estimationPortion = estimationPortion;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public boolean isDoable() {
		return isDoable;
	}

	public void setDoable(boolean isDoable) {
		this.isDoable = isDoable;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
