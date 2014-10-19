'use strict';

/* Services */

var manageTasksServices = angular.module('manageTasksServices',
		[ 'ngResource' ]);

manageTasksServices
		.factory(
				'Task',
				[
						'$resource',
						'$http',
						'$rootScope',
						'$filter',
						function($resource, $http, $rootScope, $filter) {
							return {
								createTask : function(formData) {
									var topTask = null;
									if ($rootScope.taskList != null) {
										for (var i = 0; i < $rootScope.taskList.length
												&& topTask == null; ++i) {
											if ($rootScope.taskList[i].categoryId == formData.categoryId) {
												topTask = $rootScope.taskList[i];
											}
										}
									}
									if (topTask != null) {
										formData.nextTask = topTask.taskId;
									}
									formData.deadline = $filter('date')(
											formData.deadline, "dd-MM-yyyy");
									var task = $resource('create-task.json',
											formData);
									task.save().$promise
											.then(function(newTask) {
												if ($rootScope.taskList != null) {
													$rootScope.taskList
															.unshift(newTask);
												}
											});
								},
								deleteTask : function(taskId) {
									var index = null;
									for (var i = 0; i < $rootScope.taskList.length
											&& index == null; ++i) {
										if ($rootScope.taskList[i].taskId == taskId) {
											index = i;
										}
									}
									var task = $resource('delete-task.json', {
										taskId : taskId
									});
									task.remove().$promise.then(function() {
										$rootScope.taskList.splice(index, 1);
									});
								},
								listTasks : function() {
									var tasks = $resource('list-tasks.json',
											{}, {
												query : {
													method : 'GET',
													isArray : true
												}

											});
									tasks.query().$promise.then(function(
											taskList) {
										$rootScope.taskList = taskList;
									});
								},
								rewireTask : function(rewiredTask) {
									var task = $resource('rewire-task.json',
											rewiredTask);
									task.save().$promise.then(function(
											updatedTask) {
									});
								},
								finishTask : function(finishedTask) {
									var task = $resource(
											'toggle-finish-task.json',
											finishedTask);
									task.save().$promise.then(function(
											updatedTask) {
									});
								}
							}
						} ]);

manageTasksServices
		.factory(
				'Category',
				[
						'$resource',
						'$http',
						'$rootScope',
						function($resource, $http, $rootScope) {
							return {
								listCategories : function() {
									var categories = $resource(
											'../categories/list-categories.json',
											{}, {
												query : {
													method : 'GET',
													isArray : true
												}

											});
									categories.query().$promise.then(function(
											categoryList) {
										$rootScope.categoryList = categoryList;
									});
								},
								archiveTasks : function(categoryId) {
									var tasks = $resource('archive-tasks.json',
											{
												categoryId : categoryId
											});
									tasks.save().$promise
											.then(function() {
												if ($rootScope.taskList != null) {
													$rootScope.taskList = $rootScope.taskList
															.filter(function(
																	item) {
																var result = !(item.categoryId == categoryId && item.done);
																if (!result) {
																	console
																			.log(item);
																}
																return result;
															});
												}
											});
								}
							}
						} ]);
