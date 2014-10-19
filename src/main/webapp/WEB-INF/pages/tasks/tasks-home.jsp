<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script src="<c:url value="/assets/angularjs/angular.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular-route.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular-resource.js" />"></script>
<script src="<c:url value="/assets/jquery/jquery.js" />"></script>
<script src="<c:url value="/assets/tasks/angular-app/app.js" />"></script>
<script src="<c:url value="/assets/tasks/angular-app/controllers.js" />"></script>
<script src="<c:url value="/assets/tasks/angular-app/services.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular-ui-bootstrap-tpls-0.11.0.js" />"></script>

<div ng-app="nextTaskApp" ng-controller="NextTaskController">
  <form ng-submit="createTask()">
    <div class="form-group">
      <span data-toggle="buttons" ng-repeat="category in predicates.categoryList">
        <label ng-class="{'active': category.active, 'btn-success': category.status == 'NICE', 'btn-danger': category.status=='ALERT', 'btn-warning': category.status=='WARNING'}" class="btn"
          tooltip="{{category.messages}}" tooltip-placement="top"> <input type="checkbox" ng-model="category.active" /> {{category.categoryName}}
        </label>
      </span>
    </div>
    <div class="form-group">
      <div class="input-group">
        <input class="form-control" type="text" ng-model="predicates.availableTime" placeholder="Available time" />
        <div class="input-group-btn" dropdown is-open="status.isopen">
          <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
            <span ng-init="predicates.timePortion=timePortions[0].value" ng-model="predicates.timePortion">{{predicates.timePortion}}</span>
            <span class="caret"></span>
          </button>
          <ul class="dropdown-menu dropdown-menu-right" role="menu">
            <li class="dropdown-toggle" on-toggle="toggled(open)" ng-repeat="portion in timePortions"
              ng-click="predicates.timePortion=portion.value">
              <a href="#">{{portion.label}}</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <p class="form-group">
      <button class="btn-primary" ng-click="nextTask()">Gimme a task</button>
    </p>
  </form>

</div>


