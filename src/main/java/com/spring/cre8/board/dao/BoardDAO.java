package com.spring.cre8.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.cre8.board.dto.AdminArticleVO;
import com.spring.cre8.board.dto.ArticleVO;

public interface BoardDAO {
	public List selectAllArticlesList(int categoryNO) throws DataAccessException;

	public List selectHotArticlesList(int categoryNO) throws DataAccessException;

	public List selectArticleById(String id) throws DataAccessException;

	public List selectArticleByKeyword(String keyword) throws DataAccessException;

	public List selectComments(int articleNO) throws DataAccessException;

	public List selectAllAdminArticlesList() throws DataAccessException;

	public int insertComments(Map commentMap) throws DataAccessException;

	public int deleteComments(int commentNO) throws DataAccessException;

	public int listArticlesCountById(String id) throws Exception;

	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public int insertAdminNewArticle(Map articleMap) throws DataAccessException;
	
	public void updateAdminPointArticleChecked(int articleNO) throws DataAccessException;

	
	public int selectNewArticleCount() throws DataAccessException;
	public int selectNewAdminArticleCount() throws DataAccessException;

	public int selectNewCommentsCount() throws DataAccessException;

	public ArticleVO selectArticle(int articleNO) throws DataAccessException;

	public ArticleVO selectArticleAdmin(int articleNO) throws DataAccessException;

	public AdminArticleVO selectAdminArticle(int articleNO) throws DataAccessException;

	public void updateArticle(Map articleMap) throws DataAccessException;

	public void deleteArticle(int articleNO) throws DataAccessException;

	public List selectImageFileList(int articleNO) throws DataAccessException;
}
