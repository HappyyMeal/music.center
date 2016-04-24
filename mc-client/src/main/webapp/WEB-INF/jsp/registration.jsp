<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/css.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css" />
<title>Registration</title>
</head>
<body>

	<c:if test="${error != null}">
		<div class="alert alert-danger">
			<strong>Ошибка!</strong> ${error}
		</div>
	</c:if>

	<div class="login-page">
		<div class="form">
			<form class="login-form" action="/mc-client/user/registration"
				method="post">
				<fieldset>
					<input type="text" placeholder="Имя пользователя" name="username"
						required /> <input id="password" type="password"
						placeholder="Пароль" name="password" required /> <input
						id="confirm_password" type="password"
						placeholder="Повторите пароль" name="confirm_password" required />
					<button id="btnSubmit" type="submit">зарегистрироваться</button>
				</fieldset>
			</form>
			<a href="/mc-client/user/login"><button id="btnSubmit" type="submit">домой</button></a>
		</div>
	</div>

	<script src="/mc-client/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="/mc-client/resources/js/js.js"></script>
</body>
</html>