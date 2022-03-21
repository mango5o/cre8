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
</head>
<script type="text/javascript">
function validate() {
	
    var myPoint = parseInt(document.getElementById("myPoint").value);
    var donatePoint = parseInt(document.getElementById("donatePoint").value);
 	
    if(donatePoint > myPoint) {
        alert("보유한 포인트가 충분하지 않습니다. 다시 확인해 주세요.");
        return false;
    }

    if(donatePoint <= 0) {
        alert("기부할 포인트를 다시 확인해 주세요.");
        return false;
    }         
    // alert("포인트 기부를 처리합니다.");
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
<div id="boxHeader">후원하기</div>
</li>
<hr>
<form name="frmDonate" method="post" onsubmit="return validate();" action="${contextPath}/member/donateAction.do">
<li>
<div class="_donatetitle1"><a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title} - ${article.nickname}</a></div>
</li>
<li>
<div class="_donatetitle2">이 작품이 마음에 드셨어요?</div>
</li>
<li>
<div class="_donatetitle3">이 작품의 작가님께 포인트로 후원할 수 있어요!</div>
</li>
<p>
<input type="hidden" name="donateToId" value="${article.id }">
<input type="hidden" name="articleNO" value="${article.articleNO}">
<input type="hidden" name="donateFromId" value="${member.id}">
<input type="hidden" name="myPoint" id="myPoint" value="${mypoint}" size="10" readonly> 
<li>
<div class="_donateLabel">나의 포인트</div>
<div class="_donateBox"><b>${mypoint} 점</b>&nbsp;&nbsp;<a href="${contextPath}/member/mypoint.do?id=${memberOne.id }" class="button">포인트 관리</a>
</div>
</li>
<li>
<div class="_donateLabel">후원할 포인트</div>
<div class="_donateBox">
<input type="text" class="donateBox" name="donatePoint" id="donatePoint" value="" size="10" required>
</div>
</li>
<li>
<div class="infotb">※ 한번 후원한 포인트는 취소/환불이 불가능합니다.<br>
</div>
</li>
<li>
<div>
<input type="submit" class="button" value="후원하기">&nbsp;
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
