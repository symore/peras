package com.pitf.peras.task.web.transformer;

import org.springframework.stereotype.Component;

import com.pitf.peras.category.domain.Category;
import com.pitf.peras.task.domain.Task;
import com.pitf.peras.task.web.domain.CreateTaskRequest;
import com.pitf.peras.task.web.domain.TaskView;

@Component
public class TaskTransformer {
	public Task transformTask(CreateTaskRequest createTaskRequest) {
		Task result = new Task();
		result.setEstimation(createTaskRequest.getEstimation());
		result.setEstimationPortion(createTaskRequest.getEstimationPortion());
		result.setSummary(createTaskRequest.getSummary());
		Category category = new Category();
		category.setCategoryId(createTaskRequest.getCategoryId());
		result.setCategory(category);
		result.setNext(createTaskRequest.getNextTask());
		result.setDeadline(createTaskRequest.getDeadline());
		return result;
	}

	public TaskView transformTask(Task task) {
		TaskView result = new TaskView();
		result.setCategoryId(task.getCategory() == null ? null : task
				.getCategory().getCategoryId());
		result.setCategoryName(task.getCategory() == null ? null : task
				.getCategory().getName());
		result.setEstimation(task.getEstimation());
		result.setNextTask(task.getNext());
		result.setSummary(task.getSummary());
		result.setTaskId(task.getTaskId());
		result.setEstimationPortion(task.getEstimationPortion());
		result.setDone(task.isDone());
		result.setDeadline(task.getDeadline());
		result.setDoable(task.isDoable());
		result.setStartDate(task.getStartDate());
		return result;
	}
}
