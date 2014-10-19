'use strict';

/* Controllers */

var manageCategoriesControllers = angular.module('manageCategoriesControllers',
		[]);

manageCategoriesControllers.controller('CategoryListController', [ '$scope',
		'Category', function($scope, Category) {
			Category.listCategories();

		} ]);

manageCategoriesControllers.controller('CreateCategoryController', [ '$scope',
		'Category', function($scope, Category) {
			$scope.createCategory = function(event) {
				Category.createCategory($scope.category);
			}
		} ]);

manageCategoriesControllers.controller('DeleteCategoryController', [ '$scope',
		'Category', function($scope, Category) {
			$scope.deleteCategory = function(index) {
				Category.deleteCategory(index);
			}
		} ]);
