package com.spring.cre8.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.cre8.member.dto.MemberDTO;
import com.spring.cre8.point.dto.PointDTO;



public interface MemberController {
	// 회원 전체목록 (관리자모드)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 포인트 관리 페이지
	public ModelAndView adminPointHandler(HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	// 포인트 관리 실행
	public ModelAndView adminPointAction(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 마이페이지
	public ModelAndView mypage(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 회원가입
	public ModelAndView addMember(@ModelAttribute("info") MemberDTO memberVO,HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 회원정보 1명 열람
	public ModelAndView modMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 회원정보 수정
	public ModelAndView modMemberAction(@ModelAttribute("info") MemberDTO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 내 포인트 조회
	public ModelAndView mypoint(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 내 후원 내역 보기
	public ModelAndView mydonate(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 내 좋아요 조회
	public ModelAndView mylikes(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 후원하기
	public ModelAndView donate(@RequestParam("id") String id, @RequestParam("articleNO") int articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 후원하기 실행
	public ResponseEntity donateAction(HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	public ModelAndView donateResult(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	
	// 포인트 충전 신청
	public ModelAndView myIncomePoint(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 포인트 충전 실행
	public ModelAndView myIncomePointAction(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 포인트 출금 신청
	public ModelAndView myOutgoPoint(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 포인트 출금 실행
	public ModelAndView myOutgoPointAction(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 회원 탈퇴 	
	public ModelAndView resignMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 회원 탈퇴 실행
	public ResponseEntity resignMemberAction(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 삭제
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 로그인
	public ModelAndView login(@ModelAttribute("member") MemberDTO member,
                              RedirectAttributes rAttr,
                              HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 로그아웃
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
}