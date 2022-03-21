<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newLineChar", "\r\n"); %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
   <meta charset="UTF-8">
   <title>글보기</title>
<link rel='stylesheet' href='${contextPath}/resources/css/cre8_viewBoard.css'>   
<script>
function fn_modify_article(){
	 action="${contextPath}/board/modArticleView.do?articleNO=${article.articleNO}";
	 submit();
}
</script>
</head>
<body>
<table class="viewTB">
<tbody>
<tr>
<td id="_titleID"><b>작품명</b></td>
<td colspan="2" id="_left">${article.title}</td>
</tr>
<tr>
<td id="_titleID"><b>작가</b></td>
<td id="_left">${article.nickname} (${article.id})</td>
<td id="_right">${article.writeDate}</td>
</tr>
<tr>
<td colspan="3">
<c:choose> 
	  <c:when test="${not empty article.imageFileName && article.imageFileName!='null' }">
	   <c:set var="filename" value="${article.imageFileName}" />
 		<!-- 확장자가 대문자일경우를 고려하여 소문자로 파일을 변경한다. -->
 		
 		<c:set var="fileNm" value="${fn:toLowerCase(filename)}" />
 		<!-- 문자열들을 .으로 split 한후 c:forTokens을이용하여 문자열들을 iterator한다 -->
 
 		<c:forTokens var="token" items="${fileNm}" delims="." varStatus="status">
 		<!-- 파일명중간에 "." 이 존재할수도 있으니 항상 status.last(마지막번째) 를 실행해주어야 한다 -->
  		<c:if test="${status.last}">
   		<c:choose>
    		<c:when test="${token eq 'jpg' || token eq 'png'}">
     		<!-- ${filename} 그림파일  -->
     	 	<img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" id="preview" style="max-width: 800px; height: auto;"/><br>
    		</c:when>
    	<c:when test="${token eq 'mp3' || token eq 'wav'}">
     		<!-- ${filename} 음악파일  -->
     		<audio controls> 
     		<source src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" type="audio/mp3">
     		<source src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" type="audio/wav">
     		음악 재생을 위해서는 HTML5가 지원되는 최신 브라우저를 사용해주세요.</audio> 
    	</c:when>
    	<c:when test="${token eq 'mp4' || token eq 'avi'}">
	    	<video style="max-width:800px" controls>
  			<source src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" type="video/mp4">동영상 재생을 위해서는 HTML5가 지원되는 최신 브라우저를 사용해주세요.</video>  
    	</c:when>
   		</c:choose>
  		</c:if>
 		</c:forTokens>
 </c:when>
</c:choose>
<p>
<div id="_left">${fn:replace(article.content, newLineChar, "<br/>")}</div>
<br>&nbsp;
<div class="_likesBox">
<c:choose>
          <c:when test="${isLogOn != true  || member == null}">
       	  ♡ 좋아요<br>
          </c:when>
<c:otherwise>
		<a href="${contextPath}/board/likes.do?articleNO=${article.articleNO}&id=${member.id}">
		<c:choose>
        <c:when test="${likes == 1}">♥ </c:when>
		<c:otherwise>♡ </c:otherwise>
		</c:choose>
		좋아요</a>
</c:otherwise>
</c:choose>
<b>${countLikes}</b>
</div>
<c:if test="${isLogOn != true  || member == null}">
<p>좋아요 기능은 회원에게만 제공합니다.
</c:if>
<p>
<c:if test="${member.id != article.id }">
<a href="${contextPath}/member/donate.do?articleNO=${article.articleNO}&id=${member.id}" class="donateButton">이 작품을 후원</a>
</c:if>
</p>
</td>
</tr>
</tbody>
</table>

<table class="viewTB">
<tr>
</tr>
<tr>
<c:forEach  var="commentList" items="${commentsList }" varStatus="commentsNum" >
     <tr>
	<td id="_commentID">${commentList.commentNickname}</td>
	<td id="_comment">${commentList.commentContent}</td>
		<td>
		<c:if test="${member.id == commentList.commentId }">
		<form name="deleteCommentform" method="POST" action="${contextPath}/board/commentDelete.do">
			<input type="hidden" name="commentNO" value="${commentList.commentNO}" readonly>	
			<input type="hidden" name="articleNO" value="${article.articleNO}" readonly>	
			<input type="submit" value="X">
		</form>	
		</c:if>
		</td>
	<td id="_commentDate">${commentList.commentDate}</td>
	</tr>
	</c:forEach>
<c:choose>
<c:when test="${isLogOn != true  || member == null}">
<tr>
<td colspan=4><p><b>댓글 작성은 회원만 가능합니다.</b></td>
</tr>
</c:when>
<c:otherwise>
<tr>
<td align="left">
<!-- 댓글창에 ID는 보내지 않는다. -->
<input type="text" class="commenttxt" size="10" name="commentId" value="${member.nickname}" readonly>
</td>
<td align="left">
<form name="frmComments" method="POST" action="${contextPath}/board/comments.do?articleNO=${article.articleNO}&id=${member.id}">
<input type="hidden" name="commentId" value="${member.id}" readonly>
<input type="text" class="commenttxt" size="65" maxlength="100" name="commentContent"/>
</td>
<td colspan=2>
<a href="#" onclick="javascript:document.frmComments.submit();" class="button">댓글</a>
</form>
</td>
</tr>
</c:otherwise>
</c:choose>
</table>

<p>
<c:if test="${member.id == article.id }">
<form name="frmRemoveArticle" method="POST" action="${contextPath}/board/removeArticle.do" style='display:inline'>
<input type="hidden" name="articleNO" value=${article.articleNO}><input type="hidden" name="categoryNO" value=${article.categoryNO}>
<a href="${contextPath}/board/modArticleView.do?articleNO=${article.articleNO}" class="button">수정</a>
<a href="#" onclick="javascript:document.frmRemoveArticle.submit();" class="button">삭제</a>
</form>
</c:if>
<a href="${contextPath}/board/listArticles.do?category=${article.categoryNO}" class="button">목록으로</a>
<p>
<!--  
개발확인용 변수들<br>
글번호 : ${article.articleNO}
카테고리번호 : ${article.categoryNO}
글 활성화 상태 : ${article.usage}
첨부파일명 : ${article.imageFileName}
-->
</body>
</html>