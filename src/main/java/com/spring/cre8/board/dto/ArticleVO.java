package com.spring.cre8.board.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("articleVO")
public class ArticleVO {
	
	private int articleNO; // 글번호
	private int categoryNO; // 카테고리 번호
	//private int cateArticleNO; // 카테고리 내에서의 글번호
	private String id; // 작성자
	private String nickname; // 작성자
	private String title; //제목
	private String content; // 내용
	private String imageFileName; // 첨부파일
	private Date  writeDate; // 글쓴 시간
	private int usage; // 삭제판별
	private int likeCount; // 카테고리 내에서의 글번호


	public ArticleVO() {
		System.out.println("ArticleVO 생성자");
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		try {
			if(imageFileName!= null && imageFileName.length()!=0) {
				this.imageFileName = URLEncoder.encode(imageFileName,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getNickname() {
		return nickname;
	}
	
	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public int getCategoryNO() {
		return categoryNO;
	}

	public void setCategoryNO(int categoryNO) {
		this.categoryNO = categoryNO;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public int getUsage() {
		return usage;
	}

	public void setUsage(int usage) {
		this.usage = usage;
	}


}
