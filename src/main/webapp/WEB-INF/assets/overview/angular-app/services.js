'use strict';

/* Services */

var overviewServices = angular.module('overviewServices', [ 'ngResource' ]);

overviewServices.factory('Overview', [
		'$resource',
		'$http',
		'$rootScope',
		function($resource, $http, $rootScope) {
			return {
				listUrgentTasks : function() {
					// var categories = $resource('list-urgent-categories.json',
					var urgentTasks = $resource('list-urgent-tasks.json', {}, {
						query : {
							method : 'GET',
							isArray : true
						}

					});
					return urgentTasks.query().$promise;
				},
				listShouldTouchCategories : function() {
					// var categories = $resource('list-urgent-categories.json',
					var categories = $resource(
							'list-should-touch-categories.json', {}, {
								query : {
									method : 'GET',
									isArray : true
								}

							});
					return categories.query().$promise;
				},
				listUntouchedCategories : function() {
					// var categories = $resource('list-urgent-categories.json',
					var categories = $resource(
							'list-untouched-categories.json', {}, {
								query : {
									method : 'GET',
									isArray : true
								}

							});
					return categories.query().$promise;
				}
			}
		} ]);
