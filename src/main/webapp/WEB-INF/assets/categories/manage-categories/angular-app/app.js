'use strict';

/* App Module */

var manageCategoriesApp = angular.module('manageCategoriesApp', [ 'ngRoute',
		'manageCategoriesControllers', 'manageCategoriesServices' ]);

manageCategoriesApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/categories', {
		templateUrl : 'manage/angular-template/category-list.html',
		controller : 'CategoryListController'
	}).when('/phones/:phoneId', {
		templateUrl : 'partials/phone-detail.html',
		controller : 'PhoneDetailCtrl'
	}).otherwise({
		redirectTo : '/categories'
	});
} ]);
