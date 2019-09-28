<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>	
<meta charset="UTF-8">
<title>B_login_check</title>
</head>
<body>
	<%
		String h1 = "다시 로그인 하여 주십시오";
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("j_Businame");
		String pass = request.getParameter("j_Password");
		// Parameter 체크 필요함
		Class.forName("oracle.jdbc.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273",
				"201630273");
		PreparedStatement st = null;
		if (id == null || id.trim().equals("")) { // 인자가 없거나 공백인 경우 
			// redirect 404.html
			h1 = "아이디를 입력해주십시오.";
		} else { // 정상적인 값이 전달된 경우 
			st = conn.prepareStatement("select * from login where id='" + id + "'");
		}
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			String cpass = rs.getString("pass");
			System.out.print(cpass);
			String type = rs.getString("type");
			int B_NO = rs.getInt("B_NO");
			if (pass.equals(cpass.trim())) {
				session.setAttribute("TYPE", type);
				session.setAttribute("B_NO", B_NO);
				st = conn.prepareStatement("select * from BUSI where B_NO = ?");
				st.setInt(1, B_NO);
				rs = st.executeQuery();
				rs.next();
				session.setAttribute("B_NA", rs.getString("B_NA"));
				session.setAttribute("TELE", rs.getString("TELE"));	
				h1 = "로그인 되었습니다.";
			} else {
				h1 = "비밀번호 오류";
			}
		}
		rs.close();
		st.close();
		conn.close();
	%>
	<script>alert("<%=h1%>");
	document.location.href="/Postman/busi_login.jsp"
	</script>
</body>
</html>
