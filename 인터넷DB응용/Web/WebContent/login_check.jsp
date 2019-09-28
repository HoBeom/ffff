<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>login_check</title>
</head>
<body>
	<%
		String h1 = "찾을 수 없는 ID 입니다.";
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("j_username");
		String pass = request.getParameter("j_password");
		// Parameter 체크 필요함

		Class.forName("oracle.jdbc.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273",
				"201630273");

		PreparedStatement st = null;

		if (id == null || id.trim().equals("")) { // 인자가 없거나 공백인 경우 
			// redirect 404.html
			response.sendRedirect("login-error.jsp");
		} else { // 정상적인 값이 전달된 경우 
			st = conn.prepareStatement("select * from login where id='" + id + "'");
		}
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			String cpass = rs.getString("pass");
			String type = rs.getString("type");
			int u_no = rs.getInt("u_no");
			if (pass.equals(cpass.trim())) {
				session.setAttribute("TYPE", type);
				session.setAttribute("U_NO", u_no);
				st = conn.prepareStatement("select * from USR where u_no = ?");
				st.setInt(1, u_no);
				rs = st.executeQuery();
				rs.next();
				session.setAttribute("U_NA", rs.getString("U_NA"));
				session.setAttribute("U_PO", rs.getInt("U_PO"));
				session.setAttribute("TELE", rs.getString("TELE"));				
				response.sendRedirect("index.jsp");
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
	document.location.href="/Postman/index.jsp"
	</script>
</body>
</html>