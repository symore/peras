package com.pitf.peras.task.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pitf.peras.category.dao.CategoryDao;
import com.pitf.peras.category.dao.domain.CategoryEntity;
import com.pitf.peras.category.domain.Category;
import com.pitf.peras.task.dao.RetrieveTaskDao;
import com.pitf.peras.task.dao.TaskDao;
import com.pitf.peras.task.dao.TaskSpecifications;
import com.pitf.peras.task.dao.domain.TaskEntity;
import com.pitf.peras.task.dao.domain.TaskViewEntity;
import com.pitf.peras.task.domain.NextTaskPredicates;
import com.pitf.peras.task.domain.RewiredTask;
import com.pitf.peras.task.domain.Task;
import com.pitf.peras.task.domain.TaskEstimation;
import com.pitf.peras.task.domain.TaskRetrievalParameters;
import com.pitf.peras.task.domain.TimePortion;
import com.pitf.peras.task.service.archive.TaskArchiver;

@Component
public class TaskService {
	private TaskDao taskDao;
	private CategoryDao categoryDao;
	private RetrieveTaskDao retrieveTaskDao;
	private TimePortionTransformer timePortionTransformer;
	private TaskArchiver taskArchiver;

	@Autowired
	public TaskService(TaskDao taskDao, CategoryDao categoryDao,
			RetrieveTaskDao retrieveTaskDao,
			TimePortionTransformer timePortionTransformer,
			TaskArchiver taskArchiver) {
		super();
		this.taskDao = taskDao;
		this.categoryDao = categoryDao;
		this.retrieveTaskDao = retrieveTaskDao;
		this.timePortionTransformer = timePortionTransformer;
		this.taskArchiver = taskArchiver;
	}

	@Transactional
	public Task createTask(Task task) {
		TaskEntity taskEntity = taskDao.save(createTaskEntity(task));
		if (task.isRecurring()) {
			archiveTask(taskEntity);
		}
		return transformTaskEntity(taskEntity);
	}

	private void transformToConformEstimation(Task task) {
		Long estimation = task.getEstimation();
		if (estimation != null) {
			TimePortion estimationPortion = TimePortion.MINUTES;
			if (estimation / (60 * 24) > 1) {
				estimation = estimation / (60 * 24);
				estimationPortion = TimePortion.DAYS;
			} else if (estimation / 60 > 1) {
				estimation = estimation / 60;
				estimationPortion = TimePortion.HOURS;
			}
			task.setEstimation(estimation);
			task.setEstimationPortion(estimationPortion);
		}
	}

	private void consistentToMinutes(Task task) {
		task.setEstimation(transformToMinutes(task));
		task.setEstimationPortion(TimePortion.MINUTES);
	}

	private Long transformToMinutes(Task task) {
		return timePortionTransformer.transformToMinutes(
				task.getEstimationPortion(), task.getEstimation());
	}

	private Task transformTaskEntity(TaskEntity taskEntity) {
		Task result = new Task();
		result.setEstimation(taskEntity.getEstimation());
		result.setSummary(taskEntity.getSummary());
		result.setTaskId(taskEntity.getTaskId());
		result.setCategory(transformCategory(taskEntity.getCategoryEntity()));
		result.setUserId(taskEntity.getUserId());
		result.setNext(taskEntity.getNext());
		result.setDone(taskEntity.isDone());
		result.setStartDate(taskEntity.getStartDate());
		result.setDeadline(taskEntity.getDeadline());
		result.setRecurrenceMeasure(taskEntity.getRecurrenceMeasure());
		result.setRecurring(taskEntity.isRecurring());
		result.setRecurrenceValue(taskEntity.getRecurrenceValue());
		transformToConformEstimation(result);
		return result;
	}

	private TaskEntity createTaskEntity(Task task) {
		consistentToMinutes(task);
		TaskEntity result = new TaskEntity();
		result.setEstimation(task.getEstimation());
		result.setSummary(task.getSummary());
		result.setUserId(task.getUserId());
		result.setNext(task.getNext());
		CategoryEntity categoryEntity = categoryDao.findOne(task.getCategory()
				.getCategoryId());
		result.setCategoryEntity(categoryEntity);
		result.setCreationDate(new Date());
		result.setDeadline(task.getDeadline());
		result.setRecurring(task.isRecurring());
		result.setRecurrenceMeasure(task.getRecurrenceMeasure());
		result.setRecurrenceValue(task.getRecurrenceValue());
		return result;
	}

	public List<Task> listUsersTasks(Long userId) {
		Iterable<TaskViewEntity> taskEntities = retrieveTaskDao
				.findByUserId(userId);
		return transformTaskEntities(taskEntities);

	}

	private List<Task> transformTaskEntities(
			Iterable<TaskViewEntity> taskEntities) {
		List<Task> result = new ArrayList<Task>();
		for (TaskViewEntity taskEntity : taskEntities) {
			Task task = new Task();
			task.setEstimation(taskEntity.getEstimation());
			task.setSummary(taskEntity.getSummary());
			task.setTaskId(taskEntity.getTaskId());
			task.setCategory(transformCategory(taskEntity.getCategoryEntity()));
			task.setUserId(taskEntity.getUserId());
			task.setDone(taskEntity.isDone());
			task.setDeadline(taskEntity.getDeadline());
			task.setStartDate(taskEntity.getStartDate());
			task.setRecurrenceMeasure(taskEntity.getRecurrenceMeasure());
			task.setRecurrenceValue(taskEntity.getRecurrenceValue());
			task.setRecurring(taskEntity.isRecurring());
			result.add(task);
			transformToConformEstimation(task);
		}
		return result;
	}

	private Category transformCategory(CategoryEntity categoryEntity) {
		Category result = new Category();
		result.setCategoryId(categoryEntity.getCategoryId());
		result.setName(categoryEntity.getName());
		return result;
	}

	@Transactional
	public void deleteTask(Long taskId) {
		TaskEntity taskEntity = taskDao.findOne(taskId);
		TaskEntity previousTask = taskDao.findByNext(taskId);
		if (previousTask != null) {
			previousTask.setNext(taskEntity.getNext());
			taskDao.save(previousTask);
		}
		taskDao.delete(taskId);
	}

	@Transactional
	public Task rewireTask(RewiredTask rewiredTask) {
		TaskEntity rewired = taskDao.findOne(rewiredTask.getRewiredTask());
		rewired.setNext(rewiredTask.getNewNext());
		taskDao.save(rewired);

		if (rewiredTask.getOldPrevious() != null) {
			TaskEntity oldPrevious = taskDao.findOne(rewiredTask
					.getOldPrevious());
			oldPrevious.setNext(rewiredTask.getOldNext());
			taskDao.save(oldPrevious);
		}
		if (rewiredTask.getNewPrevious() != null) {
			TaskEntity newPrevious = taskDao.findOne(rewiredTask
					.getNewPrevious());
			newPrevious.setNext(rewiredTask.getRewiredTask());
			taskDao.save(newPrevious);
		}
		return transformTaskEntity(rewired);
	}

	@Transactional
	public Task toggleFinishTask(Long taskId) {
		TaskEntity taskEntity = taskDao.findOne(taskId);
		boolean isDoneNow = !taskEntity.isDone();
		taskEntity.setDone(isDoneNow);
		if (isDoneNow) {
			taskEntity.setDoneDate(new Date());
		} else {
			taskEntity.setDoneDate(null);
		}
		taskDao.save(taskEntity);
		return transformTaskEntity(taskEntity);
	}

	public List<Task> listTask(TaskRetrievalParameters taskRetrievalParameters) {
		List<TaskViewEntity> result = null;
		TaskViewEntity alreadyStartedTask = retrieveTaskDao
				.findByUserIdAndStartDateIsNotNull(taskRetrievalParameters
						.getUserId());
		if (alreadyStartedTask == null) {
			List<TaskViewEntity> urgentTasks = listUrgentTaskEntities(taskRetrievalParameters);
			if (urgentTasks.isEmpty()) {
				result = retrieveTaskDao.findAll(new TaskSpecifications()
						.getNextTask(taskRetrievalParameters));
			} else {
				result = Arrays.asList(urgentTasks.get(0));
			}
		} else {
			result = Arrays.asList(alreadyStartedTask);
		}
		return transformTaskEntities(result);
	}

	public NextTaskPredicates getNextTaskPredicates(Long userId) {
		Iterable<CategoryEntity> categories = categoryDao.findByUserId(userId);
		Iterator<CategoryEntity> categoriesIterator = categories.iterator();
		List<Long> activeCategories;
		if (categoriesIterator.hasNext()) {
			activeCategories = new ArrayList<>();
			activeCategories.add(categoriesIterator.next().getCategoryId());
		} else {
			activeCategories = Collections.emptyList();
		}
		return new NextTaskPredicates(activeCategories, new TaskEstimation(
				Long.valueOf(5), TimePortion.MINUTES));
	}

	@Transactional
	public Task startTask(Long taskId) {
		TaskEntity taskEntity = taskDao.findOne(taskId);
		taskEntity.setStartDate(new Date());
		return transformTaskEntity(taskDao.save(taskEntity));
	}

	@Transactional
	public void archiveTasks(Long categoryId) {
		taskArchiver.archiveTasks(categoryId);
	}

	@Transactional
	public Task finishTask(Long taskId) {
		TaskEntity taskEntity = doFinishTask(taskId);
		TaskEntity result = taskDao.save(taskEntity);

		archiveTask(taskEntity);

		TaskEntity previous = taskDao.findByNext(taskId);
		if (previous != null) {
			previous.setNext(taskEntity.getNext());
			taskDao.save(previous);
		}
		if (!taskEntity.isRecurring()) {
			taskDao.delete(taskId);
		} else {
			reopenRecurring(taskEntity);
		}
		return transformTaskEntity(result);
	}

	private void reopenRecurring(TaskEntity taskEntity) {
		taskEntity.setDone(false);
		taskEntity.setStartDate(null);
		taskEntity.setDoneDate(null);
		taskDao.save(taskEntity);
	}

	private TaskEntity doFinishTask(Long taskId) {
		TaskEntity taskEntity = taskDao.findOne(taskId);
		taskEntity.setDone(true);
		taskEntity.setDoneDate(new Date());
		return taskEntity;
	}

	private void archiveTask(TaskEntity taskEntity) {
		taskArchiver.archiveTask(taskEntity);
	}

	public List<Task> listUrgentTasks(
			TaskRetrievalParameters taskRetrievalParameters) {
		List<TaskViewEntity> result = listUrgentTaskEntities(taskRetrievalParameters);
		return transformTaskEntities(result);
	}

	private List<TaskViewEntity> listUrgentTaskEntities(
			TaskRetrievalParameters taskRetrievalParameters) {
		List<TaskViewEntity> result = new ArrayList<TaskViewEntity>();
		result.addAll(retrieveTaskDao.findAll(new TaskSpecifications()
				.getRecurringTasks(taskRetrievalParameters)));
		result.addAll(retrieveTaskDao.findAll(new TaskSpecifications()
				.getDeadLinedTasks(taskRetrievalParameters)));
		return result;
	}

	public List<Task> listTasksById(List<Long> taskIds) {
		return transformTaskEntities(retrieveTaskDao.findAll(taskIds));

	}
}
