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
<title>작품 후원</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
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
	width:150px;
	font-size: 18px;
	color: gray;
	display: inline-block;
	text-align: left;
	line-height:30px;
	padding: 5px;	
}

._donateBox {
	width:320px;
	font-size: 18px;
	color: gray;
	display: inline-block;
	text-align: center;
	font-weight:bold;	
	line-height:30px;
	padding: 5px;
		
}





.infotb {
	width:600px;
	font-size: 18px;
	text-align: center;
	color: gray;
	display: inline-block;
	line-height:30px;
	padding: 5px;	
	border: solid 1px #ccc;
	
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
	width: 100px;
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

<body>
<c:choose>
          <c:when test="${isLogOn != true  || member == null}">
          <h1>잘못된 접근입니다!</h1>
          </c:when>
<c:otherwise>
<div class="mypage_outer">
<div class="box">
<li>
<div id="boxHeader">포인트 출금</div>
</li>
<hr>
<form name="frmIncome" method="post" onsubmit="return validate();" action="${contextPath}/member/myIncomePointAction.do">
<li>
<div id="mypoint">${myOutgoPoint}포인트 출금을 신청했습니다.</div>
</li>
<p>
<li>
<div class="_donateLabel">입금 금액</div>
<div class="_donateBox">
${outgoBankToWon}원
</div>
</li>
<li>
<div class="_donateLabel">계좌 정보</div>
<div class="_donateBox">${outgoBank}
</div>
</li>
<li>
<div class="_donateLabel">예금주</div>
<div class="_donateBox">${outgoName}</div>
<li>
<div class="infotb">- 관리자 확인 후 3영업일 이내에 입력한 계좌로 처리됩니다.<br>
- 3영업일 이후에도 미 처리시 이메일로 문의 해 주세요.<br>
- 예금주와 회원명 불일치 할 경우, 출금요청이 지연/거부 될 수 있습니다.
</div>
</li>
<li>
<div>
<a href="${contextPath}/member/mypage.do?id=${member.id }" class="button">마이페이지</a>
</div>
</form>
</li>
</div>
</div>
</c:otherwise>
</c:choose>  	
</body>
</html>