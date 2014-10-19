'use strict';

/* Controllers */

var nextTaskControllers = angular.module('nextTaskControllers', []);

nextTaskControllers.controller('NextTaskController', [ '$scope', 'Task',
		'Category', '$modal', function($scope, Task, Category, $modal) {
			$scope.nextTask = function(event) {
				var modalInstance = $modal.open({
					templateUrl : 'tasks/next/angular-template/next-task.html',
					controller : 'NextTaskModalController',
					backdrop : 'static',
					resolve : {
						'Task' : function() {
							return Task
						}
					}
				});

			}
			$scope.timePortions = [ {
				value : 'MINUTES',
				label : 'minutes'
			}, {
				value : 'HOURS',
				label : 'hours'
			}, {
				value : 'DAYS',
				label : 'days'
			} ];
			Category.listCategories();
		} ]);

nextTaskControllers.controller('NextTaskModalController', function($scope,
		$modalInstance, Task) {
	var res = Task.query();
	res.$promise.then(function(nextTask) {
		$scope.task = nextTask;
		if (nextTask.doable) {
			console.log('hello1');
			$scope.hideStart = nextTask.startDate != null;
			$scope.hideDone = !$scope.hideStart;
			$scope.hideOk = true;
		} else {
			console.log('hello2');
			$scope.hideOk = false;
			$scope.hideStart = true;
			$scope.hideDone = true;
		}
	});

	$scope.start = function() {
		Task.startTask($scope.task);
		$scope.hideStart = true;
		$scope.hideDone = false;
	};

	$scope.done = function() {
		Task.finishTask($scope.task);
		$modalInstance.dismiss();
	};

	$scope.ok = function() {
		$modalInstance.dismiss();
	};
});