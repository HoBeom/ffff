<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE HTML>
<!--
	Snapshot by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->		
<html>
<%
	Class.forName("oracle.jdbc.OracleDriver");
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273",
			"201630273");
	String post = request.getParameter("post");
	PreparedStatement st = null;
	int[] p_no = new int[8];
	int[] p_vp = new int[8];
	int[] p_vi = new int[8];
	int[] p_po = new int[8];
	int[] b_no = new int[8];
	String[] p_ca = new String[8];
	String[] b_na = new String[8];
	String[] tele = new String[8];

	st = conn.prepareStatement(
			"select * from (POST inner join BUSI on post.b_no = BUSI.b_no) order by P_NO DESC");
	ResultSet rs = st.executeQuery();
	for (int i = 0; rs.next() && i < 8; i++) {
		b_no[i] = rs.getInt("B_NO");
		p_vp[i] = rs.getInt("P_VP");
		p_po[i] = rs.getInt("P_PO");
		p_no[i] = rs.getInt("P_NO");
		p_vi[i] = rs.getInt("P_VI");
		p_ca[i] = rs.getString("P_CA");
		b_na[i] = rs.getString("B_NA");
		tele[i] = rs.getString("tele");
		System.out.println(p_no[i]);
	}
	rs.close();
	st.close();
%>
<head>
<title>POSTMAN</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="icon" href="/Postman/favicon.ico" type="image/x-icon"/>
</head>
<body>
	<div class="page-wrap">

		<!-- Nav -->
		<nav id="nav">
			<ul>
				<li><a href="index.jsp" class="active"><span
						class="icon fa-home"></span></a></li>
				<li><a href="gallery.jsp"><span
						class="icon fa-camera-retro"></span></a></li>
				<li><a href="generic.jsp"><span class="icon fa-file-text-o"></span></a></li>
			</ul>
		</nav>

		<!-- Main -->
		<section id="main">

			<!-- Banner -->
			<section id="banner">
				<div class="inner">
					<h1>POST MAN</h1>
					<p>We service leaflet with online</p>
					<ul class="actions">
						<li><a href="#galleries" class="button alt scrolly big">GET
								SOME POINT !!!</a></li>
					</ul>
				</div>
			</section>

			<!-- Gallery -->
			<section id="galleries">

				<!-- Photo Galleries -->
				<div class="gallery">
					<header class="special">
						<h2>What's New</h2>
					</header>
					<!-- newest 8! -->
					<div class="content">
					<%for(int i = 0; i < p_no.length;i++){ %>
						<div class="media">
							<a href="/Postman/Imagetoster?post=<%=p_no[i]%>"><img
								src="/Postman/Imagetoster?post=<%=p_no[i]%>" alt=""
								<%if(p_po[i]>500){ %>
								OnClick="location.href='detail.jsp?post=<%=p_no[i]%>'"
										<%} %> title="<%=b_na[i] %>" /></a>
						</div>
						<%} %>
					</div>
					<footer>
						<a href="gallery.jsp" class="button big">MORE</a>
					</footer>
				</div>
			</section>

			<!-- Contact -->
			<section id="contact">
				<!-- Social -->
				<div class="business column">
					<h3>How can you upload leaflet</h3>
					<p>We are an online leaflet promotional platform. We can
						promote your leaflet in your area. When you give point to viewer,
						Many viewer see your leaflet.</p>
					<p>We will come bigger platform as soon as possible. You should
						start faster. And don't hesitate. Why don't you join our business
						partner? You can join easy and fast. Push bellow button and join
						us.</p>
					<h3>Start your business promotion</h3>
					<a href="busi_login.jsp">
						<button>Business Promotion</button>
					</a>
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span
								class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span
								class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span
								class="label">Instagram</span></a></li>
					</ul>
				</div>

				<!-- Form -->
				<%
					if (session.getAttribute("U_NO") == null) {
				%>
				<div class="column">
					<h3>Sign IN</h3>
					<form action="login_check.jsp" method="post">
						<div class="field half first">
							<label for="name">ID</label> <input name="j_username" id="id_si"
								type="text" placeholder="ID">
						</div>
						<div class="field half">
							<label for="email">PASSWORD</label> <input name="j_password"
								id="password_si" type="password" placeholder="PASSWORD">
						</div>
						<ul class="actions">
							<li><input value="Sign In" class="button" type="submit"></li>
						</ul>
					</form>
					<h3>Sign UP</h3>
					<form action="joinProcess.jsp" method="post">
						<div class="field half first">
							<label for="name">NAME</label> <input name="U_NA" id="name_su"
								type="text" placeholder="NAME">
						</div>
						<div class="field half">
							<label for="email">TELEPHONE</label> <input name="TELE"
								id="tele_su" type="text" placeholder="010-1234-5678">
						</div>
						<div class="field half first">
							<label for="name">ID</label> <input name="ID" id="id_su"
								type="text" placeholder="ID">
						</div>
						<div class="field half">
							<label for="email">PASSWORD</label> <input name="PASS"
								id="pass_su" type="password" placeholder="PASSWORD">
						</div>

						<ul class="actions">
							<li><input value="Sign Up" class="button" type="submit"></li>
						</ul>
					</form>
				</div>
				<%
					} else {
				%>
				<div class="column">
					<h3>User Infomation</h3>
					<p>
						Name :
						<%=session.getAttribute("U_NA")%></p>
					<p>
						Point :
						<%=session.getAttribute("U_PO")%></p>
					<p>
						TEL :
						<%=session.getAttribute("TELE")%></p>

					<form action="logout.jsp" method="post">
						<ul class="actions">
							<li><input value="Sign Out" class="button" type="submit"></li>
						</ul>
					</form>
					<form action="refund.jsp" method="post">
						<ul class="actions">
							<li><input value="환급" class="button" type="submit"></li>
						</ul>
					</form>
				</div>
				<%
					}
				%>
			</section>

			<!-- Footer -->
			<footer id="footer">
				<div class="copyright">
					&copy; Untitled Design: <a href="https://templated.co/">TEMPLATED</a>.
					Images: <a href="https://unsplash.com/">Unsplash</a>.
				</div>
			</footer>
		</section>
	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.poptrox.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>