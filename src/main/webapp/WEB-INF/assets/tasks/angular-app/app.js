'use strict';

/* App Module */

var nextTaskApp = angular.module('nextTaskApp', [ 'ngRoute',
		'nextTaskControllers', 'nextTaskServices', 'ui.bootstrap' ]);

nextTaskApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/tasks/next', {
		controller : 'NextTaskController'
	}).when('/phones/:phoneId', {
		templateUrl : 'partials/phone-detail.html',
		controller : 'PhoneDetailCtrl'
	}).otherwise({
		redirectTo : '/tasks/next'
	});
} ]);
