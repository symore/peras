package com.pitf.peras.category.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageCategoriesController {
	@Value("${manageCategoriesController.successView}")
	private String successView;

	@RequestMapping("${manageCategoriesController}")
	public String taskHome() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

}
