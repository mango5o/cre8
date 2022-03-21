package com.spring.cre8.likes.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.cre8.board.dto.ArticleVO;
import com.spring.cre8.likes.dto.LikesDTO;

@Repository("likesDAO")
public class likesDAOImpl implements likesDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int viewArticleLikesCount(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.likes.selectLikesCountByArticleNO", articleNO);
	}

	@Override
	public int likesCheck(Map likesMap) throws DataAccessException {
		return sqlSession.selectOne("mapper.likes.likesCheck", likesMap);
	}

	@Override
	public int likesCancel(Map likesMap) throws DataAccessException {
		return sqlSession.delete("mapper.likes.deleteLikes", likesMap);

	}

	@Override
	public int likesEnable(Map likesMap) throws DataAccessException {
		return sqlSession.insert("mapper.likes.insertLikes", likesMap);
	}

	@Override
	public List viewMyLikes(String id) throws DataAccessException {
		List<LikesDTO> mylikesList;
		mylikesList = sqlSession.selectList("mapper.likes.selectLikesListById", id);

		return mylikesList;
	}
}