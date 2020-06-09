<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello</title>
</head>
<body>

	<c:if test="${!empty pageContext.request.userPrincipal.name}">
		<li><a href="<c:url value="/index" />">
		Welcome		${pageContext.request.userPrincipal.name} </a></li>

	</c:if>


	<c:if test="${pageContext.request.userPrincipal.name==null}">
		<li><a href="<c:url value="/login" />"> Login</a></li>
	</c:if>
	I'm IN

</body>
</html>