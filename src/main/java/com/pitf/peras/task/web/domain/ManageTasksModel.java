package com.pitf.peras.task.web.domain;

import java.util.List;

import com.pitf.peras.category.web.domain.CategoryView;

public class ManageTasksModel {
	private List<CategoryView> categories;

	public ManageTasksModel(List<CategoryView> categories) {
		super();
		this.categories = categories;
	}

	public List<CategoryView> getCategories() {
		return categories;
	}

}
