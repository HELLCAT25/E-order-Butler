<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style>
	.checkbox {
		width=500;
		height=600;
	}
</style>

</head>
<body>
	<h3 align="center">Sign In</h3>

	<c:if test="${not empty registrationSuccess}">
		<div class="error" style="color: #ff0000;">${registrationSuccess}</div>
	</c:if>
	<!-- will display after logged out successfull -->
	<c:if test="${not empty logout}">
		<div class="error" style="color: #ff0000;">${logout}</div>
	</c:if>

	<form name="loginForm" action="<c:url value="/login"/>" method="post">
		<fieldset>
			<div class="form-group">
				<input class="form-control" placeholder="E-mail" name="username"
					type="email">
			</div>
			<div class="form-group">
				<input class="form-control" placeholder="Password" name="password"
					type="password">
			</div>
			<div class="checkbox" >
				<c:if test="${not empty error}">
					<div class="error" style="color: #ff0000">${error}</div>
				</c:if>
			</div>
			<!-- Change this to a button or input when using this as a form -->
			<button type="submit" class="btn btn-sm btn-success"
				style="margin-right: 79px; margin-left: 60px">Login</button>
		</fieldset>
	</form>
</body>
</html>