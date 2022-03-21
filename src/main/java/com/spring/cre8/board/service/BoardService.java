package com.spring.cre8.board.service;

import java.util.List;
import java.util.Map;

import com.spring.cre8.board.dto.AdminArticleVO;
import com.spring.cre8.board.dto.ArticleVO;
import com.spring.cre8.board.dto.CommentsVO;

public interface BoardService {
	public List<ArticleVO> listArticles(int categoryNO) throws Exception;

	public List<ArticleVO> listHotArticles(int categoryNO) throws Exception;

	public List<ArticleVO> listArticlesById(String id) throws Exception;

	public List<ArticleVO> listArticlesByKeyword(String keyword) throws Exception;

	public List<CommentsVO> listCommentsByArticleNO(int articleNO) throws Exception;

	public List<AdminArticleVO> listAdminArticles() throws Exception;

	public int listArticlesCountById(String id) throws Exception;

	public int addNewArticle(Map articleMap) throws Exception;

	public int addComment(Map commentMap) throws Exception;

	public int deleteComment(int commentNO) throws Exception;

	
	public ArticleVO viewArticle(int articleNO) throws Exception;

	public ArticleVO viewArticleAdmin(int articleNO) throws Exception;

	public AdminArticleVO viewAdminArticle(int articleNO) throws Exception;

	// public Map viewArticle(int articleNO) throws Exception;
	public void modArticle(Map articleMap) throws Exception;

	public void removeArticle(int articleNO) throws Exception;

	public int addAdminArticle(Map articleMap) throws Exception;

	public void adminPointArticleChecked(int articleNO) throws Exception;
	
	public int NewArticleCount() throws Exception;
}
