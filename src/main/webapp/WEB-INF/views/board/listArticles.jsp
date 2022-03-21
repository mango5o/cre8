<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<link rel='stylesheet' href='${contextPath}/resources/css/cre8_board.css'>
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
<body>
<h1>
   <c:choose>
      	<c:when test="${categoryParameter eq '1'}">
      	사진
      	</c:when>
      	<c:when test="${categoryParameter eq '2'}">
      	음악
      	</c:when>
      	<c:when test="${categoryParameter eq '3'}">
      	글
      	</c:when>
      	<c:when test="${categoryParameter eq '4'}">
      	그림
      	</c:when>
      	<c:when test="${categoryParameter eq '5'}">
      	영상
      	</c:when>
      	<c:when test="${categoryParameter eq '0'}">
      	전체 목록
      	</c:when>
      </c:choose>
</h1>

<c:choose>
 <c:when test="${categoryParameter eq '1' || categoryParameter eq '4'}">

 <c:set var="i" value="0" />
 <c:set var="j" value="4" />
 <table border="0" align="center">
  <c:choose>
   <c:when test="${articlesList != null}">
    <c:forEach items="${articlesList}" var="article">
     <c:if test="{i%j == 0}">
      <tr>
     </c:if>
     <td>
  		<c:choose>
     	 <c:when test="${not empty article.imageFileName && article.imageFileName!='null' }">
		 <a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">
		 <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" id="preview" style="border:0px;max-width: 300px; max-height: 250px;" /><br>
		  <b>${article.title }</b></a><br>${article.nickname }<br>${article.writeDate} 
		 </c:when> 
		 <c:otherwise>
		 <a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">
		 <img src="${contextPath}/resources/image/NO_IMAGE.jpg" id="preview" style="border:0px;max-width: 300px; height: auto;" /><br>
		 ${article.title }</a><br>${article.nickname }<br>${article.writeDate} 
		 </c:otherwise>		 	
		 </c:choose>
     </td>
    <c:if test="${i%j == j-1}">
     </tr>
    </c:if> 
   <c:set var="i" value="${i+1}" />
    </c:forEach>
   </c:when>
  <c:otherwise>
   <tr>
    <td>존재하지 않습니다.</td>
   </tr>
  </c:otherwise>
  </c:choose>
 </table>
</c:when>


<c:when test="${categoryParameter eq '0'}">
<table class="viewTB">
  <tr>
     <th>글번호</th>
     <th >카테고리</th>
     <th >작성자</th>              
     <th >닉네임</th>    
     <th >제목</th>
     <th >작성일</th>
     <th >글상태</th>
  </tr>
  <tbody>
<c:choose>
  <c:when test="${articlesList ==null }" >
    <tr  height="10">
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
	   <c:choose>
      	<c:when test="${article.categoryNO eq '1'}">
      	사진
      	</c:when>
      	<c:when test="${article.categoryNO eq '2'}">
      	음악
      	</c:when>
      	<c:when test="${article.categoryNO eq '3'}">
      	글
      	</c:when>
      	<c:when test="${article.categoryNO eq '4'}">
      	그림
      	</c:when>
      	<c:when test="${article.categoryNO eq '5'}">
      	영상
      	</c:when>
      </c:choose>	
	</td>
	<td>${article.id }</td>
	<td>${article.nickname }</td>
	
	<td id="_title"><a href="${contextPath}/board/viewArticleAdmin.do?articleNO=${article.articleNO}">${article.title }</a></td>
	<td>${article.writeDate}</td> 
	<td>
	<c:choose>
      	<c:when test="${article.usage eq '0'}">
      	<b>정상</b>
      	</c:when>
      	<c:when test="${article.usage eq '1'}">
      	<font color="#8e003c"><b>삭제</b></font>
      	</c:when>
	</c:choose>	
	</td> 
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
    </tbody>
</table>
<p>
</c:when>

<c:otherwise>  	
<!-- 이외의 파라메터라면 일반 게시판으로 보여주기 -->
<table class="viewTB">
  <tr>
     <th>NO</th>            
     <th>작품명</th>
     <th>작가</th> 
     <th>등록일</th>
  </tr>
  <tbody>
<c:choose>
  <c:when test="${articlesList ==null }" >
    <tr>
      <td colspan="4">
        <b>등록된 글이 없습니다.</b>
      </td>  
    </tr>
  </c:when>
  <c:when test="${articlesList !=null }" >
   <c:forEach  var="article" items="${articlesList }" varStatus="articleNum" >
     <tr>
	<td>${fn:length(articlesList) - articleNum.index}</td>
	<td id="_title" width="60%"><a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a></td>
	<td>${article.nickname }</td>
	<td>${article.writeDate}</td> 
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</tbody>
</table>
</c:otherwise>
</c:choose>
<p>
<c:choose>
<c:when test="${isLogOn != true  || member == null}">
 </c:when>
 <c:when test="${categoryParameter == 0}">
</c:when>        
<c:otherwise>
<a href="#" onClick="javascript:fn_articleForm('${isLogOn}','${contextPath}/board/articleForm.do?category=${categoryParameter}','${contextPath}/member/loginForm.do')" class="button">작품등록</a>
<p>
</c:otherwise>
</c:choose>
</body>
</html>