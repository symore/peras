package com.pitf.peras.task.domain;

import java.util.List;

public class TaskQuery {
	private List<Long> categoryIds;
	private Long availableTime;

	public TaskQuery(List<Long> categoryIds, Long availableTime) {
		super();
		this.categoryIds = categoryIds;
		this.availableTime = availableTime;
	}

	public Long getAvailableTime() {
		return availableTime;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

}
