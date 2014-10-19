<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="<c:url value="/assets/jquery/jquery.js" />"></script>
<script src="<c:url value="/assets/jquery/jquery-ui.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular-route.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular-resource.js" />"></script>
<script src="<c:url value="/assets/angularjs/ui-sortable.js" />"></script>
<script src="<c:url value="/assets/angularjs/angular-ui-bootstrap-tpls-0.11.0.js" />"></script>
<script src="<c:url value="/assets/tasks/manage-tasks/angular-app/app.js" />"></script>
<script src="<c:url value="/assets/tasks/manage-tasks/angular-app/controllers.js" />"></script>
<script src="<c:url value="/assets/tasks/manage-tasks/angular-app/services.js" />"></script>
<link rel="stylesheet" href="<c:url value="/assets/tasks/tasks.css" />" type="text/css" media="all">

<div ng-app="manageTasksApp">
  <div class="view-container">

    <div ng-view class="view-frame"></div>
  </div>
</div>


