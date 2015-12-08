<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
Login sucess!  wait 5 second and then redirect page.
<a href="/Helloworld">click here to redirect page at this time</a>
</body>
<script>
	setTimeout(function(){
		location.href = "/Helloworld";
	}, 5000);
</script>
</html>