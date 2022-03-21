package com.spring.cre8.point.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface PointDAO {
	 public List selectAllPoint(String id) throws DataAccessException;
		public List viewMydonateList(String id) throws DataAccessException;

	 public int selectByIdPoint(String id) throws DataAccessException;
	 public int selectNewPointCount() throws DataAccessException;
	public int selectByIdDonateCount(String id) throws DataAccessException;
	public int actionToPoint1(Map donateMap1) throws DataAccessException;
	public int actionToPoint2(Map donateMap2) throws DataAccessException;

	public int actionToIncomePoint(Map incomeMap) throws DataAccessException;
	public int actionToOutgoPoint(Map outgoMap) throws DataAccessException;

}