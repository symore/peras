<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script src="<c:url value="/assets/angularjs/angular.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular-route.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular-resource.js" />"></script>
<script src="<c:url value="/assets/jquery/jquery.js" />"></script>
<script src="<c:url value="/assets/categories/manage-categories/angular-app/app.js" />"></script>
<script src="<c:url value="/assets/categories/manage-categories/angular-app/controllers.js" />"></script>
<script src="<c:url value="/assets/categories/manage-categories/angular-app/services.js" />"></script>

<div ng-app="manageCategoriesApp">
  <div class="view-container">
    <div ng-controller="CreateCategoryController">
      <form ng-submit="createCategory()">
        <input type="text" ng-model="category.name" placeholder="Category name" />
        <input type="text" ng-model="category.warningThreshold" placeholder="Warning Threshold" />
        <input type="text" ng-model="category.dangerThreshold" placeholder="Danger Threshold" />
        <input type="submit" value="Add" />
      </form>
    </div>
    <div ng-view class="view-frame"></div>
  </div>
</div>


