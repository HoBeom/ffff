<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Snapshot by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<%
	if (session.getAttribute("U_NO") != null) {
		session.removeAttribute("U_NO");
		session.removeAttribute("TELE");
		session.removeAttribute("U_PO");
	}
%>
<html>
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
				<li><a href="busi_login.jsp" class="active"><span
						class="icon fa-home"></span></a></li>
				<li><a href="gallery.jsp"><span class="icon fa-camera-retro"></span></a></li>
				<li><a href="generic.jsp"><span class="icon fa-file-text-o"></span></a></li>
			</ul>
		</nav>

		<!-- Main -->
		<section id="main">

			<!-- Banner -->
			<section id="banner">
				<div class="inner">
					<h1>POST MAN</h1>
					<p>Business User Plaform</p>
					<ul class="actions">
						<li><a href="#galleries" class="button alt scrolly big">GO
								TO UPLOAD AD</a></li>
					</ul>
				</div>
			</section>

			<!-- Gallery -->
			<section id="galleries">

				<!-- Photo Galleries -->
				<div class="gallery">
					<%
						if (session.getAttribute("B_NO") == null) {
					%>
					<h2>Sign In for business</h2>
					<FORM action="B_Login_check.jsp" method="POST">
						<div class="field half first">
							<label for="name">ID</label> <input name="j_Businame" id="id_si"
								type="text" placeholder="ID">
						</div>
						<div class="field half">
							<label for="email">PASSWORD</label> <input name="j_Password"
								id="password_si" type="password" placeholder="PASSWORD">
						</div>
						<ul class="actions">
							<li><input value="Sign In" class="button" type="submit"></li>
						</ul>
					</FORM>

					<h2>Sign Up for business</h2>
					<form action="B_joinProcess.jsp" method="post">
						<div class="field half first">
							<label for="name">NAME</label> <input name="B_NA" id="name_su"
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
					<%
						} else {
					%>
					<footer>
						<a href="upload.jsp" class="button big">GO TO UPLOAD</a>
					</footer>
					<div class="column">
						<h3>Business Infomation</h3>
						<p>
							Name :
							<%=session.getAttribute("B_NA")%></p>
						<p>
							TEL :
							<%=session.getAttribute("TELE")%></p>

						<form action="B_logout.jsp" method="post">
							<ul class="actions">
								<li><input value="Sign Out" class="button" type="submit"></li>
							</ul>
						</form>
					</div>
					<%
						}
					%>
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

				<div class="column">
					<h3>Go to User Home</h3>
					<a href="index.jsp"><button>User Home</button></a>
				</div>

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