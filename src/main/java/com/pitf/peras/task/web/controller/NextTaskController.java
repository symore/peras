package com.pitf.peras.task.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.task.domain.Task;
import com.pitf.peras.task.domain.TaskQuery;
import com.pitf.peras.task.facade.TaskFacade;
import com.pitf.peras.task.service.TimePortionTransformer;
import com.pitf.peras.task.web.domain.NextTaskRequest;
import com.pitf.peras.task.web.domain.TaskView;
import com.pitf.peras.task.web.transformer.TaskTransformer;

@RestController
public class NextTaskController {
	private TaskFacade taskFacade;
	private TaskTransformer taskTransformer;
	private TimePortionTransformer timePortionTransformer;

	@Autowired
	public NextTaskController(TaskFacade taskFacade,
			TaskTransformer taskTransformer,
			TimePortionTransformer timePortionTransformer) {
		super();
		this.taskFacade = taskFacade;
		this.taskTransformer = taskTransformer;
		this.timePortionTransformer = timePortionTransformer;
	}

	@RequestMapping("${nextTaskController}")
	public TaskView taskHome(NextTaskRequest nextTaskRequest) {
		Task task = taskFacade.getNextTask(new TaskQuery(nextTaskRequest
				.getCategoryId(), timePortionTransformer.transformToMinutes(
				nextTaskRequest.getTimePortion(),
				nextTaskRequest.getAvailableTime())));
		return taskTransformer.transformTask(task);
	}
}
