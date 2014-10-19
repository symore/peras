package com.pitf.peras.task.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.task.domain.RewiredTask;
import com.pitf.peras.task.domain.Task;
import com.pitf.peras.task.facade.TaskFacade;
import com.pitf.peras.task.web.domain.RewireTaskRequest;
import com.pitf.peras.task.web.domain.TaskView;
import com.pitf.peras.task.web.transformer.TaskTransformer;

@RestController
public class RewireTaskController {
	private TaskFacade taskFacade;
	private TaskTransformer taskTransformer;

	@Autowired
	public RewireTaskController(TaskFacade taskFacade,
			TaskTransformer taskTransformer) {
		super();
		this.taskFacade = taskFacade;
		this.taskTransformer = taskTransformer;
	}

	@RequestMapping(value = "${rewireTaskController}", method = RequestMethod.POST)
	public TaskView rewireTask(RewireTaskRequest rewireTaskRequest) {
		Task task = taskFacade.rewireTask(transformRequest(rewireTaskRequest));
		return taskTransformer.transformTask(task);
	}

	private RewiredTask transformRequest(RewireTaskRequest rewireTaskRequest) {
		RewiredTask result = new RewiredTask();
		result.setNewNext(rewireTaskRequest.getNewNext());
		result.setNewPrevious(rewireTaskRequest.getNewPrevious());
		result.setOldNext(rewireTaskRequest.getOldNext());
		result.setOldPrevious(rewireTaskRequest.getOldPrevious());
		result.setRewiredTask(rewireTaskRequest.getRewiredTask());
		return result;
	}

}
