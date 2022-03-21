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
<title>회원 가입창</title>
<style>
   .text_center{
     text-align:center;
   }
</style>
<script>
function validate() {
    var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
    var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    // 이메일이 적합한지 검사할 정규식

    var id = document.getElementById("id");
    var pwd = document.getElementById("pwd");
    var nickname = document.getElementById("nickname");
    var email = document.getElementById("email");

    
    if(!check(re, pwd,"패스워드는 4~12자의 영문 대소문자와 숫자로 입력해주세요.")) {
        return false;
    }    
    
    if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
        return false;
    }
   
    alert("회원가입이 완료되었습니다.");
}

function winopen(){
		window.open("joinIdCheck.jsp?userid="+document.form.id.value,"","width=500, height=300");

}

function check(re, what, message) {
    if(re.test(what.value)) {
        return true;
    }
    alert(message);
    what.value = "";
    what.focus();
}
</script>

<link rel='stylesheet' href='${contextPath}/resources/css/cre8_member.css'>


</head>
<body>
<div class="mypage_outer">
<div class="box">
<li>
<div id="boxHeader">회원 가입</div>
</li>
<hr>
<form method="post" onsubmit="return validate();" action="${contextPath}/member/addMember.do" name="form" onsubmit="joinMember();">
<ul>
<li>
<span class="boxlabel">아이디</span>
<input type="text" class="inputBox" id="id" name="id" required placeholder="사용할 ID를 입력하세요.">
</li>
</ul>
<ul>
<li>
<span class="boxlabel">비밀번호</span>
<input type="password" class="inputBox" id="pwd" name="pwd" required placeholder="영문 대소문자와 숫자 4~12자">
</li>
</ul>
<ul>
<li>
<span class="boxlabel">이름</span>
<input type="text" class="inputBox" id="name" name="name" required placeholder="이름을 입력하세요.">
</li>
</ul>
<ul>
<li>
<span class="boxlabel">닉네임</span>
<input type="text" class="inputBox" id="nickname" name="nickname" required placeholder="사용할 닉네임을 입력하세요.">
</li>
</ul>
<ul>
<li>
<span class="boxlabel">이메일</span>
<input type="text" class="inputBox" id="email" name="email" required placeholder="이메일을 입력하세요.">
</li>
</ul>
<input type="submit" class="button" value="가입하기">&nbsp;<input type="reset" class="button" value="다시입력">
</form>
	</div>
	<div>
</body>
