package com.pitf.peras.task.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.task.facade.TaskFacade;
import com.pitf.peras.task.web.domain.DeleteTaskRequest;

@RestController
public class DeleteTaskController {
	private TaskFacade taskFacade;

	@Autowired
	public DeleteTaskController(TaskFacade taskFacade) {
		super();
		this.taskFacade = taskFacade;
	}

	@RequestMapping("${deleteTaskController}")
	public String createTask(DeleteTaskRequest deleteTaskRequest) {
		taskFacade.deleteTask(deleteTaskRequest.getTaskId());
		return "true";
	}
}
