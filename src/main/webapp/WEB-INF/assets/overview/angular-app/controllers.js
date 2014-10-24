'use strict';

/* Controllers */

var overviewControllers = angular.module('overviewControllers', []);

overviewControllers.controller('OverviewController', [
		'$scope',
		'ngTableParams',
		'Overview',

		function($scope, ngTableParams, Overview) {
			var dataPromise = Overview.listUrgentCategories();
			dataPromise.then(function(data) {
				$scope.tableParams = new ngTableParams({
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
