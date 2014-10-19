package com.pitf.peras.task.web.domain;

import com.pitf.peras.category.web.domain.CategoryView;

public class TaskCategoryView {
	private TaskView task;
	private CategoryView category;

	public TaskCategoryView(TaskView task, CategoryView category) {
		super();
		this.task = task;
		this.category = category;
	}

	public TaskView getTask() {
		return task;
	}

	public CategoryView getCategory() {
		return category;
	}

}
