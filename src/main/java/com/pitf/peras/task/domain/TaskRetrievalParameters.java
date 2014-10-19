package com.pitf.peras.task.domain;

import java.util.List;

public class TaskRetrievalParameters {
	private List<Long> categoryIds;
	private Long userId;
	private Long availableTime;

	public TaskRetrievalParameters(List<Long> categoryIds, Long userId,
			Long availableTime) {
		super();
		this.categoryIds = categoryIds;
		this.userId = userId;
		this.availableTime = availableTime;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getAvailableTime() {
		return availableTime;
	}

}
