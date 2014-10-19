package com.pitf.peras.task.domain;

public class TaskEstimation {
	private Long estimation;
	private TimePortion estimationPortion;

	public TaskEstimation(Long estimation, TimePortion estimationPortion) {
		super();
		this.estimation = estimation;
		this.estimationPortion = estimationPortion;
	}

	public Long getEstimation() {
		return estimation;
	}

	public TimePortion getEstimationPortion() {
		return estimationPortion;
	}

}
