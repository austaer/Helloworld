<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Hello, World!</title>
</head>
<body>
	Hello, World! the day is a today ${day}
	<c:if test="${not empty total}">
		<br />Total is ${total }
	</c:if>
	<br />
	<a href="${pageContext.request.contextPath}/add">Add New Employee</a>
	<br />

	<c:if test="${not empty operationType and operationType eq 'list' }">
		<table>
			<tbody>
				<c:forEach items="${employees }" var="employee" varStatus="no">
					<tr>
						<td>Id:</td>
						<td>${employee.id }</td>
					</tr>
					<tr>
						<td>Name:</td>
						<td>${employee.name }</td>
					</tr>
					<tr>
						<td>phone:</td>
						<td>${employee.phone }</td>
					</tr>
					<tr>
						<td>address:</td>
						<td>${employee.address}</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<c:if test="${not empty operationType and operationType eq 'add' }">
		<form:form method="POST" commandName="employee"
			action="${pageContext.request.contextPath}/add">
			<table>
				<tbody>
					<tr>
						<td>Name:</td>
						<td><form:input path="name" /></td>
					</tr>
					<tr>
						<td>phone:</td>
						<td><form:input path="phone" /></td>
					</tr>
					<tr>
						<td>address:</td>
						<td><form:input path="address" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Add" /></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</c:if>
</body>
</html>