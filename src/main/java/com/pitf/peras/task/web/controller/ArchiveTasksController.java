package com.pitf.peras.task.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.task.facade.TaskFacade;
import com.pitf.peras.task.web.domain.ArchiveTasksRequest;

@RestController
public class ArchiveTasksController {
	private TaskFacade taskFacade;

	@Autowired
	public ArchiveTasksController(TaskFacade taskFacade) {
		super();
		this.taskFacade = taskFacade;
	}

	@RequestMapping("${archiveTasksController}")
	public boolean archiveTasks(ArchiveTasksRequest archiveTasksRequest) {
		taskFacade.archiveTasks(archiveTasksRequest.getCategoryId());
		return true;
	}

}
