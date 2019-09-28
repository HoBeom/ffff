<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>logout</title>
</head>
<body>
	<% session.invalidate(); %>
	<script>alert("로그아웃되었습니다.");
	document.location.href="/Postman/index.jsp"
	</script>
</body>	
</html>