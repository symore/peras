package com.pitf.peras.task.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.task.domain.Task;
import com.pitf.peras.task.facade.TaskFacade;
import com.pitf.peras.task.web.domain.TaskView;
import com.pitf.peras.task.web.transformer.TaskTransformer;

@RestController
public class ListTasksController {
	private TaskFacade taskFacade;
	private TaskTransformer taskTransformer;

	@Autowired
	public ListTasksController(TaskFacade taskFacade,
			TaskTransformer taskTransformer) {
		super();
		this.taskFacade = taskFacade;
		this.taskTransformer = taskTransformer;
	}

	@RequestMapping("${listTasksController}")
	public List<TaskView> taskList() {
		return listTasks();
	}

	private List<TaskView> listTasks() {
		List<TaskView> result = new ArrayList<>();
		for (Task task : taskFacade.listTasks()) {
			result.add(transformTaskCategoryView(task));
		}
		return result;
	}

	private TaskView transformTaskCategoryView(Task task) {
		return taskTransformer.transformTask(task);
	}

}
