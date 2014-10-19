package com.pitf.peras.category.domain;

public class CategoryRetrievalParameters {
	private Long userId;

	public CategoryRetrievalParameters(Long userId) {
		super();
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

}
