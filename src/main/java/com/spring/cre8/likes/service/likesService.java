package com.spring.cre8.likes.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.cre8.likes.dto.LikesDTO;

public interface likesService {
	public int viewArticleLikesCount(int articleNO) throws Exception;

	public int likesCheck(int articleNO, String likes_id) throws Exception;

	public int likesCancel(int articleNO, String likes_id) throws Exception;

	public int likesEnable(int articleNO, String likes_id) throws Exception;

	public List<LikesDTO> myLikes(String id) throws Exception;
}
