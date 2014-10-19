<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css" />" type="text/css" media="all">

<title>Welcome</title>

</head>
<body>
  <div class="container">
    <!-- tiles:insertAttribute name="header" / -->
    <div class="content">
      <tiles:insertAttribute name="menu" />
      <tiles:insertAttribute name="body" />
    </div>
    <!-- tiles:insertAttribute name="footer" / -->
  </div>
</body>
</html>