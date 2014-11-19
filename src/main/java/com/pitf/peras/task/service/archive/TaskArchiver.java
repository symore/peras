package com.pitf.peras.task.service.archive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pitf.peras.task.dao.RetrieveTaskDao;
import com.pitf.peras.task.dao.TaskArchiveDao;
import com.pitf.peras.task.dao.TaskDao;
import com.pitf.peras.task.dao.domain.TaskArchiveEntity;
import com.pitf.peras.task.dao.domain.TaskEntity;
import com.pitf.peras.task.dao.domain.TaskViewEntity;

@Component
public class TaskArchiver {
	private TaskArchiveDao taskArchiveDao;
	private TaskDao taskDao;
	private RetrieveTaskDao retrieveTaskDao;

	@Autowired
	public TaskArchiver(TaskArchiveDao taskArchiveDao, TaskDao taskDao,
			RetrieveTaskDao retrieveTaskDao) {
		super();
		this.taskArchiveDao = taskArchiveDao;
		this.taskDao = taskDao;
		this.retrieveTaskDao = retrieveTaskDao;
	}

	public void archiveTask(TaskEntity taskEntity) {
		TaskArchiveEntity taskArchiveEntity = new TaskArchiveEntity();
		updateArchive(taskEntity, taskArchiveEntity);
		taskArchiveDao.save(taskArchiveEntity);
	}

	public void archiveTasks(Long categoryId) {
		taskDao.archiveTasks(categoryId);

		List<Long> archivedRecurringTasks = taskArchiveDao
				.findRecurringArchivedTasks(categoryId);

		List<Long> recurringTasksToArchive = retrieveTaskDao
				.findRecurringTasksToArchive(categoryId);
		if (!recurringTasksToArchive.isEmpty()) {

			List<Long> newRecurringTasksToArchive = new ArrayList<Long>(
					recurringTasksToArchive);
			newRecurringTasksToArchive.removeAll(archivedRecurringTasks);

			List<Long> archivedRecurringTasksToUpdate = new ArrayList<Long>(
					recurringTasksToArchive);
			archivedRecurringTasksToUpdate
					.removeAll(newRecurringTasksToArchive);

			if (!newRecurringTasksToArchive.isEmpty()) {
				taskDao.archiveRecurringTasks(newRecurringTasksToArchive);
			}

			if (!archivedRecurringTasksToUpdate.isEmpty()) {
				Map<Long, TaskArchiveEntity> archivedTasks = createTaskArchiveMap(taskArchiveDao
						.findAll(archivedRecurringTasksToUpdate));
				Iterable<TaskEntity> tasksToArchive = taskDao
						.findAll(archivedRecurringTasksToUpdate);
				List<TaskArchiveEntity> updatedArchives = new ArrayList<TaskArchiveEntity>();
				for (TaskEntity task : tasksToArchive) {
					TaskArchiveEntity taskArchiveEntity = archivedTasks
							.get(task.getTaskId());
					updateArchive(task, taskArchiveEntity);
					updatedArchives.add(taskArchiveEntity);
				}
				taskArchiveDao.save(updatedArchives);
			}
			taskDao.cleanupArchivedRecurringTasks(recurringTasksToArchive);
		}

		// taskDao.eraseLastTaskNextReferenceWhenDone(categoryId);
		updateReferences(categoryId, true);
		updateReferences(categoryId, false);
		taskDao.deleteDoneTasks(categoryId);
	}

	private void updateReferences(Long categoryId, Boolean recurring) {
		List<TaskViewEntity> undoneTasks = retrieveTaskDao.listUnDoneTasks(
				categoryId, recurring);
		Map<Long, Long> tasksTree = new HashMap<>();
		for (int i = 0; i < undoneTasks.size(); ++i) {
			TaskViewEntity current = undoneTasks.get(i);
			TaskViewEntity next = null;
			if (i + 1 < undoneTasks.size()) {
				next = undoneTasks.get(i + 1);
			}
			if (next != null) {
				if (!current.getNext().equals(next.getTaskId())) {
					tasksTree.put(current.getTaskId(), next.getTaskId());
				}
			} else if (current.getNext() != null) {
				tasksTree.put(current.getTaskId(), null);
			}
		}
		Iterable<TaskEntity> tasksToUpdate = taskDao
				.findAll(tasksTree.keySet());
		List<TaskEntity> updatedTasks = new ArrayList<TaskEntity>();
		for (TaskEntity taskEntity : tasksToUpdate) {
			taskEntity.setNext(tasksTree.get(taskEntity.getTaskId()));
			updatedTasks.add(taskEntity);
		}
		if (!updatedTasks.isEmpty()) {
			taskDao.save(updatedTasks);
		}

	}

	private Map<Long, TaskArchiveEntity> createTaskArchiveMap(
			Iterable<TaskArchiveEntity> tasks) {
		Map<Long, TaskArchiveEntity> result = new HashMap<Long, TaskArchiveEntity>();
		for (TaskArchiveEntity task : tasks) {
			result.put(task.getTaskId(), task);
		}
		return result;
	}

	private void updateArchive(TaskEntity taskEntity,
			TaskArchiveEntity taskArchiveEntity) {
		taskArchiveEntity.setCategory_name(taskEntity.getCategoryEntity()
				.getName());
		taskArchiveEntity.setCategoryId(taskEntity.getCategoryEntity()
				.getCategoryId());
		taskArchiveEntity.setCreationDate(taskEntity.getCreationDate());
		taskArchiveEntity.setDone(taskEntity.isDone());
		taskArchiveEntity.setDoneDate(taskEntity.getDoneDate());
		taskArchiveEntity.setEstimation(taskEntity.getEstimation());
		taskArchiveEntity.setNext(taskEntity.getNext());
		taskArchiveEntity.setStartDate(taskEntity.getStartDate());
		taskArchiveEntity.setSummary(taskEntity.getSummary());
		taskArchiveEntity.setTaskId(taskEntity.getTaskId());
		taskArchiveEntity.setUserId(taskEntity.getUserId());
		taskArchiveEntity.setRecurrenceMeasure(taskEntity
				.getRecurrenceMeasure());
		taskArchiveEntity.setRecurrenceValue(taskEntity.getRecurrenceValue());
		taskArchiveEntity.setRecurring(taskEntity.isRecurring());
	}
}
