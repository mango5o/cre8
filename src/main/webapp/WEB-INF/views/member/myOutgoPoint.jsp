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
<script>
$(function () {
$("#outgoPoint").change(function(){
	var pointToWon = $('#outgoPoint').val();
	var result = Math.round(pointToWon-(pointToWon*0.1));
	
	$("#pointToWon").val(result);
	});
});

function validate() {
	var myPoint = parseFloat(document.getElementById("myPoint").value);
    var outgoPoint = parseFloat(document.getElementById("outgoPoint").value);
    
    if(outgoPoint > myPoint) {
        alert("보유한 포인트가 충분하지 않습니다. 다시 확인해 주세요.");
        return false;
    }

    if(outgoPoint <= 0) {
        alert("출금할 포인트를 다시 확인해 주세요.");
        return false;
    } 
   
    if(outgoBackAccount.value=="") {
        alert("계좌번호를 입력해 주세요");
        outgoBankAccount.focus();
        return false;
    }
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


</style>

</head>
<body>
<c:choose>
          <c:when test="${isLogOn != true  || member == null}">
          <h1>잘못된 접근입니다!</h1>
          </c:when>
<c:otherwise>
<div class="mypage_outer">
<div class="box">
<form name="frmOutgo" method="post" onsubmit="return validate();" action="${contextPath}/member/myOutgoPointAction.do">
<li>
<div id="boxHeader">포인트 출금</div>
</li>
<hr>
<li>
<div id="_mypointlable">나의 포인트&nbsp;&nbsp;${mypoint} 점</div>
<a href="${contextPath}/member/mypoint.do?id=${memberOne.id }" class="button">포인트 관리</a>
</li>
<p>
<input type="hidden" name="myPoint" id="myPoint" value="${mypoint}" size="10" readonly>
<input type="hidden" name="outgoID" value="${member.id}" size="10" readonly>
<input type="hidden" name="outgoName" value="${member.name}" size="10" readonly>
<li>
<div class="_donateLabel">출금할 포인트</div>
<div class="_donateBox">
<input type="text" class="donateBox" name="outgoPoint" id="outgoPoint" value="" size="10" required>
</div>
</li>
<li>
<div class="_donateLabel">입금 금액</div>
<div class="_donateBox">
<input type="text" class="donateBox" name="pointToWon" id="pointToWon" value="" size="10"> 원
</div>
</li>
<li>
<div class="_donateLabel">입금받으실 계좌 
</div>
<div class="_donateBox">
</div>
</li>
<li>
<div class="_donateLabel">
<select name="outgoBank" class="donateAccountSelectBox" id="outgoBank" required>
	<option value="" selected disabled hidden>==은행선택==</option>
    <option value="신한">신한은행</option>
    <option value="국민">국민은행</option>
    <option value="우체국">우체국</option>
    <option value="농협">NH농협은행</option>
    <option value="농협지역">농협(단위)</option>
</select></div>
<div class="_donateBox">
<input type="text" class="donateAccountBox" name="outgoBankAccount" id="outgoBankAccount" value="" size="30" required>
</div>
</li>
<li>
<div class="infotb">※ 포인트 → 출금 시 10%의 수수료가 공제됩니다.<br>※ 회원 실명과 예금주가 일치되어야 출금요청이 처리됩니다.
</div>
</li>
<li>
<div>
<input type="submit" class="button" value="결제하기">&nbsp;
<input type="reset" class="button" value="다시입력">
</div>
</form>
</li>
</div>
</div>

</c:otherwise>
</c:choose>  	
</body>
</html>