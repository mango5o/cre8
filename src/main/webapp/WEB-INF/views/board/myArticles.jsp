<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
 <style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
  </style>
  <meta charset="UTF-8">
  <title>글목록</title>
</head>
<script>
	function fn_articleForm(isLogOn,articleForm,loginForm){
	  if(isLogOn != '' && isLogOn != 'false'){
	    location.href=articleForm;
	  }else{
	    alert("로그인 후 글쓰기가 가능합니다.")
	    location.href=loginForm+'?action=/board/articleForm.do';
	  }
	}
</script>
<style>
.viewTB {
	margin: 0 auto;
	width: 800px;
	border-collapse: collapse;
}

.viewTB tbody td {
	border-bottom: 1px solid #6a6a6a;
	padding: 10px;
	text-align: center;
}

.viewTB th {
	padding: 10px;
	background-color: #eaeaea;
	
}

#_title {
	text-align: left;
}

a.button {
	display: inline-block;
	width: 120px;
	line-height: 30px;
	text-align: center;
	background-color: #222;
	color: #fff;
	font-size: 12pt;
	text-decoration: none;
}

.button {
	width: 120px;
	height: 30px;
	border: none;
	background-color: #222;
	font-size: 12pt;
	color: #fff;
}


</style>
<body>
<h1>${member.nickname} 님의 작품</h1>

	<table class="viewTB">
	  <tr>
     <th>글번호</th>
     <th>카테고리</th>         
     <th>제목</th>
     <th>작성일</th>  
     <th>글상태</th>
  </tr>
  <tbody>
<c:choose>
  <c:when test="${articlesList ==null }" >
    <tr>
      <td colspan="4">
         <p align="center">
            <b>등록된 글이 없습니다.</b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${articlesList !=null }" >
    <c:forEach  var="article" items="${articlesList }" varStatus="articleNum" >
     <tr>
	<td>${article.articleNO}</td>
	<td>
      <c:set var="temp" value="${article.categoryNO}"/>
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
	<td id="_title"><a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a></td>
	<td>${article.writeDate}</td> 
	<td><c:choose>
									<c:when test="${article.usage eq '0'}"><b>정상</b></font>
									</c:when>
									<c:when test="${article.usage eq '1'}"><b>삭제</b></c:when>
								</c:choose></td> 
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
    </tbody>
</table>
	<p>
<a href="${contextPath}/member/mypage.do?id=${member.id }" class="button">마이페이지</a>
&nbsp;
</body>
</html>