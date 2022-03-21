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
<h1>${member.nickname} 님의 포인트 내역</h1>
<a href="${contextPath}/member/myIncomePoint.do?id=${member.id}" class="button">포인트 충전</a>&nbsp;
<a href="${contextPath}/member/myOutgoPoint.do?id=${member.id}" class="button">포인트 출금</a>
<a href="${contextPath}/member/mypage.do?id=${member.id }" class="button">마이페이지</a>&nbsp;
<p>

<table class="viewTB">
    <tr>
      <th>번호</th>
      <th>날짜</th>
      <th>포인트</th>
      <th>상세내역</th>
      <th>잔여포인트</th>
   </tr>
<tbody>	
 <c:forEach var="mypointlist" items="${pointList}" varStatus="status">     
   <tr align="center">
      <td>${fn:length(pointList) - status.index}</td>
      <td>${mypointlist.pointDate}</td>  
 
      <c:choose>
       	<c:when test="${mypointlist.pointCase eq '1' || mypointlist.pointCase eq '3'}">
            <td><b><font color="#004d8e">+${mypointlist.incomePoint}</font></b></td>
        </c:when>
       	<c:when test="${mypointlist.pointCase eq '2' || mypointlist.pointCase eq '4'}">
    	  <td><b><font color="#8e003c">-${mypointlist.outgoPoint}</font></b></td>
		</c:when>
		</c:choose>	

	  <td>
      <c:choose>
      	<c:when test="${mypointlist.pointCase eq '1'}">
      	<b>포인트 충전</b>
      	</c:when>
      	<c:when test="${mypointlist.pointCase eq '2'}">
      	후원을 했습니다. (<a href="${contextPath}/board/viewArticle.do?articleNO=${mypointlist.boardCode}">후원 작품</a>)
      	</c:when>
      	<c:when test="${mypointlist.pointCase eq '3'}">
      	후원을 받았습니다. (<a href="${contextPath}/board/viewArticle.do?articleNO=${mypointlist.boardCode}">후원 받은 작품</a>)
      	</c:when>
      	<c:when test="${mypointlist.pointCase eq '4'}">
      	<b>포인트 출금</b>
      	</c:when>
      </c:choose>
      </td>
      <!--  <td>${mypointlist.pointCase}</td>-->
	  <!--  <td>${mypointlist.boardCode}</td>-->
      <!--  <td>${mypointlist.donator}</td>-->
        <td>${mypointlist.currentPoint}</td>
  
    </tr>
  </c:forEach>   
</table>
</tbody>
<p>
</body>
</html>
