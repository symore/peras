'use strict';

/* Services */

var overviewServices = angular.module('overviewServices', [ 'ngResource' ]);

overviewServices.factory('Overview', [ '$resource', '$http', '$rootScope',
		function($resource, $http, $rootScope) {
			return {
				listUrgentCategories : function() {
					// var categories = $resource('list-urgent-categories.json',
					var categories = $resource('list-urgent-tasks.json', {}, {
						query : {
							method : 'GET',
							isArray : true
						}

					});
					return categories.query().$promise;
				}
			}
		} ]);
