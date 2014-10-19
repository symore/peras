package com.pitf.peras.task.service;

import org.springframework.stereotype.Component;

import com.pitf.peras.task.domain.TimePortion;

@Component
public class TimePortionTransformer {

	public Long transformToMinutes(TimePortion timePortion, Long timeUnits) {
		Long result = null;
		if (timeUnits != null) {
			switch (timePortion) {
			case MINUTES:
				result = timeUnits.longValue();
				break;
			case HOURS:
				result = timeUnits.longValue() * 60;
				break;
			case DAYS:
				result = timeUnits.longValue() * 60 * 24;
				break;
			}
		}
		return result;
	}
}
