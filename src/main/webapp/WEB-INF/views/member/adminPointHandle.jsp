<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    
</script>
<html>
<head>
<meta charset=UTF-8">
<title>관리자 모드 포인트 핸들러</title>
<script type="text/javascript"> 
function goReplace(str) { 
	location.replace(str); 
	} 
	
function historyBack() { 
	history.back(); 
	}
	
	function reset(){
		$("#frmPointCount").val('');

	}
</script>
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

#mypoint {
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
	border: solid 3px gray;;
	padding: 10px;	
	line-height:30px;
}


._donateLabel {
	width:200px;
	font-size: 18px;
	color: gray;
	display: inline-block;
	text-align: left;
	line-height:30px;
	padding: 5px;	
}

._donateBox {
	width:300px;
	font-size: 18px;
	color: gray;
	display: inline-block;
	text-align: left;
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

</style>
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

<div class="mypage_outer">
<div class="box">
<li>
<div id="boxHeader">포인트 <font style=color:#6a6a6a;>조회</font> (관리자 모드)</div>
</li>
<hr>
<form name="frmPointCount" id="frmPointCount" method="post" action="${contextPath}/member/adminPointAction.do">
<input type="hidden" name="parameter" value="1" size="10">
<li>
<div class="_donateLabel">조회할 회원 ID</div>
<div class="_donateBox">
<input type="text" class="donateBox" name="memberId" id="memberId" value="${memberId}" size="10" required>
</div>
</li>
<li>
<div class="_donateLabel">포인트</div>
<div class="_donateBox">
<input type="text" class="donateBox" name="resultPoint" id="resultPoint" value="${memberPoint}" size="10">
</div>
</li>
<li>
<div>
<p>
<input type="submit" class="button" value="조회하기">&nbsp;
<input type="button" class="button" value="다시입력" onclick="goReplace('${contextPath}/member/adminPointHandle.do')">
<input type="button" class="button" value="관리자 게시판" onclick="goReplace('${contextPath}/board/listAdminArticles.do')">
</div>
</form>
</li>
</div>
</div>
<div class="mypage_outer">
<div class="box">
<li>
<div id="boxHeader">포인트 <font style=color:#004d8e;>충전</font> (관리자 모드)</div>
</li>
<hr>
<form name="frmPointIncome" method="post"  action="${contextPath}/member/adminPointAction.do">
<input type="hidden" name="parameter" value="2" size="10">
<li>
<div class="_donateLabel">충전할 회원 ID</div>
<div class="_donateBox">
<input type="text" class="donateBox" name="pointIncomeId" id="pointIncomeId" value="${memberId}" size="10" required>
</div>
</li>
<li>
<div class="_donateLabel">포인트</div>
<div class="_donateBox">
<input type="text" class="donateBox" name="incomePoint" id="incomePoint" value="" size="10" required>
</div>
</li>
<li>
<div>
<p>
<input type="submit" class="button" value="충전하기">&nbsp;
<input type="button" class="button" value="다시입력" onclick="goReplace('${contextPath}/member/adminPointHandle.do')">
<input type="button" class="button" value="관리자 게시판" onclick="goReplace('${contextPath}/board/listAdminArticles.do')">
</div>
</form>
</li>
</div>
</div>
<div class="mypage_outer">
<div class="box">
<li>
<div id="boxHeader">포인트 <font style=color:#8e003c;>인출</font> (관리자 모드)</div>
</li>
<hr>
<form name="frmPointOutgo" method="post"  action="${contextPath}/member/adminPointAction.do">
<input type="hidden" name="parameter" value="3" size="10">
<li>
<div class="_donateLabel">인출할 회원 ID</div>
<div class="_donateBox">
<input type="text" class="donateBox" name="pointOutgoId" id="pointOutgoId" value="${memberId}" size="10" required>
</div>
</li>
<li>
<div class="_donateLabel">인출할 포인트</div>
<div class="_donateBox">
<input type="text" class="donateBox" name="outgoPoint" id="outgoPoint" value="" size="10" required>
</div>
</li>
<li>
<div>
<p>
<input type="submit" class="button" value="인출하기">&nbsp;
<input type="button" class="button" value="다시입력" onclick="goReplace('${contextPath}/member/adminPointHandle.do')">
<input type="button" class="button" value="관리자 게시판" onclick="goReplace('${contextPath}/board/listAdminArticles.do')">

</div>
</form>
</li>
</div>
</div>
</c:otherwise>
</c:choose>  	
</body>
</html>
