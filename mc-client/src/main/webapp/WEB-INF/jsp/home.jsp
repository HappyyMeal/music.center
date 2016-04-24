<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Main page</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/style.css" />
</head>

<body>
	<div class="navbar navbar-fixed-top" data-activeslide="1">
		<div class="container">

			<!-- .navbar-toggle is used as the toggle for collapsed navbar content -->
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>



			<div class="nav-collapse collapse navbar-responsive-collapse">
				<ul class="nav row">
					<li data-slide="1" class="col-12 col-sm-2"><a id="menu-link-1"
						href="http://localhost:8082/mc-client/home" title="Next Section"><span
							class="icon icon-home"></span> <span class="text">Домой</span></a></li>

					<li data-slide="4" class="col-12 col-sm-2"
						style="text-align: center; float: right;"><a id="menu-link-4"
						href="http://localhost:8082/mc-client/user/login"
						title="Next Section"><span class="icon icon-gears"></span> <span
							class="text">Выход</span></a></li>

				</ul>

				<div class="row">
					<div class="col-sm-2 active-menu"></div>
				</div>
			</div>
			<!-- /.nav-coll
			<!-- /.nav-collapse -->
		</div>
		<!-- /.container -->

		<h4 style="position: absolute; right: 5%; top: 0; color: #FFFFFF">
			Добро пожаловать, ${sessionScope.username }</h4>
	</div>
	<!-- /.navbar -->


	<!-- === MAIN Background === -->
	<div class="slide story" id="slide-1" data-slide="1"
		style="height: 95%;">
		<div class="container">
			<div id="home-row-1" class="row clearfix">
				<div class="col-12">
					<h1 class="font-semibold">
						Музыкальный <span class="font-thin">Сервис</span>
					</h1>
					<h4 class="font-thin">
						Мы <span class="font-semibold">независимое интереактивное
							агенство</span>.
					</h4>
					<br> <br>
				</div>
				<!-- /col-12 -->
			</div>
			<!-- /row -->
			<div id="home-row-2" class="row clearfix">
				<div class="col-12 col-sm-4">
					<div class="home-hover navigation-slide" data-slide="4">
						<a href="playlist/show-all"><img
							src="${pageContext.servletContext.contextPath}/resources/images/s02.png"></a>
					</div>
					<span>Управление Плейлистами</span>
				</div>
				<div class="col-12 col-sm-4">
					<div class="home-hover navigation-slide" data-slide="5">
						<a href="http://localhost:8082/mc-client/search"><img
							src="${pageContext.servletContext.contextPath}/resources/images/s03.png"></a>
					</div>
					<span>Управление песнями</span>
				</div>
				<div class="col-12 col-sm-4">
					<div class="home-hover navigation-slide" data-slide="5">
						<a href="artist/show-all"><img
							src="${pageContext.servletContext.contextPath}/resources/images/s01.png"></a>
					</div>
					<span>Управление артистами</span>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>

	<!-- /slide1 -->
	<!-- SCRIPTS -->
	<script
		src="${pageContext.servletContext.contextPath}/resources/js/html5shiv.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/js/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/js/jquery.easing.1.3.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/js/script.js"></script>

	<!-- fancybox init -->
	<script>
		$(document).ready(function(e) {
			var lis = $('.nav > li');
			menu_focus(lis[0], 1);

			$(".fancybox").fancybox({
				padding : 10,
				helpers : {
					overlay : {
						locked : false
					}
				}
			});

		});
	</script>
</body>
</html>