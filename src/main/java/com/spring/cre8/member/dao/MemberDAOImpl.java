package com.spring.cre8.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.cre8.member.dto.MemberDTO;
import com.spring.cre8.point.dto.PointDTO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberDTO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	// 아이디를 이용하여 회원 1명만 호출
	@Override
	public MemberDTO selectByIdMemberList(String id) throws DataAccessException {
		System.out.println("selectByIdMemberList 호출 : " + id);
		MemberDTO memberOne = null;
		memberOne = sqlSession.selectOne("mapper.member.selectMemberById", id);

		System.out.println(memberOne.getId());
		return memberOne;
	}

	@Override
	public int memberIDCheck(String id) throws DataAccessException {
		int result = sqlSession.selectOne("mapper.member.selectMemberIDCheck", id);
		return result;
	}
	
	@Override
	public int insertMember(MemberDTO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}

	@Override
	public int updateMember(MemberDTO memberVO) throws DataAccessException {
		int result = sqlSession.update("mapper.member.updateMember", memberVO);
		return result;
	}

	@Override
	public int joinoutMember(String id) throws DataAccessException {
		int result = sqlSession.update("mapper.member.joinoutMember", id);
		return result;
	}
	
	
	// 관리자 메뉴에서 진짜로 회원을 삭제할 때 사용한다.
	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	@Override
	public MemberDTO loginById(MemberDTO memberVO) throws DataAccessException {
		MemberDTO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		return vo;
	}

	@Override
	public int ArticleLikesCount(String id) throws DataAccessException {
		int result = sqlSession.selectOne("mapper.likes.selectLikesCountById", id);
		return result;
	}

}
