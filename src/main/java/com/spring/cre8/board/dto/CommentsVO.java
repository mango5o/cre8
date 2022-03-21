package com.spring.cre8.board.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("commentsVO")
public class CommentsVO {

	private int commentNO; // 댓글 번호
	private int articleNO; // 댓글 원글번호
	private String commentId; // 작성자
	private String commentNickname; // 작성자
	private String commentContent; // 내용
	private Date  commentDate; // 글쓴 시간
	private int commentUsage; // 삭제판별

	
	public CommentsVO() {
		System.out.println("CommentsVO 생성자");
	}


	public int getCommentNO() {
		return commentNO;
	}


	public void setCommentNO(int commentNO) {
		this.commentNO = commentNO;
	}


	public int getArticleNO() {
		return articleNO;
	}


	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}


	public String getCommentId() {
		return commentId;
	}


	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}


	public String getCommentNickname() {
		return commentNickname;
	}


	public void setCommentNickname(String commentNickname) {
		this.commentNickname = commentNickname;
	}


	public String getCommentContent() {
		return commentContent;
	}


	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}


	public Date getCommentDate() {
		return commentDate;
	}


	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}


	public int getCommentUsage() {
		return commentUsage;
	}


	public void setCommentUsage(int commentUsage) {
		this.commentUsage = commentUsage;
	}

	
}
