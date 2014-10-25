package com.pitf.peras.task.web.domain;

import java.util.Date;

import com.pitf.peras.task.domain.TimePortion;

public class TaskView {
	private String summary;
	private Long estimation;
	private Long taskId;
	private Long categoryId;
	private Long nextTask;
	private TimePortion estimationPortion;
	private boolean done;
	private Date deadline;
	private boolean isDoable;
	private Date startDate;
	private String categoryName;
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

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getNextTask() {
		return nextTask;
	}

	public void setNextTask(Long nextTask) {
		this.nextTask = nextTask;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
