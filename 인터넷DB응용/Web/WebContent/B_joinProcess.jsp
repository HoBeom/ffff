<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<%
	String h1 = "잠시후 다시 시도해주세요.";
	request.setCharacterEncoding("UTF-8");
	String B_NA = request.getParameter("B_NA");
	String TELE = request.getParameter("TELE");
	String ID = request.getParameter("ID");
	String PASS = request.getParameter("PASS");
	Connection conn = null; //DB연결
	PreparedStatement pstmt = null; //SQL문
	ResultSet re = null; //해제
	int B_NO = 0;
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273",
				"201630273");		
		pstmt = conn.prepareStatement("select max(B_NO) from BUSI");
		ResultSet rs = pstmt.executeQuery();		
		if (rs.next()) {
			B_NO = rs.getInt("max(B_NO)")+1;
			pstmt = conn.prepareStatement("Insert into BUSI values(?,?,?)");
			pstmt.setInt(1, B_NO);
			pstmt.setString(2, B_NA);
			pstmt.setString(3, TELE);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("Insert into LOGIN values(?,?,'B',NULL,?)");
			pstmt.setString(1, PASS);
			pstmt.setString(2, ID);
			pstmt.setInt(3, B_NO);
		}
		rs.close();
		int result = pstmt.executeUpdate();
		if (result != 0) {			
			session.setAttribute("B_NO", B_NO);
			session.setAttribute("TELE",TELE);
			session.setAttribute("B_NA",B_NA);
			h1 = "회원가입 되었습니다.";
		} else {
			
		}
	} catch (Exception e) {
		e.printStackTrace();
		h1 = "중복된 아이디 입니다.";
		Class.forName("oracle.jdbc.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273",
				"201630273");		
		pstmt = conn.prepareStatement("Delete from BUSI where b_no=?");
		pstmt.setInt(1, B_NO);
		pstmt.executeUpdate();
	}%>
	
	<script>alert("<%=h1%>");
	document.location.href = "/Postman/busi_login.jsp"
</script>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>B_joinProcess</title>
</head>
<body>

</body>
</html>
