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
<title>leaflet - AD by POSTMAN</title>
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
				<li><a href="busi_login.jsp"><span class="icon fa-home"></span></a></li>
				<li><a href="upload.jsp" class="active"><span
						class="icon fa-camera-retro"></span></a></li>
				<li><a href="#"><span class="icon fa-file-text-o"></span></a></li>
			</ul>
		</nav>

		<!-- Main -->
		<section id="main">

			<!-- Header -->
			<header id="header">
				<div>
					AD <span>by POSTMAN</span>
				</div>
			</header>

			<!-- Gallery -->
			<section id="galleries">

				<!-- Upload -->
				<h2>Upload Your Leaflet !!!</h2>
				<form action="upload_image.jsp" method="post"
					enctype="multipart/form-data">
					<div class="card" style="margin-bottom: 10px;">
						<div class="card-header">상세 이미지</div>
						<div class="card-body">
							<table>
								<tr>
									<td><img style="margin: 10px;" id="preview" src="#"
										alt="업로드 전단" /></td>
								</tr>
								<tr>
									<td>카테고리 선택:<select class="form-control-file" name="P_CA">
											<option value="all food">식당</option>
											<option value="all hair">헤어샵</option>
											<option value="all toiec">토익</option>
									</select> <br> 총 포인트:<input type="text"
										class="form-control-file" name="P_PO"><br> 전단지
										파일업로드 : <input type="file" class="form-control-file"
										onchange="previewImage(this)" name="file"><br> <input
										style="margin-bottom: 5px;" type="submit"
										class="btn btn-primary btn-lg btn-block" name="전단사진 업로드" />
									</td>
								</tr>
							</table>
						</div>
					</div>

				</form>

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
	<script>
		function previewImage(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#preview').attr('src', e.target.result).width(300)
				};
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.poptrox.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
</body>
</html>