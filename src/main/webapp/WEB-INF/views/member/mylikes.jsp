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
<title>나의 좋아요 출력창</title>
<link rel='stylesheet' href='${contextPath}/resources/css/cre8_board.css'>
</head>

<body>
<h1>${member.nickname} 님의 좋아요 목록</h1>

<table class="viewTB">
    <tr>
      <th>번호</th>
      <th>카테고리</th>
      <th>글제목</th>
      <th>작가</th>
      <th>좋아요 한 날짜</th>
   </tr>
<tbody>	
 <c:forEach var="mylikeslist" items="${mylikesList}" varStatus="status">     
   <tr align="center">
   	  <td>${fn:length(mylikesList) - status.index}</td>
      <td><c:set var="temp" value="${mylikeslist.categoryNO}"/>
      
       <!--<c:out value="${temp}"/>-->     
     <c:choose>
      	<c:when test="${temp eq '1'}">
      	사진
      	</c:when>
      	<c:when test="${temp eq '2'}">
      	음악
      	</c:when>
      	<c:when test="${temp eq '3'}">
      	글
      	</c:when>
      	<c:when test="${temp eq '4'}">
      	그림
      	</c:when>
      	<c:when test="${temp eq '5'}">
      	영상
      	</c:when>
      	<c:when test="${temp eq '0'}">
      	전체 목록
      	</c:when>
      </c:choose>
      </td>
      <td id="_title"><a href="${contextPath}/board/viewArticle.do?articleNO=${mylikeslist.articleNO}">${mylikeslist.boardTitle}</a></td>
      <td>${mylikeslist.boardWriter}</td> 
      <td>${mylikeslist.likesDate}</td> 
    </tr>
  </c:forEach>   
</tbody>
</table>
<p>
<a href="${contextPath}/member/mypage.do?id=${member.id }" class="button">마이페이지</a>
</body>
</html>
