package com.spring.cre8.board.dao;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.cre8.board.dto.AdminArticleVO;
import com.spring.cre8.board.dto.ArticleVO;
import com.spring.cre8.board.dto.CommentsVO;
import com.spring.cre8.board.dto.ImageVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllArticlesList(int categoryNO) throws DataAccessException {
		List<ArticleVO> articlesList;
		if (categoryNO >= 1 && categoryNO <= 5) {
			articlesList = sqlSession.selectList("mapper.board.selectAllArticlesListByCategory", categoryNO);
		} else {
			articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		}
		return articlesList;
	}
	
	// HOT
	@Override
	public List selectHotArticlesList(int categoryNO) throws DataAccessException {
		List<ArticleVO> articlesList;
		
			articlesList = sqlSession.selectList("mapper.board.selectHotArticlesListByCategory", categoryNO);

		return articlesList;
	}
	
	@Override
	public List selectAllAdminArticlesList() throws DataAccessException {
		List<AdminArticleVO> articlesList;
		articlesList = sqlSession.selectList("mapper.board.selectAllAdminArticlesList");

		return articlesList;
	}
	

	@Override
	public List selectArticleById(String id) throws DataAccessException {
		List<ArticleVO> articlesList;
		articlesList = sqlSession.selectList("mapper.board.selectArticleById", id);
		return articlesList;
	}
	
	@Override
	public List selectArticleByKeyword(String keyword) throws DataAccessException {
		List<ArticleVO> articlesList;
		String keyword1 = "%" + keyword + "%";
		articlesList = sqlSession.selectList("mapper.board.selectArticleByKeyword", keyword1);
		System.out.println("리스트 출력 : " + articlesList);
	
		System.out.println(articlesList.toString());
		 for (ArticleVO resultlist : articlesList) {
	            System.out.println(resultlist.getArticleNO());
	            System.out.println(resultlist.getTitle());

		 }		
		return articlesList;
	}
	
	

	// 특정 아이디로 쓴 글 갯수 카운트
	@Override
	public int listArticlesCountById(String id) throws Exception {
		return sqlSession.selectOne("mapper.board.selectArticleCountById", id);
	}

	// 새 글 번호를 받아온다.
	@Override
	public int selectNewArticleCount() throws DataAccessException {
		Integer temp = sqlSession.selectOne("mapper.board.selectNewBoardCodeNO");

		if (temp == null) {
			temp = 1;
		}
		int parameter = temp;
		System.out.println("DAO : selectNewArticleCount : " + parameter);
		return parameter;
	}
	
	// 관리자 게시판의 새 글 번호를 받아온다.
		@Override
		public int selectNewAdminArticleCount() throws DataAccessException {
			Integer temp = sqlSession.selectOne("mapper.board.selectNewAdminBoardCodeNO");

			if (temp == null) {
				temp = 1;
			}
			int parameter = temp;
			return parameter;
		}
	

	// 글 하나를 작성한다.
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		// articleMap에서 카테고리 정보를 뽑아내기
		System.out.println(articleMap);
		String categoryNOtemp = (String) articleMap.get("categoryNO");
		int temp = Integer.parseInt(categoryNOtemp);
		System.out.println("DAO category : " + temp);

		// 새 글 번호를 받아온다.
		int articleNO = selectNewArticleCount();

		// 카테고리 번호를 받아온다.
		int categoryNO = temp;

		System.out.println("DAO : 카테고리 글번호 : " + categoryNO);
		System.out.println("DAO : 글번호 : " + articleNO);
		// 가장 최신 번호를 받아왔으면 articleNO에 넣는다.

		articleMap.put("articleNO", articleNO);

		System.out.println(articleMap);
		sqlSession.insert("mapper.board.insertNewArticle", articleMap);

		return articleNO;
	}

	// 포인트 충전 요청
	public int insertAdminNewArticle(Map articleMap) throws DataAccessException {
		// 새 글 번호를 받아온다.
		int articleNO = selectNewAdminArticleCount();

		articleMap.put("admin_bno", articleNO);
		System.out.println(articleMap);
		sqlSession.insert("mapper.board.insertNewAdminArticle", articleMap);

		return articleNO;
	}
	


	
	
	// ���� ���� ���ε�
	/*
	 * @Override public void insertNewImage(Map articleMap) throws
	 * DataAccessException { List<ImageVO> imageFileList =
	 * (ArrayList)articleMap.get("imageFileList"); int articleNO =
	 * (Integer)articleMap.get("articleNO"); int imageFileNO =
	 * selectNewImageFileNO(); for(ImageVO imageVO : imageFileList){
	 * imageVO.setImageFileNO(++imageFileNO); imageVO.setArticleNO(articleNO); }
	 * sqlSession.insert("mapper.board.insertNewImage",imageFileList); }
	 * 
	 */

	// 글 가져오기 관리자
	@Override
	public ArticleVO selectArticleAdmin(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectDeleteArticleView", articleNO);
	}

	// 글 가져오기
	@Override
	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}

	// 관리자 게시판 글 가져오기
	@Override
	public AdminArticleVO selectAdminArticle(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectAdminArticle", articleNO);
	}

	
	
	// 글 수정하기
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		System.out.println("updateArticle 호출");
		System.out.println(articleMap);

		sqlSession.update("mapper.board.updateArticle", articleMap);
	}
	
	
	// 포인트 처리글 확인하기
	@Override
	public void updateAdminPointArticleChecked(int articleNO) throws DataAccessException {
		sqlSession.update("mapper.board.updateAdminPointArticleChecked", articleNO);
	}
	
	
	

	// 글을 삭제한다.
	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.update("mapper.board.updateDeleteArticle", articleNO);
		// 주석을 해제하면 진짜로 삭제될 것이다.
		// sqlSession.delete("mapper.board.deleteArticle", articleNO);

	}

	// 댓글을 가져오기
	@Override
	public List selectComments(int articleNO) throws DataAccessException {
		List<CommentsVO> commentsList;
		commentsList = sqlSession.selectList("mapper.board.selectComments", articleNO);
		return commentsList;
	}

	// 새 댓글 번호를 가져온다.
	@Override
	public int selectNewCommentsCount() throws DataAccessException {
		Integer temp = sqlSession.selectOne("mapper.board.selectNewCommentsNO");

		// 만약 null이라면 (글이 없다면) 새 글 번호는 1번이다.
		if (temp == null) {
			temp = 1;
		}
		int newNO = temp;
		System.out.println("DAO : selectNewCommentCount : " + newNO);
		return newNO;
	}

	// 댓글을 쓰기
	@Override
	public int insertComments(Map commentMap) throws DataAccessException {
		// 새 글 번호를 받아온다.
		int commentNO = selectNewCommentsCount();
		// 글 번호를 맵에 넣어준다.
		commentMap.put("commentNO", commentNO);

		// 최종적인 값을 콘솔에 찍어본다.
		// {commentId=hong, id=hong, commentContent=댓글 테스트해봅니다., articleNO=1}
		System.out.println(commentMap);

		commentNO = sqlSession.insert("mapper.board.insertComments", commentMap);
		return commentNO;
	}

	// 댓글을 삭제
	@Override
	public int deleteComments(int commentNO) throws DataAccessException {
		return sqlSession.update("mapper.board.updateDeleteComments", commentNO);
	}


	
	
	@Override
	public List selectImageFileList(int articleNO) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.board.selectImageFileList", articleNO);
		return imageFileList;
	}

	// 카테고리 내의 글번호
	// 2021.10.06. 테이블 구조 변경으로 사용하지 않음
	private int selectNewArticleNO(int categoryNO) throws DataAccessException {
		Integer temp = sqlSession.selectOne("mapper.board.selectNewArticleNO", categoryNO);

		if (temp == null) {
			temp = 1;
		}
		int parameter = temp;

		System.out.println("전달받은 selectNewArticleNO 파라메타 : " + categoryNO);
		System.out.println("받아온 값 : " + parameter);
		return parameter;
	}

	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
	}

}
