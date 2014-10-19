package com.pitf.peras.category.web.domain;

public class CategoryView {
	private String name;
	private Long categoryId;

	public CategoryView(String name, Long categoryId) {
		super();
		this.name = name;
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

}
