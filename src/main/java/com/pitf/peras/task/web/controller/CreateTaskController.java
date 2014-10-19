package com.pitf.peras.task.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.task.domain.Task;
import com.pitf.peras.task.facade.TaskFacade;
import com.pitf.peras.task.web.domain.CreateTaskRequest;
import com.pitf.peras.task.web.domain.TaskView;
import com.pitf.peras.task.web.transformer.TaskTransformer;

@RestController
public class CreateTaskController {
	private TaskFacade taskFacade;
	private TaskTransformer taskTransformer;

	@Autowired
	public CreateTaskController(TaskFacade taskFacade,
			TaskTransformer taskTransformer) {
		super();
		this.taskFacade = taskFacade;
		this.taskTransformer = taskTransformer;
	}

	@RequestMapping("${createTaskController}")
	public TaskView createTask(CreateTaskRequest createTaskRequest) {
		Task task = taskFacade.createTask(transformTask(createTaskRequest));
		return taskTransformer.transformTask(task);
	}

	private Task transformTask(CreateTaskRequest createTaskRequest) {
		return taskTransformer.transformTask(createTaskRequest);
	}

}
