package com.pitf.peras.task.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pitf.peras.category.service.CategoryService;
import com.pitf.peras.security.domain.User;
import com.pitf.peras.security.service.AuthenticationService;
import com.pitf.peras.task.domain.NextTaskPredicates;
import com.pitf.peras.task.domain.RewiredTask;
import com.pitf.peras.task.domain.Task;
import com.pitf.peras.task.domain.TaskQuery;
import com.pitf.peras.task.domain.TaskRetrievalParameters;
import com.pitf.peras.task.service.TaskService;

@Component
public class TaskFacade {
	private TaskService taskService;
	private AuthenticationService authenticationService;
	private CategoryService categoryService;

	@Autowired
	public TaskFacade(TaskService taskService,
			AuthenticationService authenticationService,
			CategoryService categoryService) {
		super();
		this.taskService = taskService;
		this.authenticationService = authenticationService;
		this.categoryService = categoryService;
	}

	public Task createTask(Task task) {
		User currentUser = getCurrentUser();
		task.setUserId(currentUser.getUserId());
		return taskService.createTask(task);
	}

	private User getCurrentUser() {
		return authenticationService.getCurrentUser();
	}

	public List<Task> listTasks() {
		return taskService.listUsersTasks(getCurrentUser().getUserId());
	}

	public Task getNextTask(TaskQuery taskQuery) {
		TaskRetrievalParameters taskRetrievalParameters = new TaskRetrievalParameters(
				taskQuery.getCategoryIds(), getCurrentUser().getUserId(),
				taskQuery.getAvailableTime());
		List<Task> taskList = listTasks(taskRetrievalParameters);
		return taskList.size() > 0 ? taskList.get(0) : createDefaultTask();
	}

	private Task createDefaultTask() {
		Task result = new Task();
		result.setSummary("You have nothing to do:)");
		result.setDoable(false);
		return result;
	}

	private List<Task> listTasks(TaskRetrievalParameters taskRetrievalParameters) {
		return taskService.listTask(taskRetrievalParameters);
	}

	public void deleteTask(Long taskId) {
		taskService.deleteTask(taskId);
	}

	public Task rewireTask(RewiredTask rewiredTask) {
		return taskService.rewireTask(rewiredTask);
	}

	public Task toggleFinishTask(Long taskId) {
		return taskService.toggleFinishTask(taskId);
	}

	public void archiveTasks(Long categoryId) {
		taskService.archiveTasks(categoryId);
	}

	public NextTaskPredicates getNextTaskPredicates() {
		return taskService.getNextTaskPredicates(getCurrentUser().getUserId());
	}

	public Task startTask(Long taskId) {
		return taskService.startTask(taskId);
	}

	public Task finishTask(Long taskId) {
		return taskService.finishTask(taskId);
	}
}
