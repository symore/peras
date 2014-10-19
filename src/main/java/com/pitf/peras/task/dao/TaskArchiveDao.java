package com.pitf.peras.task.dao;

import org.springframework.data.repository.CrudRepository;

import com.pitf.peras.task.dao.domain.TaskArchiveEntity;

public interface TaskArchiveDao extends CrudRepository<TaskArchiveEntity, Long> {

}
