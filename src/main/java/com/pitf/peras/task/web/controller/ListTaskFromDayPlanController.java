package com.pitf.peras.task.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.task.facade.TaskFacade;
import com.pitf.peras.task.web.domain.TaskView;
import com.pitf.peras.task.web.transformer.TaskTransformer;

@RestController
public class ListTaskFromDayPlanController {
	private TaskFacade taskFacade;
	private TaskTransformer taskTransformer;

	@Autowired
	public ListTaskFromDayPlanController(TaskFacade taskFacade,
			TaskTransformer taskTransformer) {
		super();
		this.taskFacade = taskFacade;
		this.taskTransformer = taskTransformer;
	}

	@RequestMapping("${listTaskFromDayPlanController}")
	public List<TaskView> taskList() {
		return taskTransformer
				.transformTasks(taskFacade.listTasksFromDayPlan());
	}
}
