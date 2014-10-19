package com.pitf.peras.task.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.category.domain.Category;
import com.pitf.peras.category.facade.CategoryFacade;
import com.pitf.peras.task.domain.NextTaskPredicates;
import com.pitf.peras.task.facade.TaskFacade;
import com.pitf.peras.task.web.domain.EstimationView;
import com.pitf.peras.task.web.domain.TasksHomeCategoryView;
import com.pitf.peras.task.web.domain.TasksHomeSettingsView;

@RestController
public class TasksHomeSettingsController {
	private TaskFacade taskFacade;
	private CategoryFacade categoryFacade;

	@Autowired
	public TasksHomeSettingsController(TaskFacade taskFacade,
			CategoryFacade categoryFacade) {
		super();
		this.taskFacade = taskFacade;
		this.categoryFacade = categoryFacade;
	}

	@RequestMapping("${tasksHomeSettingsController}")
	public TasksHomeSettingsView getTasksHomeSettings() {
		NextTaskPredicates nextTaskPredicates = taskFacade
				.getNextTaskPredicates();
		TasksHomeSettingsView result = new TasksHomeSettingsView();
		result.setEstimation(new EstimationView(nextTaskPredicates
				.getTaskEstimation().getEstimation(), nextTaskPredicates
				.getTaskEstimation().getEstimationPortion()));
		result.setTasksHomeCategories(createTasksHomeCategroyViews(nextTaskPredicates));
		return result;
	}

	private List<TasksHomeCategoryView> createTasksHomeCategroyViews(
			NextTaskPredicates nextTaskPredicates) {
		List<Category> categories = categoryFacade.listCategories();
		List<TasksHomeCategoryView> result = new ArrayList<>();
		TasksHomeCategoryView categoryView;
		for (Category category : categories) {
			categoryView = new TasksHomeCategoryView();
			categoryView.setCategoryId(category.getCategoryId());
			categoryView.setCategoryName(category.getName());
			categoryView.setMessages(category.getMessages());
			categoryView.setActive(nextTaskPredicates.getActiveCategories()
					.contains(category.getCategoryId()));
			categoryView.setStatus(category.getCategoryStatus());
			result.add(categoryView);
		}
		return result;
	}
}
