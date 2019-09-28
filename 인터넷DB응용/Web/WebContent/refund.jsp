<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>포인트환급</title>
</head>
<body>
	<%
		String h1 = "";
		if (session.getAttribute("U_NO") == null) {
			//response.sendRedirect("/Postman/login.jsp");
			h1 = "로그인이 필요한 서비스 입니다. 로그인하여 주십시오";
		} else {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273",
					"201630273");
			PreparedStatement st = null;
			int u_po = ((Integer)session.getAttribute("U_PO")).intValue();
			int u_no = ((Integer)session.getAttribute("U_NO")).intValue();
			if(u_po >= 1000){
				h1 = String.valueOf(u_po)+"원 환급 되었습니다.!!";				
				st = conn.prepareStatement("Insert into hist values (?,SYSDATE,?)");
				st.setInt(1, u_no);
				st.setInt(2, u_po);
				st.executeUpdate();
				st = conn.prepareStatement("update usr set u_po = 0 where u_no = ?");
				st.setInt(1, u_no);
				st.executeUpdate();
				session.setAttribute("U_PO", 0);
				st.close();
			}else{
				h1 = "1000원 이상 환급 가능합니다.!!";
			}
		}
	%>
	<script>alert("<%=h1%>");
	document.location.href="/Postman/index.jsp"
	</script>
</body>
</html>