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
import com.pitf.peras.task.dao.TaskArchiveDao;
import com.pitf.peras.task.dao.TaskDao;
import com.pitf.peras.task.dao.TaskSpecifications;
import com.pitf.peras.task.dao.domain.TaskArchiveEntity;
import com.pitf.peras.task.dao.domain.TaskEntity;
import com.pitf.peras.task.dao.domain.TaskViewEntity;
import com.pitf.peras.task.domain.NextTaskPredicates;
import com.pitf.peras.task.domain.RewiredTask;
import com.pitf.peras.task.domain.Task;
import com.pitf.peras.task.domain.TaskEstimation;
import com.pitf.peras.task.domain.TaskRetrievalParameters;
import com.pitf.peras.task.domain.TimePortion;

@Component
public class TaskService {
	private TaskDao taskDao;
	private CategoryDao categoryDao;
	private RetrieveTaskDao retrieveTaskDao;
	private TimePortionTransformer timePortionTransformer;
	private TaskArchiveDao taskArchiveDao;

	@Autowired
	public TaskService(TaskDao taskDao, CategoryDao categoryDao,
			RetrieveTaskDao retrieveTaskDao,
			TimePortionTransformer timePortionTransformer,
			TaskArchiveDao taskArchiveDao) {
		super();
		this.taskDao = taskDao;
		this.categoryDao = categoryDao;
		this.retrieveTaskDao = retrieveTaskDao;
		this.timePortionTransformer = timePortionTransformer;
		this.taskArchiveDao = taskArchiveDao;
	}

	@Transactional
	public Task createTask(Task task) {
		TaskEntity taskEntity = taskDao.save(createTaskEntity(task));
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
		if (taskEntity.getNext() == null) {
			TaskEntity previousTask = taskDao.findByNext(taskId);
			if (previousTask != null) {
				previousTask.setNext(null);
				taskDao.save(previousTask);
			}

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
			result = retrieveTaskDao.findAll(new TaskSpecifications()
					.getNextTask(taskRetrievalParameters));
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
		taskDao.archiveTasks(categoryId);
		taskDao.eraseLastTaskNextReferenceWhenDone(categoryId);
		taskDao.deleteDoneTasks(categoryId);
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
		taskDao.delete(taskId);
		return transformTaskEntity(result);
	}

	private TaskEntity doFinishTask(Long taskId) {
		TaskEntity taskEntity = taskDao.findOne(taskId);
		taskEntity.setDone(true);
		taskEntity.setDoneDate(new Date());
		return taskEntity;
	}

	private void archiveTask(TaskEntity taskEntity) {
		TaskArchiveEntity taskArchiveEntity = new TaskArchiveEntity();
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
		taskArchiveDao.save(taskArchiveEntity);
	}

	public List<Task> listUrgentTasks(
			TaskRetrievalParameters taskRetrievalParameters) {
		List<TaskViewEntity> result = null;
		result = retrieveTaskDao.findAll(new TaskSpecifications()
				.getUrgentTasks(taskRetrievalParameters));
		return transformTaskEntities(result);
	}
}
