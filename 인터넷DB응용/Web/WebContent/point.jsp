<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포인트적립</title>
</head>
<body>
	<%
		String h1 = "";
		int p_no = 0;
		if (session.getAttribute("U_NO") == null) {
			//response.sendRedirect("/Postman/login.jsp");
			h1 = "로그인이 필요한 서비스 입니다. 로그인하여 주십시오";
		} else {
			int u_no = ((Integer) session.getAttribute("U_NO")).intValue();
			int p_vp = 0, p_vi = 0, p_po = 0;
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273",
					"201630273");
			String post = request.getParameter("post");
			PreparedStatement st = null;
			if (post == null || post.trim().equals("")) { // 인자가 없거나 공백인 경우 
				// redirect 404.html
				response.sendRedirect("/Postman/404.jsp");
			} else {
				//P_VP 체크				
				try {
					p_no = Integer.parseInt(post);
				} catch (Exception e) {
					response.sendRedirect("/Postman/404.jsp");
				}
				st = conn.prepareStatement("select P_VP,P_PO,P_VI from POST where p_no=?");
				st.setInt(1, p_no);
				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					p_vp = rs.getInt("P_VP");
					p_po = rs.getInt("P_PO");
					p_vi = rs.getInt("P_VI");
				}
				rs.close();
				st.close();
			}
			if (p_po > 0 && p_vp > 0) {
				//적립내역 확인
				st = conn.prepareStatement("select * from views where p_no=? and u_no=?");
				st.setInt(1, p_no);
				st.setInt(2, u_no);
				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					h1 = "이미 적립되었습니다.";
					//적립내역이 존재함
				} else {
					st = conn.prepareStatement("insert into views values (VIEWS_SEQ.NEXTVAL,SYSDATE,?,?)");
					st.setInt(1, p_no);
					st.setInt(2, u_no);
					st.executeUpdate();
					int u_po = ((Integer) session.getAttribute("U_PO")).intValue();
					u_po += p_vp;
					session.setAttribute("U_PO", u_po);
					st = conn.prepareStatement("update usr set u_po = ? where u_no = ?");
					st.setInt(1, u_po);
					st.setInt(2, u_no);
					st.executeUpdate();
					st = conn.prepareStatement("update post set p_po = ?,p_vi = ? where p_no = ?");
					st.setInt(1, p_po - p_vp);
					st.setInt(2, ++p_vi);
					st.setInt(3, p_no);
					st.executeUpdate();
					h1 = p_vp + "포인트 적립!!";
				}
				rs.close();
				st.close();
			}else{
				h1 = "적립 포인트가 마감되었습니다. 죄송합니다.";
			}
			conn.close();
		}
		
	%>
	<script>alert("<%=h1%>");
	document.location.href="/Postman/index.jsp"
	</script>
</body>
</html>