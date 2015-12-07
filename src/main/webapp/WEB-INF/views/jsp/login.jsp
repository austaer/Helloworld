<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<div><span>Login Page !</span> <span style="margin-left:86%"><a href="/register">register</a></span></div>
	<form:form method="POST" commandName="user"
		action="${pageContext.request.contextPath}/login">
		<div style="display: table;">
			<div style="display: table-row;">
				<span style="display: table-cell;">帳號</span> <span
					style="display: table-cell;"><form:input path="account"/></span>
			</div>
			<div style="display: table-row;">
				<span style="display: table-cell;">密碼</span> <span
					style="display: table-cell;"><form:input path="password" type="password"/></span>
			</div>
			<input type="submit" value="Login"/> 
		</div>
	</form:form>
</body>
</html>