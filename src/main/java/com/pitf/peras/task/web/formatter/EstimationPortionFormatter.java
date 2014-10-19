package com.pitf.peras.task.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.pitf.peras.task.domain.TimePortion;

public class EstimationPortionFormatter implements Formatter<TimePortion> {
	private static final String MINUTES_TEXT = "minutes";
	private static final String HOURS_TEXT = "hours";
	private static final String DAYS_TEXT = "days";

	@Override
	public String print(TimePortion estimationPortion, Locale locale) {
		String result = "";
		switch (estimationPortion) {
		case MINUTES:
			result = MINUTES_TEXT;
			break;
		case HOURS:
			result = HOURS_TEXT;
			break;
		case DAYS:
			result = DAYS_TEXT;
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	public TimePortion parse(String text, Locale locale)
			throws ParseException {
		return TimePortion.valueOf(text);
	}

}
