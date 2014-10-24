package com.pitf.peras.overview.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OverviewController {
	@Value("${overviewController.successView}")
	private String successView;

	@RequestMapping("${overviewController}")
	public String taskHome() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

}
