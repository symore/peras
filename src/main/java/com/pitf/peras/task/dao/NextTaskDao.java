package com.pitf.peras.task.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.pitf.peras.task.dao.domain.TaskViewEntity;
import com.pitf.peras.task.domain.TaskRetrievalParameters;

@org.springframework.stereotype.Repository
public class NextTaskDao implements Repository<TaskViewEntity, Long> {

	public List<TaskViewEntity> findNextTask(
			TaskRetrievalParameters taskRetrievalParameters) {
		return null;
	}
}
