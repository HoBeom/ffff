<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Snapshot by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
<head>
<title>We made it!! - AD by POSTMAN</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="icon" href="/Postman/favicon.ico" type="image/x-icon" />
</head>
<body>
	<div class="page-wrap">

		<!-- Nav -->
		<nav id="nav">
			<ul>
				<li><a href="index.jsp"><span class="icon fa-home"></span></a></li>
				<li><a href="gallery.jsp"><span
						class="icon fa-camera-retro"></span></a></li>
				<li><a href="generic.jsp" class="active"><span
						class="icon fa-file-text-o"></span></a></li>
			</ul>
		</nav>

		<!-- Main -->
		<section id="main">

			<!-- Header -->
			<header id="header">
				<div>
					Made <span>by POSTMAN</span>
				</div>
			</header>

			<!-- Section -->
			<section>
				<div class="inner">
					<header>
						<h1>TEAM POSTMAN</h1>
					</header>
					<p></p>
					
					
					<h2>팀원소개</h2>
					<section class="columns double">
						<div class="column">
							<span class="image left special"><img
								src="images/pic01.jpg" alt="" /></span>
							<h2>전호범</h2>
							<p>밤샘코딩하며 너무 힘들었지만 재밌는 경험이였습니다.</p>
						</div>
						<div class="column">
							<span class="image left special"><img
								src="images/pic02.jpg" alt="" /></span>
							<h2>최현민</h2>
							<p>송하주 교수님 덕분에 많이 배웠습니다.</p>
						</div>
					</section>
					<h1>여러분 고생 하셨습니다! 즐거운 여름방학 되세요!</h1>
					<h4>교수님 사랑합니다.</h4>
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