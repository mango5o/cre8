package com.spring.cre8.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.spring.cre8.member.dto.MemberDTO;


public interface MemberService {
	 public List listMembers() throws DataAccessException;
	public int memberIDCheck(String id) throws DataAccessException;
	 public int addMember(MemberDTO memberVO) throws DataAccessException;
	 public int updateMember(MemberDTO memberVO) throws DataAccessException;
	public int joinoutMember(String id) throws DataAccessException;

	 public int removeMember(String id) throws DataAccessException;
	 public MemberDTO login(MemberDTO memberVO) throws Exception;
     public MemberDTO memberOne(String id) throws DataAccessException;
	public int ArticleLikesCount(String id) throws DataAccessException;


}
