<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/css.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css" />
<title>Song Management</title>
</head>
<style media="screen" type="text/css">
#backgroundaudio {
	display: block;
	position: fixed;
	bottom: -43px;
	left: 5px;
	-webkit-transition: all 1s ease-in-out;
	-moz-transition: all 1s ease-in-out;
	-ms-transition: all 1s ease-in-out;
	-o-transition: all 1s ease-in-out;
	transition: all 1s ease-in-out;
}

#backgroundaudio:hover {
	bottom: 0;
	-webkit-transition: all 1s ease-in-out;
	-moz-transition: all 1s ease-in-out;
	-ms-transition: all 1s ease-in-out;
	-o-transition: all 1s ease-in-out;
	transition: all 1s ease-in-out;
}

#backgroundaudio audio {
	background: #ffffff;
	padding: 5px;
	display: table-cell;
	vertical-align: middle;
	height: 43px;
	z-index: 9998;
}

#backgroundaudio i {
	font-size: 40px;
	display: block;
	background: #ffffff;
	padding: 5px;
	width: 50px;
	float: none;
	margin-bottom: -1px;
	z-index: 9999;
}
</style>
<body>
	Hey, ${username }
	<i class="icon-volume-up"></i>
	<div id="backgroundaudio">
		<table>
			<tr>
				<td>Song Name</td>
				<td>Song Duration</td>
				<td>Song Recording</td>
			</tr>
			<c:forEach items="${songs}" var="song">
				<tr>
					<td>${song.songTitle}</td>
					<td>${song.songDuration}</td>
					<td><audio controls="controls" preload="none">
							<source src="${song.songLink}" type="audio/mp3">
						</audio></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>