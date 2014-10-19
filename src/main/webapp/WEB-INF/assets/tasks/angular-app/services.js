'use strict';

/* Services */

var nextTaskServices = angular.module('nextTaskServices', [ 'ngResource' ]);

nextTaskServices
		.factory(
				'Task',
				[
						'$resource',
						'$rootScope',
						function($resource, $rootScope) {
							return {
								query : function($scope) {
									var categoryIds = [];
									for (var i = 0; i < $rootScope.predicates.categoryList.length; ++i) {
										if ($rootScope.predicates.categoryList[i].active == true) {
											categoryIds
													.push($rootScope.predicates.categoryList[i].categoryId);
										}
									}
									var nextTask = $resource(
											'tasks/next-task.json',
											{
												categoryId : categoryIds,
												availableTime : $rootScope.predicates.availableTime,
												timePortion : $rootScope.predicates.timePortion,
											}, {
												query : {
													method : 'GET',
													isArray : false
												}
											});
									return nextTask.query();
								},
								startTask : function(task) {
									var startTask = $resource(
											'tasks/start-task-execution.json',
											{
												taskId : task.taskId,
											});
									startTask.save().$promise.then(function(
											startedTask) {
										task.startDate = startedTask.startDate;
									});
								},
								finishTask : function(task) {
									var finishTask = $resource(
											'tasks/finish-task.json', {
												taskId : task.taskId,
											});
									finishTask.save();
								}
							}

						} ]);

nextTaskServices
		.factory(
				'Category',
				[
						'$resource',
						'$http',
						'$rootScope',
						function($resource, $http, $rootScope) {
							$rootScope.predicates = {
								categoryList : [],
								estimationPortion : ''
							};
							return {

								listCategories : function() {
									var tasksHomeSettings = $resource(
											'tasks-home-settings.json', {}, {
												query : {
													method : 'GET',
													isArray : false
												}

											});
									tasksHomeSettings.query().$promise
											.then(function(homeSettings) {
												$rootScope.predicates.categoryList = homeSettings.tasksHomeCategories;
											});
								}

							}
						} ]);
