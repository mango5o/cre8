package com.spring.cre8.point.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cre8.likes.dto.LikesDTO;
import com.spring.cre8.member.dao.MemberDAO;
import com.spring.cre8.member.service.MemberService;
import com.spring.cre8.point.dao.PointDAO;
import com.spring.cre8.point.dto.PointDTO;

@Service("pointService")
@Transactional(propagation = Propagation.REQUIRED)
public class PointServiceImpl implements PointService {
	@Autowired
	private PointDAO PointDAO;

	
	// 현재의 포인트를 조회한다.
	@Override
	public int currentPoint(String id) throws DataAccessException {
		int point = 0;
		point = PointDAO.selectByIdPoint(id);

		return point;
	}
	
	@Override
	public int donateCountbyID(String id) throws DataAccessException {
		return PointDAO.selectByIdDonateCount(id);
	}
	
	
	

	// 포인트 내역을 리스트로 출력한다.
	@Override
	public List pointList(String id) throws DataAccessException {
		List pointList = null;
		pointList = PointDAO.selectAllPoint(id);
		return pointList;
	}
	
	@Override
	public List mydonateList(String id) throws DataAccessException {
		List mydonateList = PointDAO.viewMydonateList(id);
		return mydonateList;
	}

	

	@Override
	public void actionDonate(Map donateMap) throws DataAccessException {
		int resultA = PointDAO.actionToPoint1(donateMap);
		int resultB = PointDAO.actionToPoint2(donateMap);
		System.out.println("Transection A : " + resultA);
		System.out.println("Transection B : " + resultB);
}
	
	@Override
	public int actionIncomePoint(Map incomeMap) throws DataAccessException {
		return PointDAO.actionToIncomePoint(incomeMap);
	}

	@Override
	public int actionOutgoPoint(Map outgoMap) throws DataAccessException {
		return PointDAO.actionToOutgoPoint(outgoMap);
	}
	
}
