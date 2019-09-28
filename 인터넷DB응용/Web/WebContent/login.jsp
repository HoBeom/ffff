<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 테스트용 페이지 입니다.</title>
</head>
<body>
	<h1>로그인 하세요.</h1>
	<FORM action="login_check.jsp" method="POST">
		User name: <INPUT type="text" name="j_username"><br>
		Password: <INPUT type="password" name="j_password"><br> <INPUT
			type="submit" name="action" value="Login">
	</FORM>
</body>
</html>