'use strict';

/* App Module */

var manageTasksApp = angular.module('manageTasksApp', [ 'ngRoute',
		'manageTasksControllers', 'manageTasksServices', 'ui.sortable',
		'ui.bootstrap' ]);

manageTasksApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/tasks', {
		templateUrl : 'manage/angular-template/task-list.html',
		controller : 'TaskListController'
	}).otherwise({
		redirectTo : '/tasks'
	});
} ]);
