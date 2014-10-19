package com.pitf.peras.task.web.domain;

import java.util.List;

public class TasksHomeSettingsView {
	private List<TasksHomeCategoryView> tasksHomeCategories;
	private EstimationView estimation;

	public List<TasksHomeCategoryView> getTasksHomeCategories() {
		return tasksHomeCategories;
	}

	public void setTasksHomeCategories(
			List<TasksHomeCategoryView> tasksHomeCategory) {
		this.tasksHomeCategories = tasksHomeCategory;
	}

	public EstimationView getEstimation() {
		return estimation;
	}

	public void setEstimation(EstimationView estimation) {
		this.estimation = estimation;
	}

}
