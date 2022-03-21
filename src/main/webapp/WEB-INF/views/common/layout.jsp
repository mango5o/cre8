<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
a:link {
	color: gray;
	text-decoration: none;
}

a:visited {
	color: gray;
	text-decoration: none;
}

#container {
	text-align: center;
}

#header {
	margin-bottom: 0px;
	border-bottom: solid 1px #ccc;
	border: solid 1px #ccc;
	background: transparent;
}

#content {
	width: 100%;
	border: 0px;
}

#footer {
	clear: both;
	border: solid 1px #ccc;
	padding: 20px;
	font-size: 13px;
	text-align: center;
}
</style>
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>

		<div id="content">
			<tiles:insertAttribute name="body" />
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>