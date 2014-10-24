package com.pitf.peras.overview.web.controller;

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
public class ListUrgentTasksController {
	private TaskFacade taskFacade;
	private TaskTransformer taskTransformer;

	@Autowired
	public ListUrgentTasksController(TaskFacade taskFacade,
			TaskTransformer taskTransformer) {
		super();
		this.taskFacade = taskFacade;
		this.taskTransformer = taskTransformer;
	}

	@RequestMapping("${listUrgentTasksController}")
	public List<TaskView> urgentTaskList() {
		return listUrgentTasks();
	}

	private List<TaskView> listUrgentTasks() {
		List<TaskView> result = new ArrayList<>();
		for (Task task : taskFacade.listUrgentTasks()) {
			result.add(transformTaskCategoryView(task));
		}
		return result;
	}

	private TaskView transformTaskCategoryView(Task task) {
		return taskTransformer.transformTask(task);
	}

}
