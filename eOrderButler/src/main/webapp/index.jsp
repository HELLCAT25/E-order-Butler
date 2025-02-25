<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h2>Hello World!</h2>
	I have logged In !!!
	<div>
		<ul class="nav navbar-nav navbar-right">

			<c:if test="${!empty pageContext.request.userPrincipal.name}">
				<li><a href="<c:url value="/index" />"><span
						class="glyphicon glyphicon-shopping-user"></span>Welcome
						${pageContext.request.userPrincipal.name} </a></li>

				<security:authorize access="hasRole('ROLE_USER')">
					<li><a href="<c:url value="/getAllShoppingOrder" />"><span
							class="glyphicon glyphicon-shopping-cart"></span> Order</a></li>
				</security:authorize>
				<li><a href="<c:url value="/logout" />"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</c:if>
		</ul>


		<ul class="nav navbar-nav navbar-right">
			<c:if test="${pageContext.request.userPrincipal.name==null}">
				<li><a href="<c:url value="/login" />"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>
