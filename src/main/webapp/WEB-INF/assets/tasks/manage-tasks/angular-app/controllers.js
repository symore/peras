'use strict';

/* Controllers */

var manageTasksControllers = angular.module('manageTasksControllers', []);

manageTasksControllers.controller('CreateTaskCtrl', [ '$scope', 'Task',
		function($scope, Task) {
			$scope.createTask = function(event) {
				$scope.task.categoryId = $scope.category.categoryId;
				// $scope.task.estimationPortion =
				// $scope.task.estimationPortion.value;
				Task.createTask($scope.task);
			}
		} ]);
manageTasksControllers.controller('TaskListController', [ '$scope', 'Task',
		'Category', function($scope, Task, Category) {
			$scope.estimationPortions = [ {
				value : 'MINUTES',
				label : 'minutes'
			}, {
				value : 'HOURS',
				label : 'hours'
			}, {
				value : 'DAYS',
				label : 'days'
			} ];

			Task.listTasks();
			Category.listCategories();
			var oldPrevious = null;
			var oldNext = null;
			$scope.tasklistSortable = {
				update : function(event, ui) {
					var rewiredTask = {};
					if (oldPrevious != null) {
						rewiredTask.oldPrevious = oldPrevious.id;
					}
					if (oldNext != null) {
						rewiredTask.oldNext = oldNext.id;
					}
					var newPrevious = ui.item.prevAll()[0];
					if (newPrevious != null) {
						rewiredTask.newPrevious = newPrevious.id;
					}
					var newNext = ui.item.nextAll()[0];
					if (newNext != null) {
						rewiredTask.newNext = newNext.id;
					}
					rewiredTask.rewiredTask = ui.item.attr('id');
					console.log('update-before rewire');
					Task.rewireTask(rewiredTask);
					console.log('after rewire');
				},
				start : function(event, ui) {
					oldPrevious = ui.item.prevAll()[0];
					oldNext = ui.item.nextAll()[1];
					console.log('started');
				},
				containment : "parent",// Dont let the user drag outside the
				// parent
				cursor : "move",// Change the cursor icon on drag
				tolerance : "pointer"// Read
			// http://api.jqueryui.com/sortable/#option-tolerance
			};
		} ]);

manageTasksControllers.controller('DeleteTaskController', [ '$scope', 'Task',
		function($scope, Task) {
			$scope.deleteTask = function(taskId) {
				Task.deleteTask(taskId);
			}

		} ]);
manageTasksControllers.controller('FinishTaskController', [ '$scope', 'Task',
		function($scope, Task) {
			$scope.finishTask = function(task) {
				Task.finishTask(task);
			}
		} ]);

manageTasksControllers.controller('ArchiveTasksController', [ '$scope',
		'Category', function($scope, Category) {
			$scope.archiveTasks = function(categoryId) {
				Category.archiveTasks(categoryId);
			}
		} ]);

manageTasksControllers.controller('DatePickerController', [ '$scope', 'Task',
		function($scope, Task) {
			$scope.open = function($event) {
				$event.preventDefault();
				$event.stopPropagation();

				$scope.opened = true;
			};
		} ]);