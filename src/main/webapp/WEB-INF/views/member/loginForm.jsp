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
  <meta charset="UTF-8">
<title>로그인창</title>

<c:choose>
	<c:when test="${result=='loginFailed' }">
	  <script>
	    window.onload=function(){
	      alert("아이디나 비밀번호가 잘못 되었습니다. 다시 로그인 하세요!");
	    }
	  </script>
	</c:when>
</c:choose>
<link rel='stylesheet' href='${contextPath}/resources/css/cre8_login.css'>
</head>
<div class="login_outer">
    <form name="frmLogin"  action="${contextPath}/member/login.do"  method="post">
   	<div class="box">
   	<h2>LOGIN</h2>
   	<div class="loginbox">
   		<label for="userID">아이디 </label>
   		<input type="text" id="userID" name="id" placeholder="아이디" required><br>
   	</div>

   	<div class="loginbox">
   	<label for="userPwd">비밀번호 </label>
    <input type="password" id="userPwd" name="Pwd" placeholder="패스워드" required><br>
    </div>
	<div class="loginSubmit">
    <input type="submit" value="로그인"><br>
    <br><a href="${contextPath}/member/memberForm.do">회원이 아니신가요?</a>
   </div>
   </div>
   </form>
</div>
</body>
</html>
