<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel='stylesheet' href='${contextPath}/resources/css/cre8_about.css'>
</head>
<body>
	<div class="outer1" id="searchSpace">
		<div class="inner">
			<img src="${contextPath}/resources/image/cre8-logo-white.png" width="350px">
			<div class="inner_txt"><br>크리에잇은 누구나, 어떤 형태의 창작물이든 자유롭게 선보일 수 있는 아티스트가 될 수 있는 공간입니다.</div>
		</div>
	</div>

	<div class="outer2" id="searchSpace">
		<div class="inner">
			<h1>
			무한(∞)한 가능성
			</h1>
			<div class="inner_txt"><br>크리에잇은 창작(create)과 에잇(8)의 합성어로. 8은 무한(∞)을 의미합니다.<br><br>
			창작의 종류에 상관없이 누구나 아티스트가 될 수 있는 무한한 가능성이 여기 있습니다.</div>
		</div>
	</div>

	<div class="outer3" id="searchSpace">
		<div class="inner">
			<h1>
			힘이 되는 창작활동
			</h1>
			<div class="inner_txt"><br>크리에잇에서는 좋아하는 창작물을 응원할 수 있습니다.<br><br>
			포인트를 사용하여 작품을 후원할 수 있고, 또한 후원을 받을 수 있습니다.</div>
		</div>
	</div>


	<div class="outer4" id="searchSpace">
		<div class="inner">
			<h1>
			연결되는 아름다움
			</h1>
			<div class="inner_txt"><br>크리에잇에서는 누구나 아티스트!<br><br>
			글, 그림, 음악, 영상, 사진. 지금 크리에잇을 둘러보세요.</div>
		</div>
	</div>


</body>
</html>