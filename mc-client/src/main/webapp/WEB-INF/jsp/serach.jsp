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
			<div class="jumbotron" style="background-color: #6FC5B1">
				<p>Интерактивный поиск песен.</p>
			</div>

			<div id="custom-search-input">
				<div class="input-group col-md-12">
					<input type="text" class="search-query form-control" id="songTitle"
						placeholder="Название песни" style="width: 79%" /> <select
						class="form-control" style="width: 20%">
						<option value="songTitle">Название песни</option>
					</select> <span class="input-group-btn">
						<button class="btn btn-default" type="button" id="send">
							Поиск</button>
					</span>
				</div>
			</div>


			<table id='resultTable' class="table"
				style="display: none; margin-left: 21%; width: 100%;">
				<thead>
					<tr>
						<th>Название песни:</th>
						<th>Проигрыватель:</th>
						<th>Добавить в плейлист</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

			<div id="failMessage" style="display: none">
				<div class="alert alert-danger" role="alert">
					<strong>Сожалеем!</strong> По вашему запросу ничего найдено не
					было.
				</div>
			</div>
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
	<script
		src="${pageContext.servletContext.contextPath}/resources/js/js.js"></script>

</body>
</html>