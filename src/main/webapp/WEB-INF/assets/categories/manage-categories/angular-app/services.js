'use strict';

/* Services */

var manageCategoriesServices = angular.module('manageCategoriesServices',
		[ 'ngResource' ]);

manageCategoriesServices.factory('Category', [ '$resource', '$http',
		'$rootScope', function($resource, $http, $rootScope) {
			return {
				createCategory : function(formData) {
					var category = $resource('create-category.json', formData);
					category.save().$promise.then(function(newCategory) {
						$rootScope.categoryList.push(newCategory);
					});
				},
				deleteCategory : function(index) {
					var categoryId = $rootScope.categoryList[index].categoryId;
					var category = $resource('delete-category.json', {
						categoryId : categoryId
					});
					category.remove().$promise.then(function() {
						$rootScope.categoryList.splice(index, 1);
					});
				},
				listCategories : function() {
					var categories = $resource('list-categories.json', {}, {
						query : {
							method : 'GET',
							isArray : true
						}

					});
					categories.query().$promise.then(function(categoryList) {
						$rootScope.categoryList = categoryList;
					});
				}
			}
		} ]);
