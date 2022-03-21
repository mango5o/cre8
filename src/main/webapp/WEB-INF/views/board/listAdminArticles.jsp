<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<link rel='stylesheet' href='${contextPath}/resources/css/cre8_board.css'>

<script>
	function fn_articleForm(isLogOn, articleForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = articleForm;
		} else {
			alert("로그인 후 글쓰기가 가능합니다.")
			location.href = loginForm + '?action=/board/articleForm.do';
		}
	}
</script>
</head>
<body>
	<h1>포인트 충전/출금 요청 게시판</h1>
	<c:choose>	          
          <c:when test="${isLogOn != true  || member == null}">
          <h1>잘못된 접근입니다!</h1>
          </c:when>
          <c:when test="${member.id ne 'admin'}">
          <h1>이 페이지는 관리자만 접근할 수 있습니다.</h1>
          </c:when>
<c:otherwise>
	<table class="viewTB">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>처리여부</th>

		</tr>
		<c:choose>
			<c:when test="${articlesList ==null }">
				<tr height="10">
					<td colspan="5">
						<p align="center">
							<b>등록된 글이 없습니다.</b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${articlesList !=null }">
				<tbody>
					<c:forEach var="article" items="${articlesList }"
						varStatus="articleNum">
						<tr>
							<td width="5%">${fn:length(articlesList) - articleNum.index}</td>
							<td width="25%" id="_title"><a
								href="${contextPath}/board/viewAdminArticle.do?articleNO=${article.articleNO}">${article.title }</a></td>
							<td width="5%">${article.id }</td>
							<td width="10%">${article.writeDate}</td>
							<td width="10%"><c:choose>
									<c:when test="${article.usage eq '0'}"><font color="#8e003c"><b>처리대기</b></font>
									</c:when>
									<c:when test="${article.usage eq '1'}"><b>처리완료</b></c:when>
								</c:choose></td>
						</tr>
					</c:forEach>
			</c:when>
		</c:choose>
		</tbody>
	</table>
	&nbsp;
	<p>
	</p>
	</c:otherwise>
	</c:choose>
</body>
</html>