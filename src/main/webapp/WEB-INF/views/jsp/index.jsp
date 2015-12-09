<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hello, World!</title>
<style>
table {
	border-collapse: collapse;
}

.endTr {
	border-bottom: 4px solid;
}
</style>

<!-- 最新編譯和最佳化的 CSS -->
<link rel="stylesheet"
	href="/resources/css/bootstrap.min.css">

<!-- 選擇性佈景主題 -->
<link rel="stylesheet"
	href="/resources/css/bootstrap-theme.min.css">
</head>
<body>
	<%@ include file="template/header.jsp"%>
	Hello, World! the day is a today ${day} ${loginStatus } ${username }
	<c:if test="${not empty total}">
		<br />Total is ${total }
	</c:if>
	<br />
	<a href="${pageContext.request.contextPath}/webmgr/list">Show
		Employees</a>
	<br />
	<a href="${pageContext.request.contextPath}/webmgr/add">Add New
		Employee</a>
	<p />

	<c:if test="${not empty operationType and operationType eq 'listOne' }">
		<table>
			<tbody>
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
				<tr class="endTr">
					<td>address:</td>
					<td>${employee.address}</td>
				</tr>
			</tbody>
		</table>
		<a
			href="${pageContext.request.contextPath}/webmgr/edit/${employee.id}"><input
			type="button" value="編輯" /></a>
		<input type="button" value="刪除" class="btn" data-method="delete"
			data-href="${pageContext.request.contextPath}/webmgr/delete/${employee.id}" />

		<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
		<script>
			(function() {
				var items = document.querySelectorAll(".btn");
				var len = items.length;
				$('.btn')
						.click(
								function(e) {
									var confirmCheck = confirm('確定要刪除嗎?');
									if (confirmCheck) {
										$
												.ajax({
													url : this.dataset.href,
													type : this.dataset.method,
												})
												.then(
														function(data) {
															if (data === "true") {
																alert("確認刪除了");
																location.href = "${pageContext.request.contextPath}/webmgr/list";
															}
														});
									} else {
										return;
									}
								});
			})();
		</script>
	</c:if>

	<c:if test="${not empty operationType and operationType eq 'edit' }">
		<form:form method="POST" commandName="employee"
			action="${pageContext.request.contextPath}/webmgr/edit/${employee.id }">
			<table>
				<tbody>
					<tr>
						<td>Name:</td>
						<td><form:input path="name" value="${employee.name }" /></td>
					</tr>
					<tr>
						<td>phone:</td>
						<td><form:input path="phone" value="${employee.phone }" /></td>
					</tr>
					<tr>
						<td>address:</td>
						<td><form:input path="address" value="${employee.address }" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="確定" /><input
							type="reset" value="重新輸入" /><a
							href="${pageContext.request.contextPath}/list"><input
								type="button" value="取消" /></a></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</c:if>

	<c:if test="${not empty operationType and operationType eq 'list' }">
		<table>
			<tbody>
				<c:forEach items="${employees }" var="employee" varStatus="no">
					<tr>
						<td>Id:</td>
						<td><a
							href="${pageContext.request.contextPath}/webmgr/list/${employee.id}">${employee.id }</a></td>
					</tr>
					<tr>
						<td>Name:</td>
						<td>${employee.name }</td>
					</tr>
					<tr>
						<td>phone:</td>
						<td>${employee.phone }</td>
					</tr>
					<tr class="endTr">
						<td>address:</td>
						<td>${employee.address}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<c:if test="${not empty operationType and operationType eq 'add' }">
		<form:form method="POST" commandName="employee"
			action="${pageContext.request.contextPath}/webmgr/add">
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
	${message }

	<!-- 最新編譯和最佳化的 JavaScript -->
	<script
		src="/resources/js/bootstrap.min.js"></script>

</body>
</html>