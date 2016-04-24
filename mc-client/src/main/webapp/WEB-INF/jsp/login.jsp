<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Login page</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/css.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/style.css" />
</head>
<body>

	<c:if test="${error != null}">
		<div class="alert alert-danger">
			<strong>Ошибка!</strong> ${error}
		</div>
	</c:if>

	<c:if test="${success != null}">
		<div class="alert alert-success" role="alert">
			<strong>Ура!</strong> ${success}.
		</div>
	</c:if>

	<div class="login-page">
		<div class="form">
			<form class="login-form" action="/mc-client/user/login" method="post">
				<input type="text" placeholder="Имя пользователя" name="username"
					required /> <input type="password" placeholder="Пароль"
					name="password" required />
				<button>войти</button>
				<p class="message">
					Еще не зарегистрированы? <a href="/mc-client/user/registration">Создать
						учетную запись</a>
				</p>
			</form>
		</div>
	</div>

	<script type="text/javascript" src="resources/js/js.js"></script>
</body>
</html>