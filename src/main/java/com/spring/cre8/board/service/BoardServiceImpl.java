package com.spring.cre8.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cre8.board.dao.BoardDAO;
import com.spring.cre8.board.dto.AdminArticleVO;
import com.spring.cre8.board.dto.ArticleVO;
import com.spring.cre8.board.dto.CommentsVO;
import com.spring.cre8.board.dto.ImageVO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO boardDAO;

	@Override
	public List<ArticleVO> listArticles(int categoryNO) throws Exception {
		List<ArticleVO> articlesList = boardDAO.selectAllArticlesList(categoryNO);
		return articlesList;
	}
	
	@Override
	public List<ArticleVO> listHotArticles(int categoryNO) throws Exception {
		List<ArticleVO> articlesList = boardDAO.selectHotArticlesList(categoryNO);
		return articlesList;
	}

	@Override
	public List<CommentsVO> listCommentsByArticleNO(int articleNO) throws Exception {
		List<CommentsVO> commentsList = boardDAO.selectComments(articleNO);
		return commentsList;
	}

	@Override
	public List<AdminArticleVO> listAdminArticles() throws Exception {
		List<AdminArticleVO> articlesList = boardDAO.selectAllAdminArticlesList();
		return articlesList;
	}
	
	// 특정 키워드로 글 검색
	@Override
	public List<ArticleVO> listArticlesByKeyword(String keyword) throws Exception {
		List<ArticleVO> articlesList = boardDAO.selectArticleByKeyword(keyword);
		return articlesList;
	}
	
	
	// 특정 아이디로 글 검색
	@Override
	public List<ArticleVO> listArticlesById(String id) throws Exception {
		List<ArticleVO> articlesList = boardDAO.selectArticleById(id);
		return articlesList;
	}

	// 특정 아이디 글 갯수 카운트
	@Override
	public int listArticlesCountById(String id) throws Exception {
		return boardDAO.listArticlesCountById(id);
	}

	// 관리자 게시판 포인트 충전요청 글쓰기
	@Override
	public int addAdminArticle(Map articleMap) throws Exception {
		return boardDAO.insertAdminNewArticle(articleMap);
	}
	


	
	
	
	// 본문 글쓰기 - 단일이미지 추가
	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int temp = boardDAO.insertNewArticle(articleMap);
		System.out.println("DAO를 통해 Service에서 받은 값 : " + temp);
		return temp;
	}

	// 댓글 달기
	@Override
	public int addComment(Map commentMap) throws Exception {
		return boardDAO.insertComments(commentMap);
	}

	
	// 댓글 삭제articleNO, comments_id
	public int deleteComment(int commentNO) throws Exception {
		return boardDAO.deleteComments(commentNO);
	}
	
	// 새 글의 번호를 카운트한다.
	@Override
	public int NewArticleCount() throws Exception {
		return boardDAO.selectNewArticleCount();

	}

		
	
	// 다중이미지추가
	/*
	 * @Override public int addNewArticle(Map articleMap) throws Exception{ int
	 * articleNO = boardDAO.insertNewArticle(articleMap);
	 * articleMap.put("articleNO", articleNO); boardDAO.insertNewImage(articleMap);
	 * return articleNO; }
	 */
	/*
	 * //다중 파일 보이기
	 * 
	 * @Override public Map viewArticle(int articleNO) throws Exception { Map
	 * articleMap = new HashMap(); ArticleVO articleVO =
	 * boardDAO.selectArticle(articleNO); List<ImageVO> imageFileList =
	 * boardDAO.selectImageFileList(articleNO); articleMap.put("article",
	 * articleVO); articleMap.put("imageFileList", imageFileList); return
	 * articleMap; }
	 */

	// 본문 읽기 (단일 이미지 파일)
	@Override
	public ArticleVO viewArticle(int articleNO) throws Exception {
		ArticleVO article = boardDAO.selectArticle(articleNO);
		return article;
	}

	// 본문 읽기 (관리자)
	@Override
	public ArticleVO viewArticleAdmin(int articleNO) throws Exception {
		ArticleVO article = boardDAO.selectArticleAdmin(articleNO);
		return article;
	}
	
	// 관리자 게시판 본문 읽기
	@Override
	public AdminArticleVO viewAdminArticle(int articleNO) throws Exception {
		AdminArticleVO article = boardDAO.selectAdminArticle(articleNO);
		return article;
	}

	// 본문을 수정할 것이다.
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}

	// 글을 삭제한다.
	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}

	// 본문을 수정할 것이다.
	@Override
	public void adminPointArticleChecked(int articleNO) throws Exception {
		boardDAO.updateAdminPointArticleChecked(articleNO);
	}

}
