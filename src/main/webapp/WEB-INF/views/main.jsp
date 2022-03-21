<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel='stylesheet' href='${contextPath}/resources/css/cre8_main.css'>
</head>
<body>
	<div class="searchOuter" id="searchSpace">
		<div class="searchInner">
			<h1>
			글, 그림, 사진, 음악, 영상<br>무한(∞)의 아이디어를 지금 만나보세요
			</h1>
			<br>

			<form name="frmSearch" action="${contextPath}/board/search.do"
				METHOD="POST">
				<input type="text" name="keyword" id="searchBox" value="" placeholder=" 크리에잇에서 작품을 찾기"> <input
					type="submit" id="searchButtom" value="검색">
			</form>
		</div>
	</div>
	<h1>인기 게시물</h1>


	<div class="gallery" id="article1box">
		<ul>
			<c:choose>
				<c:when test="${articlesList1 != null}">
					<c:forEach var="article1" items="${articlesList1}" begin="0"
						end="3" step="1" varStatus="status">
						<c:choose>
							<c:when
								test="${not empty article1.imageFileName && article1.imageFileName!='null' }">

								<li><a
									href="${contextPath}/board/viewArticle.do?articleNO=${article1.articleNO}">
										<span class="thumb"><img
											src="${contextPath}/download.do?articleNO=${article1.articleNO}&imageFileName=${article1.imageFileName}"
											id="preview"
											style="border: 0px; max-width: 300px; max-height: 200px;" /></span><p>
										${article1.title }
								</a><br>${article1.nickname }<br>${article1.writeDate}<br><span class="heart">♥</span>
									${article1.likeCount}</li>

							</c:when>
							<c:otherwise>
								<!-- 이미지 파일이 없을 경우 -->
								<li><a
									href="${contextPath}/board/viewArticle.do?articleNO=${article1.articleNO}">
										<span class="thumb"><img
											src="${contextPath}/resources/image/NO_IMAGE.jpg"
											id="preview"
											style="border: 0px; max-width: 300px; max-height: 200px;" /></span><p>
										${article1.title }
								</a><br>${article1.nickname }<br>${article1.writeDate}<br><span class="heart">♥</span>
									${article1.likeCount}</li>

							</c:otherwise>
						</c:choose>
					</c:forEach>

				</c:when>

			</c:choose>
		</ul>
		<div class="gallery_bottom"><a href="${contextPath}/board/listArticles.do?category=1">사진작품 더 보기 ▶</a></div>
	</div>


	<div class="outer" id="article2and3out">
		<div class="inner" id="article2box">
			<div class="innerbox" id="article2box">

				<c:choose>
					<c:when test="${articlesList2 != null}">
						<c:forEach var="article2" items="${articlesList2}" begin="0"
							end="3" step="1" varStatus="status">
							<li><span class="listArticleTitle"><a
									href="${contextPath}/board/viewArticle.do?articleNO=${article2.articleNO}">${article2.title }</a></span>
								<span class="listArticleWriter">${article2.nickname }</span> <span
								class="listArticleDate">${article2.writeDate}</span> <span
								class="listLikes"><span class="heart">♥</span> ${article2.likeCount}</span></li>
						</c:forEach>
					</c:when>
				</c:choose>
				<span class="listBottom"><a href="${contextPath}/board/listArticles.do?category=2">음악작품 더 보기 ▶</a></span>
			</div>
		</div>

		<div class="inner" id="article3box">
			<div class="innerbox" id="article3box">

				<c:choose>
					<c:when test="${articlesList3 != null}">
						<c:forEach var="article3" items="${articlesList3}" begin="0"
							end="3" step="1" varStatus="status">
							<li><span class="listArticleTitle"><a
									href="${contextPath}/board/viewArticle.do?articleNO=${article3.articleNO}">${article3.title }</a></span>
								<span class="listArticleWriter">${article3.nickname }</span> <span
								class="listArticleDate">${article3.writeDate}</span> <span
								class="listLikes"><span class="heart">♥</span> ${article3.likeCount}</span></li>
						</c:forEach>
					</c:when>
				</c:choose>
				<span class="listBottom"><a href="${contextPath}/board/listArticles.do?category=3">글 작품 더 보기 ▶</a></span>
			</div>
		</div>
	</div>


	<div class="gallery" id="article4box">
		<ul>
			<c:choose>
				<c:when test="${articlesList4 != null}">
					<c:forEach var="article4" items="${articlesList4}" begin="0"
						end="3" step="1" varStatus="status">
						<c:choose>
							<c:when
								test="${not empty article4.imageFileName && article4.imageFileName!='null' }">

								<li><a
									href="${contextPath}/board/viewArticle.do?articleNO=${article4.articleNO}">
										<span class="thumb"><img
											src="${contextPath}/download.do?articleNO=${article4.articleNO}&imageFileName=${article4.imageFileName}"
											id="preview"
											style="border: 0px; max-width: 300px; height: 200px;" /></span><p>
										${article4.title }
								</a><br>${article4.nickname }<br>${article4.writeDate}<br><span class="heart">♥</span>
									${article4.likeCount}</li>

							</c:when>
							<c:otherwise>
								<!-- 이미지 파일이 없을 경우 -->
								<li><a
									href="${contextPath}/board/viewArticle.do?articleNO=${article4.articleNO}">
										<span class="thumb"><img
											src="${contextPath}/resources/image/NO_IMAGE.jpg"
											id="preview"
											style="border: 0px; max-width: 300px; max-height: 200px;" /></span><p>
										${article4.title }
								</a><br>${article4.nickname }<br>${article4.writeDate}<br><span class="heart">♥</span>
									${article4.likeCount}</li>

							</c:otherwise>
						</c:choose>
					</c:forEach>

				</c:when>

			</c:choose>
		</ul>
		<div class="gallery_bottom"><a href="${contextPath}/board/listArticles.do?category=4">그림작품 더 보기 ▶</a></div>
	</div>

	<div class="outer" id="article5">
		<div class="gallery" id="article5box">
			<ul>
				<c:choose>
					<c:when test="${articlesList5 != null}">
						<c:forEach var="article5" items="${articlesList5}" begin="0"
							end="3" step="1" varStatus="status">
							<c:choose>
								<c:when
									test="${not empty article5.imageFileName && article5.imageFileName!='null' }">

									<li><a
										href="${contextPath}/board/viewArticle.do?articleNO=${article5.articleNO}">
											<span class="thumb"> <video source
													src="${contextPath}/download.do?articleNO=${article5.articleNO}&imageFileName=${article5.imageFileName}"
													id="preview"
													style="border: 0px; max-width: 300px; max-height: 200px;"
													type="vidio/mp4"></video></span><p> ${article5.title }
									</a><br>${article5.nickname }<br>${article5.writeDate}<br><span class="heart">♥</span>
										${article5.likeCount}</li>

								</c:when>
								<c:otherwise>
									<!-- 이미지 파일이 없을 경우 -->
									<li><a
										href="${contextPath}/board/viewArticle.do?articleNO=${article5.articleNO}">
											<span class="thumb"><img
												src="${contextPath}/resources/image/NO_IMAGE.jpg"
												id="preview"
												style="border: 0px; max-width: 300px; max-height: 200px;" /></span><p>
											${article5.title }
									</a><br>${article5.nickname }<br>${article5.writeDate}<br><span class="heart">♥</span>
										${article5.likeCount}</li>

								</c:otherwise>
							</c:choose>
						</c:forEach>

					</c:when>

				</c:choose>
			</ul>
			<div class="gallery_bottom"><a href="${contextPath}/board/listArticles.do?category=5">영상작품 더 보기 ▶</a></div>
		</div>
	</div>
</body>
</html>