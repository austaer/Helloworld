<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.around {
	display: flex;
	justify-content: space-around;
	flex-direction: initial;
	padding
}

.centerAlign {
	display: flex;
	justify-content: center;
	flex-direction: initial;
}
</style>

<!-- 最新編譯和最佳化的 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<!-- 選擇性佈景主題 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<title>Insert title here</title>
</head>
<body>
	<div class="around">
		<span>Login Page !</span>
	</div>
	<div class="centerAlign" style="align-items: center; height: 300px;">
		<form:form method="POST" commandName="user"
			action="${pageContext.request.contextPath}/login">
			<div style="display: table;">
				<div style="display: table-row;">
					<span style="display: table-cell;">帳號</span> <span
						style="display: table-cell;"><form:input path="account" /></span>
				</div>
				<div style="display: table-row;">
					<span style="display: table-cell;">密碼</span> <span
						style="display: table-cell;"><form:input path="password"
							type="password" /></span>
				</div>
			</div>
			<div class="centerAlign">
				<input type="submit" value="Login" class="btn btn-sm btn-default" />
				<a href="${pageContext.request.contextPath}/webmgr/register"><input
					type="button" value="register" class="btn btn-sm btn-default" /></a>
			</div>
		</form:form>
	</div>
</body>
</html>