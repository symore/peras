<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


  <form:form cssClass="form-signin" modelAttribute="loginRequest" action="j_spring_security_check">
    <form:errors element="div" cssClass="validation-error" />
    <input type="hidden" name="_spring_security_remember_me" value="true"/>
    <div class="form-group">
      <p>
        <label for="input_username">Username</label>
        <form:input cssClass="form-control" path="username" id="input_username" placeholder="Username" />
      </p>
      <p>
        <label for="input_password">Password</label>
        <form:input cssClass="form-control" path="password" id="input_password" placeholder="Password" />
      </p>
    </div>
    <p>
      <button type="submit" class="btn btn-lg btn-primary btn-block">Login</button>
    </p>
  </form:form>
