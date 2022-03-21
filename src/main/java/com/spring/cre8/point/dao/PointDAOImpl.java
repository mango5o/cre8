package com.spring.cre8.point.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.cre8.likes.dto.LikesDTO;
import com.spring.cre8.point.dao.PointDAO;
import com.spring.cre8.point.dto.PointDTO;

@Repository("pointDAO")
public class PointDAOImpl implements PointDAO {
	@Autowired
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
		
	// 아이디 별 포인트 현황 조회
	@Override
	public int selectByIdPoint(String id) throws DataAccessException {
		int result = sqlSession.selectOne("mapper.point.selectByIdPoint", id);

		return result;
	}

	// 아이디 별 후원한 건수 조회
	@Override
	public int selectByIdDonateCount(String id) throws DataAccessException {
		int result = sqlSession.selectOne("mapper.point.selectByIdDonateCount", id);

		return result;
	}
		
	// 아이디 별 포인트 내역 전체 조회
	@Override
	public List selectAllPoint(String id) throws DataAccessException {
		List<PointDTO> pointList = null;
		pointList = sqlSession.selectList("mapper.point.selectByIdPointList", id);
		return pointList;
	}
	
	@Override
	public List viewMydonateList(String id) throws DataAccessException {
		List<PointDTO> mydonateList;
		mydonateList = sqlSession.selectList("mapper.point.selectByIdDonateList", id);

		return mydonateList;
	}
	

	// 포인트 번호를 받아오기
	@Override
	public int selectNewPointCount() throws DataAccessException {
		Integer temp = sqlSession.selectOne("mapper.point.selectNewPointNO");

		// 만약 null이라면 (글이 없다면) 새 글 번호는 1번이다.
		if (temp == null) {
			temp = 1;
		}
		int newNO = temp;
		System.out.println("DAO : selectNewPointCount : " + newNO);
		return newNO;
	}

	// 포인트 증가
		@Override
		public int actionToIncomePoint(Map incomeMap) throws DataAccessException {
	
			System.out.println(incomeMap);
			String incomeID = (String) incomeMap.get("incomeID");
			int myPoint = (Integer) incomeMap.get("myPoint");
			int incomePoint = Integer.parseInt((String) incomeMap.get("incomePoint"));
						
			Map<String, Object> incomeMap1 = new HashMap<String, Object>();
			
			incomeMap1.put("point_no", selectNewPointCount());
			incomeMap1.put("point_id", incomeID);
			incomeMap1.put("currentPoint", myPoint + incomePoint);
			incomeMap1.put("incomePoint", incomePoint);
			incomeMap1.put("pointCase", "1");
			System.out.println("충전하는 사람 : " + incomeMap1);
			
			return sqlSession.insert("mapper.point.insertActionIncomePoint", incomeMap1);

		}

		// 포인트 감소
				@Override
				public int actionToOutgoPoint(Map outgoMap) throws DataAccessException {
			
					String outgoID = (String) outgoMap.get("outgoID");
					int myPoint = (Integer) outgoMap.get("myPoint");
					int outgoPoint = Integer.parseInt((String) outgoMap.get("outgoPoint"));
								
					Map<String, Object> outgoMap1 = new HashMap<String, Object>();
					
					outgoMap1.put("point_no", selectNewPointCount());
					outgoMap1.put("point_id", outgoID);
					outgoMap1.put("currentPoint", myPoint - outgoPoint);
					outgoMap1.put("outgoPoint", outgoPoint);
					outgoMap1.put("pointCase", "4");
					System.out.println("차감하는 사람 : " + outgoMap1);
					
					return sqlSession.insert("mapper.point.insertActionOutgoPoint", outgoMap1);

				}

		
		
	
	
	
	// 포인트 전송
	@Override
	public int actionToPoint1(Map donateMap) throws DataAccessException {
		// 기부하는 사람 ID
		//String donateFromId = (String) donateMap.get("donateFromId");
		// 기부받는 사람 ID
		//String donateToId = (String) donateMap.get("donateToId");

		int myPoint1 = Integer.parseInt((String) donateMap.get("myPoint"));
		int donatePoint = Integer.parseInt((String) donateMap.get("donatePoint"));

		Map<String, Object> donateMap1 = new HashMap<String, Object>();
		
		donateMap1.put("point_no", selectNewPointCount());
		donateMap1.put("point_id", (String) donateMap.get("donateFromId"));
		donateMap1.put("currentPoint", myPoint1 - donatePoint);
		donateMap1.put("outgoPoint", donatePoint);
		donateMap1.put("pointCase", "2");
		donateMap1.put("boardCode", Integer.parseInt((String) donateMap.get("articleNO")));
		donateMap1.put("donator", (String) donateMap.get("donateToId"));
		System.out.println("기부 하는 사람 : " + donateMap);
		
		return sqlSession.insert("mapper.point.insertActionPoint1", donateMap1);

	}

	public int actionToPoint2(Map donateMap) throws DataAccessException {
		Map<String, Object> donateMap2 = new HashMap<String, Object>();
		int myPoint2 = selectByIdPoint((String) donateMap.get("donateToId"));
		int donatePoint = Integer.parseInt((String) donateMap.get("donatePoint"));

		donateMap2.put("point_no", selectNewPointCount()); 
		donateMap2.put("point_id", (String) donateMap.get("donateToId"));
		donateMap2.put("currentPoint", myPoint2 + donatePoint);
		donateMap2.put("incomePoint", donatePoint);
		donateMap2.put("pointCase", "3");
		donateMap2.put("boardCode", Integer.parseInt((String) donateMap.get("articleNO")));
		donateMap2.put("donator", (String) donateMap.get("donateFromId"));


		System.out.println("기부 받는 사람 : " + donateMap2);
		return sqlSession.insert("mapper.point.insertActionPoint2", donateMap2);

	}

}
