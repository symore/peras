package com.pitf.peras.task.web.domain;

import com.pitf.peras.task.domain.TimePortion;

public class EstimationView {
	private Long estimation;
	private TimePortion estimationPortion;

	public EstimationView(Long estimation, TimePortion estimationPortion) {
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
