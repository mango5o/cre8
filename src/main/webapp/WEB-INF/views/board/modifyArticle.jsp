<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(document).ready(function (){
	var cateCheck  =  ${article.categoryNO };
	// alert(cateCheck);
	// 1:사진, 2:음악, 3:글, 4:그림, 5:영상
	if(cateCheck==1){
	$("#categoryNO option:eq(1)").attr("selected", "selected");
	} else if(cateCheck==2){
	$("#categoryNO option:eq(2)").attr("selected", "selected");	
	} else if(cateCheck==3){
	$("#categoryNO option:eq(3)").attr("selected", "selected");	
	} else if(cateCheck==4){
	$("#categoryNO option:eq(4)").attr("selected", "selected");	
	} else if(cateCheck==5){
	$("#categoryNO option:eq(5)").attr("selected", "selected");	
	}
});

  function modifyArticle(obj){
	  obj.action="${contextPath}/board/modArticle.do";
	  obj.submit();
  }
  function readURL(input) {
      if (input.files && input.files[0]) {
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        $('#preview').attr('src', e.target.result);
          }
         reader.readAsDataURL(input.files[0]);
      }
  }  
  
  function backToList(obj){
    obj.action="${contextPath}/board/listArticles.do?category=${article.categoryNO}";
    obj.submit();
  }
  
  var cnt=1;
  function fn_addFile(){
	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
	  cnt++;
  }  
</script>

 <title>글쓰기창</title>
 <link rel='stylesheet' href='${contextPath}/resources/css/cre8_write.css'>
 
</head>
<body>
<c:choose>
          <c:when test="${isLogOn != true  || member == null}">
          <h1>잘못된 접근입니다!</h1>
          </c:when>
<c:otherwise>
<div class="mypage_outer">
<div class="box">
<li>
<div id="boxHeader">작품 수정</div>
</li>
<hr>
<form name="articleForm" method="post" action="${contextPath}/board/addNewArticle.do" enctype="multipart/form-data">
<input type="hidden" name="articleNO" value="${article.articleNO}"/> 
<table class="viewTB">
<tbody>
    	  <tr>
    	  	<td width="100px">카테고리</td>
    	  	<td class="_left">
    	  	<select id = "categoryNO" name="categoryNO">
			<option selected disabled>카테고리 선택</option>
			<option value="1">사진</option>
			<option value="2">음악</option>
			<option value="3">글</option>
			<option value="4">그림</option>
			<option value="5">영상</option>
			</select>
			</td>
			</tr>
			<tr> 
			<td>작가</td>
			<td class="_left">${member.nickname } (${member.id })</td>
			<input type="hidden" value="${member.name }" readonly/>
			</tr>
	      <tr>
			   <td colspan="2"><input type="text" class="_contentTitle" name="title" required maxlength="50" value="${article.title }"></textarea></td>
		 </tr>
	 		<tr>
				<td colspan=2>
				<textarea class="_contentForm" name="content" required wrap="virtual">${article.content}</textarea> </td>
     		</tr>
     		<tr>
     		<td>첨부파일</td>
     		<td>${article.imageFileName}</td>
     		</tr>
     		<tr>
			  <td>새 첨부파일</td>
			  <td><input type="file" name="imageFileName" accept=".gif, .jpg, .png, .mp3, .wav, .mp4, .avi" onchange="readURL(this);" /></td>
			  <tr>
			  <td>미리보기</td>  
			  <td><img id="preview" src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" style="max-width: 100px; height: auto;"/></td>
			  </tr>
	    <tr>
	      <td colspan="2">
	       <input type="button" class="button" value="수정하기" onClick="modifyArticle(articleForm)"/>
	       <input type="button" class="button" value="목록보기"onClick="backToList(this.form)" />
	      </td>
     </tr>
     </tbody>
    </table>
  </form>
</div>
</div>





  
  
  </c:otherwise>
  </c:choose>
</body>
</html>
