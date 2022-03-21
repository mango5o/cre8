<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>    


<html>
<head>
<meta charset=UTF-8">
<title>포인트 정보 출력창</title>
<link rel='stylesheet' href='${contextPath}/resources/css/cre8_board.css'>
</head>
<body>
<c:choose>
          <c:when test="${isLogOn != true  || member == null}">
          <h1>잘못된 접근입니다!</h1>
          </c:when>
	<c:otherwise>
<h1>${member.nickname} 님의 후원 내역</h1>
<table class="viewTB">    <tr>
      <th>번호</th>
      <th>날짜</th>
      <th>포인트</th>
      <th>상세내역</th>
      <th>잔여포인트</th>
         </tr>
         <tbody>	
 <c:forEach var="mydonatelist" items="${donateList}" varStatus="status">     
   <tr align="center">
      <td>${fn:length(donateList) - status.index}</td>
      <td>${mydonatelist.pointDate}</td>  
      <td><b><font color="#8e003c">${mydonatelist.outgoPoint}</font></b></td>
	  <td>
      	<a href="${contextPath}/board/viewArticle.do?articleNO=${mydonatelist.boardCode}">${mydonatelist.nickname}작가 - ${mydonatelist.boardTitle} 작품</a>에 후원
      </td>
      <td>${mydonatelist.currentPoint}</td>
  
    </tr>
  </c:forEach>   
</tbody>
</table>
<p>
<a href="${contextPath}/member/mypage.do?id=${member.id }" class="button">마이페이지</a>
</p>
</c:otherwise>
</c:choose>
</body>
</html>
