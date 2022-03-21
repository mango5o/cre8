package com.spring.cre8.member.dao;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.spring.cre8.member.dto.MemberDTO;



public interface MemberDAO {
	 public List selectAllMemberList() throws DataAccessException;
	 public int memberIDCheck(String id) throws DataAccessException;	 
	 public MemberDTO selectByIdMemberList(String id) throws DataAccessException;
	 public int insertMember(MemberDTO memberVO) throws DataAccessException ;
	 public int updateMember(MemberDTO memberVO) throws DataAccessException ;
	 public int deleteMember(String id) throws DataAccessException;
	 public int joinoutMember(String id) throws DataAccessException;

	 public MemberDTO loginById(MemberDTO memberVO) throws DataAccessException;
	public int ArticleLikesCount(String id) throws DataAccessException;

}
