<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="./loginrequest.html" method="POST">
		UserName <form:input path="username"></form:input>
		Password <form:input path="pwd"></form:input>
		<form:select path="gender" >
			<form:option value="male"></form:option>
			<form:option value="female"></form:option>
		</form:select>
		<input type="submit" value="Login"/>
	</form:form>
</body>
</html>