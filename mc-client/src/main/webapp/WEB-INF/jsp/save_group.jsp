<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Save group page</title>
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

	<c:choose>
		<c:when test="${group != null}">
			<!-- /.navbar -->
			<div class="slide story" id="slide-1" data-slide="1"
				style="height: 100%;">
				<form class="form-inline" role="form"
					action="/mc-client/artist/group/update/${group.artistId}"
					method="post">
					<label class="sr-only" for="groupName">Имя группы:</label>
					<div class="form-group">
						<input type="text" class="form-control" id="groupName"
							name="groupName" value="${group.groupName}" required>
					</div>
					<label class="sr-only" for="artistSiteUrl">Персональный
						сайт:</label>
					<div class="form-group">
						<input type='text' class="form-control" name="artistSiteUrl"
							id="artistSiteUrl" value="${group.artist.artistSiteUrl}" required />
					</div>

					<label for="genre">Жанр:</label>
					<div class="form-group">
						<select class="form-control" id="genre" name="genre">
							<c:forEach var="genre" items="${genres}">
								<c:choose>
									<c:when test="${group.artist.genre.genreId ==  genre.genreId}">
										<option value="${genre.genreId}" selected="selected">${genre.genreName}</option>
									</c:when>
									<c:otherwise>
										<option value="${genre.genreId}">${genre.genreName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>

					<label for="countryName">Страна:</label>
					<div class="form-group">
						<select class="form-control" id="country" name="country">
							<c:forEach var="country" items="${countries}">
								<c:choose>
									<c:when
										test="${group.artist.country.countryId eq country.countryId}">
										<option value="${country.countryId}" selected="selected">${country.countryName}</option>
									</c:when>
									<c:otherwise>
										<option value="${country.countryId}">${country.countryName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>

					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>

		<c:otherwise>
			<div class="slide story" id="slide-1" data-slide="1"
				style="height: 100%;">
				<form class="form-inline" role="form"
					action="/mc-client/artist/group/save" method="post">
					<label class="sr-only" for="groupName">Имя группы:</label>
					<div class="form-group">
						<input type="text" class="form-control" id="groupName"
							name="groupName" required>
					</div>
					<label class="sr-only" for="artistSiteUrl">Персональный
						сайт:</label>
					<div class="form-group">
						<input type='text' class="form-control" name="artistSiteUrl"
							id="artistSiteUrl" required />
					</div>

					<label for="genre">Жанр:</label>
					<div class="form-group">
						<select class="form-control" id="genre" name="genre">
							<c:forEach var="genre" items="${genres}">
								<option value="${genre.genreId}">${genre.genreName}</option>
							</c:forEach>
						</select>
					</div>

					<label for="countryName">Страна:</label>
					<div class="form-group">
						<select class="form-control" id="country" name="country">
							<c:forEach var="country" items="${countries}">
								<option value="${country.countryId}">${country.countryName}</option>
							</c:forEach>
						</select>
					</div>
					<input type="hidden" name="groupId" value="${group.artistId}">
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:otherwise>

	</c:choose>
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