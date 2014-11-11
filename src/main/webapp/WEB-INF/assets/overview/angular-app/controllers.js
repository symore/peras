'use strict';

/* Controllers */

var overviewControllers = angular.module('overviewControllers', []);

overviewControllers.controller('OverviewController',
		[
				'$scope',
				'ngTableParams',
				'Overview',

				function($scope, ngTableParams, Overview) {
					var urgentTasksPromise = Overview.listUrgentTasks();
					urgentTasksPromise.then(function(data) {
						$scope.urgentTasks = new ngTableParams({
							page : 1, // show first page
							count : 10
						// count per page
						}, {
							total : data.length, // length of data
							getData : function($defer, params) {
								$defer.resolve(data.slice((params.page() - 1)
										* params.count(), params.page()
										* params.count()));
							}
						});

					});
					var shouldTouchCategoriesPromise = Overview
							.listShouldTouchCategories();
					shouldTouchCategoriesPromise.then(function(data) {
						$scope.shouldTouchCategories = new ngTableParams({
							page : 1, // show first page
							count : 10
						// count per page
						}, {
							total : data.length, // length of data
							getData : function($defer, params) {
								$defer.resolve(data.slice((params.page() - 1)
										* params.count(), params.page()
										* params.count()));
							}
						});

					});
					var untouchedCategoriesPromise = Overview
							.listUntouchedCategories();
					untouchedCategoriesPromise.then(function(data) {
						$scope.untouchedCategories = new ngTableParams({
							page : 1, // show first page
							count : 10
						// count per page
						}, {
							total : data.length, // length of data
							getData : function($defer, params) {
								$defer.resolve(data.slice((params.page() - 1)
										* params.count(), params.page()
										* params.count()));
							}
						});

					});
				} ]);
