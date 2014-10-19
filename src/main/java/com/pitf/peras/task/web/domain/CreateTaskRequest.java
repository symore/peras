package com.pitf.peras.task.web.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.pitf.peras.task.domain.TimePortion;

public class CreateTaskRequest {
	private String summary;
	private Long estimation;
	private TimePortion estimationPortion;
	private Long categoryId;
	private Long nextTask;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date deadline;

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

	public TimePortion getEstimationPortion() {
		return estimationPortion;
	}

	public void setEstimationPortion(TimePortion estimationPortion) {
		this.estimationPortion = estimationPortion;
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

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

}
