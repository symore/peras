<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script src="<c:url value="/assets/angularjs/angular.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular-route.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular-resource.js" />"></script>
<script src="<c:url value="/assets/angularjs/ng-table.js" />"></script>
<script src="<c:url value="/assets/jquery/jquery.js" />"></script>
<script src="<c:url value="/assets/overview/angular-app/app.js" />"></script>
<script src="<c:url value="/assets/overview/angular-app/controllers.js" />"></script>
<script src="<c:url value="/assets/overview/angular-app/services.js" />"></script>


<div ng-app="overviewApp">
  <div class="view-container">
    <div ng-controller="OverviewController">
      <h1>You must work on these today</h1>

      <table ng-table="urgentTasks" class="table">
        <tr ng-repeat="task in $data">
          <td data-title="'Summary'">{{task.summary}}</td>
          <td data-title="'Category'">{{task.categoryName}}</td>
          <td data-title="'Reason'">{{task.recurring ? 'Recurrence' : 'Deadline'}}</td>
        </tr>
      </table>
      <h1>You should work on these today</h1>


      <table ng-table="shouldTouchCategories" class="table">
        <tr ng-repeat="category in $data">
          <td data-title="'Name'">{{category.name}}</td>
        </tr>
      </table>
      <h1>You've never worked on these</h1>
      <table ng-table="untouchedCategories" class="table">
        <tr ng-repeat="category in $data">
          <td data-title="'Name'">{{category.name}}</td>
        </tr>
      </table>
    </div>
    <div ng-view class="view-frame"></div>
  </div>
</div>


