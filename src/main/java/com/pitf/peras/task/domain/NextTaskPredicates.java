package com.pitf.peras.task.domain;

import java.util.List;

public class NextTaskPredicates {
	private List<Long> activeCategories;
	private TaskEstimation taskEstimation;

	public NextTaskPredicates(List<Long> activeCategories,
			TaskEstimation taskEstimation) {
		super();
		this.activeCategories = activeCategories;
		this.taskEstimation = taskEstimation;
	}

	public List<Long> getActiveCategories() {
		return activeCategories;
	}

	public TaskEstimation getTaskEstimation() {
		return taskEstimation;
	}

}
