<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="add">Add</a>
	<table>
		<tr>
			<td>ID:</td>
			<td>Name:</td>
			<td>Age:</td>
		</tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.id }</td>
				<td>${user.userName }</td>
				<td>${user.age }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>