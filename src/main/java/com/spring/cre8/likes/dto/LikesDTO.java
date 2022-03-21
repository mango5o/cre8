package com.spring.cre8.likes.dto;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component("LikesDTO")
public class LikesDTO {
	private String likesId; // 좋아요 누른 사람의 ID
	private int articleNO; // 좋아요 누른 글
	private Date likesDate; // 좋아요 누른 시간
	private int categoryNO; // 좋아요 누른 글의 카테고리 번호
	private String boardTitle; // 좋아요 누른 글의 제목
	private String boardWriter; // 좋아요 누른 글의 작성자

	public LikesDTO() {
	}

	public LikesDTO(String likesId, int articleNO, Date likesDate, int categoryNO, String boardTitle,
			String boardWriter) {
		this.likesId = likesId;
		this.articleNO = articleNO;
		this.likesDate = likesDate;
		this.categoryNO = categoryNO;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
	}

	public String getLikesId() {
		return likesId;
	}

	public void setLikesId(String likesId) {
		this.likesId = likesId;
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public Date getLikesDate() {
		return likesDate;
	}

	public void setLikesDate(Date likesDate) {
		this.likesDate = likesDate;
	}

	public int getCategoryNO() {
		return categoryNO;
	}

	public void setCategoryNO(int categoryNO) {
		this.categoryNO = categoryNO;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}






}
