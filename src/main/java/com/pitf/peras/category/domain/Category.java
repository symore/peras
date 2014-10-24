package com.pitf.peras.category.domain;

import java.util.List;

public class Category {
	private Long categoryId;
	private String name;
	private Long userId;
	private List<String> messages;
	private CategoryStatus categoryStatus;
	private Long warningThreshold;
	private Long dangerThreshold;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public CategoryStatus getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(CategoryStatus categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public Long getDangerThreshold() {
		return dangerThreshold;
	}

	public void setDangerThreshold(Long dangerThreshold) {
		this.dangerThreshold = dangerThreshold;
	}

	public Long getWarningThreshold() {
		return warningThreshold;
	}

	public void setWarningThreshold(Long warningThreshold) {
		this.warningThreshold = warningThreshold;
	}

}
