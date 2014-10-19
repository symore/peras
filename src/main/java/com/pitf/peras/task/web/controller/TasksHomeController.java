package com.pitf.peras.task.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TasksHomeController {
	@Value("${tasksHomeController.successView}")
	private String successView;

	@RequestMapping("${tasksHomeController}")
	public String taskHome() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

}
