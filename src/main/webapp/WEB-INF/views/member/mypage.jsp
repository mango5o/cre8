<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    


<html>
<head>
<meta charset=UTF-8">
<title>마이페이지</title>
</head>
<link rel='stylesheet' href='${contextPath}/resources/css/cre8_mypage.css'>
<body>
<!-- 로그인 세션부분 -->
<c:choose>
          <c:when test="${isLogOn != true  || member == null}">
          <h1>잘못된 접근입니다!</h1>
          </c:when>
<c:otherwise>

<!-- 본문 시작 -->
<div class="mypage_outer">
<div class="box">
<li>
<div id="loginname">${memberOne.nickname} 님의 마이페이지</div>
</li>
<hr>
<li>
<div class="panal">나의 좋아요<br><a href="${contextPath}/member/mylikes.do?id=${memberOne.id }"><img src="${contextPath}/resources/image/like.png" width="60px"><br>${countLikes} 작품</a></div>
</li>
<li>
<div class="panal">나의 후원<br><a href="${contextPath}/member/mydonate.do?id=${memberOne.id }"><img src="${contextPath}/resources/image/donate.png" width="60px"><br>${mydonate} 건</a></div>
</li>
<li>
<div class="panal">나의 창작<br><a href="${contextPath}/board/myArticles.do?id=${memberOne.id }"><img src="${contextPath}/resources/image/create.png" width="60px"><br>${myArticle} 작품</a></div>
</li>
<li>
<div id="mypoint">나의 포인트&nbsp;_&nbsp;${mypoint} 점</div>
<a href="${contextPath}/member/mypoint.do?id=${memberOne.id }" class="button">포인트 관리</a>
</li>
<hr>
<li>
<div class="infotb">나의 이름 : ${memberOne.name}<br>나의 ID : ${memberOne.id}<br>
<!-- 비밀번호 : ${memberOne.pwd}<br> -->
가입일자 : ${memberOne.joinDate}<br>
이메일 : ${memberOne.email}
<!-- 가입상태 : ${memberOne.state}<br> -->
</div>
</li>
<li>
<div>
<a href="${contextPath}/member/modMember.do?id=${memberOne.id }" class="button">정보 수정</a>&nbsp;
<a href="${contextPath}/member/resignMember.do" class="button">회원 탈퇴</a>
</div>
</li>
</div>
</div>
</c:otherwise>
</c:choose>  	
</body>
</html>
