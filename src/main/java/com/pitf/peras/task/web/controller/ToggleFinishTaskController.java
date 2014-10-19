package com.pitf.peras.task.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.task.domain.Task;
import com.pitf.peras.task.facade.TaskFacade;
import com.pitf.peras.task.web.domain.FinishTaskRequest;
import com.pitf.peras.task.web.domain.TaskView;
import com.pitf.peras.task.web.transformer.TaskTransformer;

@RestController
public class ToggleFinishTaskController {
	private TaskFacade taskFacade;
	private TaskTransformer taskTransformer;

	@Autowired
	public ToggleFinishTaskController(TaskFacade taskFacade,
			TaskTransformer taskTransformer) {
		super();
		this.taskFacade = taskFacade;
		this.taskTransformer = taskTransformer;
	}

	@RequestMapping("${toggleFinishTaskController}")
	public TaskView toggleFinishTask(FinishTaskRequest finishTaskRequest) {
		Task task = taskFacade.toggleFinishTask(finishTaskRequest.getTaskId());
		return taskTransformer.transformTask(task);
	}

}
