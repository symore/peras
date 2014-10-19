package com.pitf.peras.task.web.domain;

import java.util.List;

import com.pitf.peras.task.domain.TimePortion;

public class NextTaskRequest {
	private List<Long> categoryId;
	private Long availableTime;
	private TimePortion timePortion;

	public List<Long> getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(List<Long> categoryId) {
		this.categoryId = categoryId;
	}

	public Long getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(Long availableTime) {
		this.availableTime = availableTime;
	}

	public TimePortion getTimePortion() {
		return timePortion;
	}

	public void setTimePortion(TimePortion timePortion) {
		this.timePortion = timePortion;
	}

}
