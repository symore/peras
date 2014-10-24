package com.pitf.peras.category.web.domain;

public class CreateCategoryRequest {
	private String name;
	private Long warningThreshold;
	private Long dangerThreshold;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getWarningThreshold() {
		return warningThreshold;
	}

	public void setWarningThreshold(Long warningThreshold) {
		this.warningThreshold = warningThreshold;
	}

	public Long getDangerThreshold() {
		return dangerThreshold;
	}

	public void setDangerThreshold(Long dangerThreshold) {
		this.dangerThreshold = dangerThreshold;
	}

}
