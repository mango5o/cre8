package com.spring.cre8.point.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cre8.point.dto.PointDTO;

public interface PointService {
	 public int currentPoint(String id) throws DataAccessException;
	public int donateCountbyID(String id) throws DataAccessException;
	public List mydonateList(String id) throws DataAccessException;

     public List pointList(String id) throws DataAccessException;
     public void actionDonate(Map donateMap) throws DataAccessException;
 	public int actionIncomePoint(Map incomeMap) throws DataAccessException;
	public int actionOutgoPoint(Map outgoMap) throws DataAccessException;

}
