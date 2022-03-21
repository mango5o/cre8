package com.spring.cre8.likes.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cre8.likes.dao.likesDAO;
import com.spring.cre8.likes.dto.LikesDTO;

@Service("likesService")
@Transactional(propagation = Propagation.REQUIRED)
public class likesServiceImpl implements likesService {
	@Autowired
	likesDAO likesDAO;

	@Override
	public int viewArticleLikesCount(int articleNO) throws Exception {
		return likesDAO.viewArticleLikesCount(articleNO);
	}

	@Override
	public int likesCheck(int articleNO, String likes_id) throws Exception {
		Map<String, Object> likesMap = new HashMap<String, Object>();
		likesMap.put("articleNO", articleNO);
		likesMap.put("likesId", likes_id);
		System.out.println(likesMap);

		return likesDAO.likesCheck(likesMap);
	}

	@Override
	public int likesCancel(int articleNO, String likes_id) throws Exception {
		Map<String, Object> likesMap = new HashMap<String, Object>();
		likesMap.put("articleNO", articleNO);
		likesMap.put("likesId", likes_id);
		return likesDAO.likesCancel(likesMap);
	}

	@Override
	public int likesEnable(int articleNO, String likes_id) throws Exception {
		Map<String, Object> likesMap = new HashMap<String, Object>();
		likesMap.put("articleNO", articleNO);
		likesMap.put("likesId", likes_id);
		return likesDAO.likesEnable(likesMap);
	}

	@Override
	public List<LikesDTO> myLikes(String id) throws Exception {
		List<LikesDTO> mylikesList = likesDAO.viewMyLikes(id);
		return mylikesList;
	}

}