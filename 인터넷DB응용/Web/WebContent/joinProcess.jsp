<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<%
	String h1 = "죄송합니다. 다시 시도해 주십시오.";
	request.setCharacterEncoding("UTF-8");
	String U_NA = request.getParameter("U_NA");
	String TELE = request.getParameter("TELE");
	String ID = request.getParameter("ID");
	String PASS = request.getParameter("PASS");
	Connection conn = null; //DB연결
	PreparedStatement pstmt = null; //SQL문
	ResultSet re = null; //해제
	int U_NO = 0;
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273",
				"201630273");
		pstmt = conn.prepareStatement("select max(u_no) from USR");
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			U_NO = rs.getInt("max(u_no)") + 1;
			pstmt = conn.prepareStatement("Insert into USR values(?,?,?,0)");
			pstmt.setInt(1, U_NO);
			pstmt.setString(2, U_NA);
			pstmt.setString(3, TELE);
			pstmt.executeUpdate();			
			pstmt = conn.prepareStatement("Insert into LOGIN values(?,?,'U',?,NULL)");
			pstmt.setString(1, PASS);
			pstmt.setString(2, ID);
			pstmt.setInt(3, U_NO);
		}
		rs.close();
		int result = pstmt.executeUpdate();
		if (result != 0) {
			session.setAttribute("U_NO", U_NO);
			session.setAttribute("TELE", TELE);
			session.setAttribute("U_PO", 0);
			session.setAttribute("U_NA", U_NA);
			h1 = "회원가입 되었습니다.";
		} else {
			
		}
	} catch (Exception e) {
		e.printStackTrace();
		h1 = "중복된 아이디 입니다.";
		Class.forName("oracle.jdbc.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273",
				"201630273");		
		pstmt = conn.prepareStatement("Delete from USR where u_no=?");
		pstmt.setInt(1, U_NO);
		pstmt.executeUpdate();
	}
%>
<script>alert("<%=h1%>");
	document.location.href = "/Postman/index.jsp"
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>joinProcess</title>
</head>
<body>

</body>
</html>
