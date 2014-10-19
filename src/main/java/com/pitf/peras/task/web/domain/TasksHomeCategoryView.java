package com.pitf.peras.task.web.domain;

import java.util.List;

import com.pitf.peras.category.domain.CategoryStatus;

public class TasksHomeCategoryView {
	private Long categoryId;
	private String categoryName;
	private boolean active;
	private List<String> messages;
	private CategoryStatus status;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public CategoryStatus getStatus() {
		return status;
	}

	public void setStatus(CategoryStatus status) {
		this.status = status;
	}

}
