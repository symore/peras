<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="servletPath" value="${requestScope['javax.servlet.forward.servlet_path']}" />
<ul class="nav nav-tabs">
  <li class="${fn:endsWith(servletPath, '/') ? 'active' : ''}"><a href="<c:url value="/" />">Home</a></li>
  <li class="${fn:endsWith(servletPath, '/tasks/manage-tasks.html') ? 'active' : ''}"><a href="<c:url value="/tasks/manage-tasks.html" />">Manage Tasks</a></li>
  <li class="${fn:endsWith(servletPath, '/categories/manage-categories.html') ? 'active' : ''}"><a href="<c:url value="/categories/manage-categories.html" />">Manage Categories</a></li>
</ul>