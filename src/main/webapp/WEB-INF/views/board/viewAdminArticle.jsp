<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
   <meta charset="UTF-8">
   <title>글보기</title>
   <link rel='stylesheet' href='${contextPath}/resources/css/cre8_viewBoard.css'>
</head>
<body>
<c:choose>	          
          <c:when test="${isLogOn != true  || member == null}">
          <h1>잘못된 접근입니다!</h1>
          </c:when>
          <c:when test="${member.id ne 'admin'}">
          <h1>이 페이지는 관리자만 접근할 수 있습니다.</h1>
          </c:when>
<c:otherwise>

<table class="viewTB">
<tbody>
<tr>
<td><b>제목</b></td>
<td colspan=2 align='left'>${article.title}</td>
</tr>
<tr>
<td><b>작성자</b></td>
<td>${article.id}</td>
<td>${article.writeDate}</td>
</tr>
<tr>
<td colspan=3>
<div id="_left">${fn:replace(article.content, newLineChar, "<br/>")}</div></td>
</tr>
</tbody>
</table>
<p>
<a href="${contextPath}/board/listAdminArticles.do" class="button">목록으로</a>
<a href="${contextPath}/member/adminPointHandle.do" target="_blank" rel="noopener noreferrer" class="button">포인트관리</a>
<c:choose>
   <c:when test="${article.usage eq '0'}">
   <form name="frmAdminCheck" method="post" action="${contextPath}/board/adminPointArticleChecked.do" style='display:inline'>
   <input type="hidden" name="usageArticleNO" value=${article.articleNO}>
   <a href="#" class="button" onclick="javascript:document.frmAdminCheck.submit();">완료처리</a>
</form>
<p>
</c:when>
</c:choose>
</p>
</c:otherwise>
</c:choose>
</body>
</html>