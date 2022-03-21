package com.spring.cre8.likes.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.cre8.likes.dto.LikesDTO;

public interface likesDAO {
	public int viewArticleLikesCount(int articleNO) throws DataAccessException;

	public int likesCheck(Map likesMap) throws DataAccessException;

	public int likesCancel(Map likesMap) throws DataAccessException;

	public int likesEnable(Map likesMap) throws DataAccessException;

	public List viewMyLikes(String id) throws DataAccessException;

}
