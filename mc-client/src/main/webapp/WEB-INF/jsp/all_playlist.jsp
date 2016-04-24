<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Search songs</title>
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

	<div class="slide story" id="slide-1" data-slide="1"
		style="height: 100%; margin-top: 5px;">
		<div class="container">
			<div class="jumbotron"
				style="background-color: #6FC5B1; margin-bottom: 10px;">
				<p>Управление плейлистами.</p>
			</div>

			<a href="/mc-client/playlist/show_save_tab">
				<button class="btn btn-default" style="width: 50%; height: 56px"
					type="button" id="send">Создать плейлист</button>
			</a>

			<table id='resultTable' class="table" style="width: 100%;">
				<thead>
					<tr>
						<th>Название плейлиста:</th>
						<th>Дата создания:</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="playlist" items="${playlists}">
						<tr>
							<th><a href="/mc-client/playlist/show-songs/${playlist.playlistId}">${playlist.playlistName}</a></th>
							<th>${playlist.createdTimestamp}</th>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>

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
