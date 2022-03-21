<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>헤더</title>
</head>
<style>

.menu {
border-collapse: collapse;
width:100%;
height:50px;
}

.menu td{
border-collapse: collapse;

}

.logo-tb {
    text-align: right;
}

.login-bar {
    width: 58%;
    text-align: right;
}

.login-space {
    width: 10%;
}

.menu-bar {
border-collapse: collapse;
text-size: 20px;
padding-bottom : 15px;
}



.table_center {

display : table;
margin-left : auto;
margin-right : auto;
}





</style>
<body>
<table class="menu">
  <tr>
     <td class="logo-tb">
       <a href="${contextPath}/main.do"><img src="${contextPath}/resources/image/cre8_logo.png" alt="크리에잇 - 무한의 창작공간"></a>
     </td>     
     <td class="login-bar">
       <c:choose>
          <c:when test="${isLogOn == true  && member!= null}">
            <b>${member.nickname }(${member.id }) 님!</b>
            <a href="${contextPath}/member/logout.do">로그아웃</a>
     	    <a href="${contextPath}/member/mypage.do?id=${member.id }">마이페이지</a>
     	     <c:choose>
	          <c:when test="${member.id eq 'admin'}">
    			<!--  관리자 모드 작동 -->
 				<a href="${contextPath}/member/adminPointHandle.do">포인트관리</a>
 				<a href="${contextPath}/board/listAdminArticles.do">포인트게시판</a>
    			<a href="${contextPath}/member/listMembers.do">회원관리</a>
    			<a href="${contextPath}/board/listArticles.do?category=0">전체글보기</a> 
    		  </c:when> 	    
     	    </c:choose>
          </c:when>
          <c:otherwise>
	        <a href="${contextPath}/member/loginForm.do">로그인</a>
	        <a href="${contextPath}/member/memberForm.do">회원가입</a>	        
	      </c:otherwise>
	   </c:choose>
	     <a href="${contextPath}/about.do">ABOUT</a>      
     </td>
     <td class="login-space">
     </td>
  </tr>
  <tr>
<td colspan="3" class="menu-bar">
<a href="${contextPath}/board/listArticles.do?category=1">사진</a> I
<a href="${contextPath}/board/listArticles.do?category=2">음악</a> I 
<a href="${contextPath}/board/listArticles.do?category=3">글</a> I
<a href="${contextPath}/board/listArticles.do?category=4">그림</a> I
<a href="${contextPath}/board/listArticles.do?category=5">영상</a>
</td>
</tr>
</table>

</body>
</html>