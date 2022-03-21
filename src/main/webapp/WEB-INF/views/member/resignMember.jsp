<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    


<html>
<head>
<meta charset=UTF-8">
<title>회원 탈퇴</title>
</head>
<style>
.mypage_outer {
	margin-top:50px;
	margin-bottom:50px;
}

.box li {
	list-style: none;
	display: inline-table;
	padding-top: 3px;
	padding-bottom: 3px;

}

.box {
	position: relative;
	border: solid 1px #ccc;
	width: 700px;
	margin: auto;
	padding: 20px 20px; /* 상하 좌우 */
}


#boxHeader {
	color: black;
	font-size: 35px;
	font-weight:bold;
	display: inline-block;
	text-align: center;
}

#_mypointlable {
	font-size: 30px;
	font-weight:bold;
	display: inline-block;
	text-align: center;
	padding-top: 10px; 
	padding-right: 20px; 
	
}


.panal {
	font-size: 18px;
	font-weight:bold;
	color: gray;
	display: inline-block;
	text-align: center;
	width: 120px;
	border-radius: 20px 20px 20px 20px;
	border: solid 3px gray;
	padding: 10px;	
	line-height:30px;
}


._donateLabel {
	width:200px;
	font-size: 18px;
	color: gray;
	display: inline-block;
	text-align: center;
	line-height:30px;
	padding: 5px;	
}

._donateBox {
	width:300px;
	font-size: 18px;
	color: gray;
	display: inline-block;
	text-align: center;
	line-height:30px;
	padding: 5px;		
}


.infotb {
	width:500px;
	font-size: 18px;
	text-align: center;
	color: gray;
	display: inline-block;
	line-height:30px;
	padding: 5px;	
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

.donateBox {
	background: white;
	border: solid 1px #ccc;
	padding: 5px 5px 5px 5px;
	text-align:right;
	font-size: 13pt;
	width: 180px;
	height: 30px;
}

._donatetitle1 {
	background: white;
	padding: 5px 5px 5px 5px;
	font-weight:bold;	
	text-align:left;
	font-size: 25pt;
	text-decoration: underline;
	width: 550px;
	height: 40px;
}

._donatetitle2 {
	background: white;
	padding: 5px 5px 5px 5px;
	font-weight:bold;	
	text-align:left;
	font-size: 18pt;
	width: 550px;
	height: 40px;
}

._donatetitle3 {
	background: white;
	padding: 5px 5px 5px 5px;
	font-weight:bold;	
	text-align:center;
	width: 550px;
	height: 20px;
}


.donateAccountBox {
	background: white;
	border: solid 1px #ccc;
	padding: 5px 5px 5px 5px;
	font-size: 13pt;
	width: 250px;
	height: 30px;
}

.donateAccountSelectBox {
	background: white;
	border: solid 1px #ccc;
	padding: 5px 5px 5px 5px;
	font-size: 13pt;
	width: 150px;
	height: 40px;
}


.donateNotice {
	position: relative;
	border: solid 1px #ccc;
	width: 500px;
	margin: auto;
	text-align:left;
	padding: 20px 20px; /* 상하 좌우 */
}

</style>
<body>
<c:choose>
          <c:when test="${isLogOn != true  || member == null}">
          <h1>잘못된 접근입니다!</h1>
          </c:when>
<c:otherwise>
<div class="mypage_outer">
<div class="box">
<li>
<div id="boxHeader">회원 탈퇴</div>
</li>
<hr>
<li>
<div class="_donatetitle1">${member.nickname } 님</div>
</li>
<li>
<div class="_donatetitle2">크리에잇을 이용해 주셔서 감사합니다.</div>
</li>
<p>
<li>
<div class="donateNotice"><b>※ 주의</b><p>
- 회원 탈퇴를 하시면 크리에잇의 서비스를 이용할 수 없습니다.<br>
- 회원 탈퇴 시 복구는 불가능하며, 잔여 포인트는 소멸합니다.<br>
- 회원 개인정보는 1년간 보관 후 법령에 따라 지체없이 파기됩니다.<br>
- 탈퇴한 아이디로는 재 가입이 불가능합니다. 
</div>
</li>
<li>
<div class="infotb">
<form name="resign" method="post" action="${contextPath}/member/resignMemberAction.do" style='display:inline;'>
<input type="hidden" name="id" value="${member.id}" size="10" readonly>
<input type="submit" class="button" value="회원탈퇴">
<a href="${contextPath}/member/mypage.do?id=${member.id }" class="button">마이페이지</a>
</form>
</div>
</li>
</div>
</div>
</c:otherwise>
</c:choose>  	
</body>
</html>
