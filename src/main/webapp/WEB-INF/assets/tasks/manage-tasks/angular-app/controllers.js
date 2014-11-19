'use strict';

/* Controllers */

var manageTasksControllers = angular.module('manageTasksControllers', []);

manageTasksControllers.controller('CreateTaskCtrl', [ '$scope', 'Task',
		function($scope, Task) {
			$scope.createTask = function(event) {
				$scope.task.categoryId = $scope.category.categoryId;
				$scope.task.recurring = $scope.recur;
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
			$scope.recurrenceMeasures = [ {
				value : 'days',
				label : 'Days'
			}, {
				value : 'weeks',
				label : 'Weeks'
			}, {
				value : 'months',
				label : 'Months'
			}, {
				value : 'years',
				label : 'Years'
			} ];
			$scope.recur = false;

			Task.listTasks();
			Category.listCategories();
			$scope.tasklistSortable = {
				oldPrevious : null,
				oldNext : null,
				update : function(event, ui) {
					var rewiredTask = {};
					if (this.oldPrevious != null) {
						rewiredTask.oldPrevious = this.oldPrevious.id;
					}
					if (this.oldNext != null) {
						rewiredTask.oldNext = this.oldNext.id;
					}
					var newPrevious = ui.item.prevAll()[0];
					if (newPrevious != null) {
						console.log('newprev:');
						console.log(newPrevious);
						rewiredTask.newPrevious = newPrevious.id;
					}
					var newNext = ui.item.nextAll()[0];
					if (newNext != null) {
						console.log('newnext:');
						console.log(newNext);
						rewiredTask.newNext = newNext.id;
					}
					rewiredTask.rewiredTask = ui.item.attr('id');
					Task.rewireTask(rewiredTask);
					this.oldPrevious = null;
					this.oldNext = null;
				},
				start : function(event, ui) {
					this.oldPrevious = ui.item.prevAll()[0];
					console.log('oldprev:');
					console.log(this.oldPrevious);
					this.oldNext = ui.item.nextAll()[1];
					console.log('oldnext:');
					console.log(this.oldNext);
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