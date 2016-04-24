<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Show all artists</title>
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

	<div class="slide story" id="slide-1" data-slide="1"
		style="height: 100%; margin-top: 5px;">
		<c:if test="${deletedMsg != null}">
			<div class="alert alert-success" role="alert">
				<strong>Ура!</strong> ${deletedMsg}
			</div>
		</c:if>
		<c:if test="${savedMsg != null}">
			<div class="alert alert-success" role="alert">
				<strong>Ура!</strong> ${savedMsg}
			</div>
		</c:if>
		<c:if test="${updatedMsg != null}">
			<div class="alert alert-success" role="alert">
				<strong>Ура!</strong> ${updatedMsg}
			</div>
		</c:if>
		<div class="jumbotron"
			style="width: 80%; margin: 20px auto; background-color: #6FC5B1">
			<p>Информация о сольных исполнителях.</p>
		</div>
		<a href="/mc-client/artist/person/show_save_tab">
			<button class="btn btn-primary btn-lg" type="button">Добавить
				исполнителя</button>
		</a>
		<div class="row marketing">
			<table class="table"
				style="display: inline-block; margin-left: 21%; width: 100%;">
				<thead>
					<tr>
						<th>Имя:</th>
						<th>Фамилия:</th>
						<th>Дата рождения:</th>
						<th>Пол:</th>
						<th>Жанр:</th>
						<th>Страна:</th>
						<th>Личный вебсайт:</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="person" items="${persons}">
						<tr>
							<td>${person.firstName }</td>
							<td>${person.lastName }</td>
							<td>${person.birthday }</td>
							<td>${person.sex.sexName }</td>
							<td>${person.artist.genre.genreName }</td>
							<td>${person.artist.country.countryName }</td>
							<td>${person.artist.artistSiteUrl }</td>
							<td><button class="href-btn">
									<a class="href-a"
										href="/mc-client/artist/person/show_edit_tab/${person.artistId}">
										Редактировать</a>
								</button></td>
							<td><button class="href-btn">
									<a class="href-a"
										href="/mc-client/artist/person/delete/${person.artistId}">
										Удалить </a>
								</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="jumbotron"
			style="width: 80%; margin: 0 auto; background-color: #6FC5B1">
			<p>Информация о групах.</p>
		</div>
		<a href="/mc-client/artist/group/show_save_tab">
			<button class="btn btn-primary btn-lg">Добавить группу</button>
		</a>
		<div class="row marketing">
			<table class="table"
				style="display: inline-block; margin-left: 21%; width: 100%;">
				<thead>
					<tr>
						<th>Имя группы:</th>
						<th>Жанр:</th>
						<th>Страна:</th>
						<th>Личный вебсайт:</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="group" items="${groups}">
						<tr>
							<td>${group.groupName }</td>
							<td>${group.artist.genre.genreName }</td>
							<td>${group.artist.country.countryName }</td>
							<td>${group.artist.artistSiteUrl }</td>
							<td><button class="href-btn">
									<a class="href-a"
										href="/mc-client/artist/group/show_edit_tab/${group.artistId}">
										Редактировать</a>
								</button></td>
							<td><button class="href-btn">
									<a class="href-a"
										href="/mc-client/artist/group/delete/${group.artistId}">
										Удалить</a>
								</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
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