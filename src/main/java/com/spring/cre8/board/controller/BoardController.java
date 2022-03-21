package com.spring.cre8.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


public interface BoardController {
	
	public ModelAndView searchResult(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 글 목록 보기
	public ModelAndView listArticles(@RequestParam("category") int categoryNO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 아이디 별 글 목록 보기
	public ModelAndView listArticlesById(@RequestParam("id") String id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
		
	// 글 쓰기
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	
	// 글 보기
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO,
			                        HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 좋아요
	public ResponseEntity likesAction(@RequestParam("articleNO") int articleNO, @RequestParam("id") String likes_id,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 댓글 쓰기
	public ResponseEntity commentsAction(@RequestParam("articleNO") int articleNO, @RequestParam("id") String comments_id,
            HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 댓글 지우기
	public ResponseEntity commentsDeleteAction(HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	
	// 글보기 (관리자용)	
	public ModelAndView viewArticleAdmin(@RequestParam("articleNO") int articleNO,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 관리자 게시판 글 보기
	public ModelAndView viewAdminArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	// 글 수정하기
	public ModelAndView modArticleView(@RequestParam("articleNO") int articleNO,
            HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 글 지우기
	public ResponseEntity removeArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 포인트 관리자 게시글 처리완료
	public ResponseEntity adminPointArticleChecked(HttpServletRequest request, HttpServletResponse response) throws Exception;

	
	// 관리자 게시판 보기
	public ModelAndView listAdminArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;

	
	//public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest,  HttpServletResponse response) throws Exception;
}
