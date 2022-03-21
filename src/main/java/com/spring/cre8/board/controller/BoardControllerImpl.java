package com.spring.cre8.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.cre8.board.service.BoardService;
import com.spring.cre8.board.dto.ArticleVO;
import com.spring.cre8.board.dto.AdminArticleVO;
import com.spring.cre8.board.dto.CommentsVO;
import com.spring.cre8.board.dto.ImageVO;
import com.spring.cre8.member.dto.MemberDTO;
import com.spring.cre8.likes.service.likesService;

@Controller("boardController")
public class BoardControllerImpl implements BoardController {
	// 이미지 저장 경로
	private static final String ARTICLE_IMAGE_REPO = "C:\\app\\cre8";
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;
	@Autowired
	private AdminArticleVO adminArticleVO;
	@Autowired
	private CommentsVO commentsVO;
	@Autowired
	private likesService likesService;

	@Override
	@RequestMapping(value = "/board/listArticles.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listArticles(@RequestParam("category") int categoryNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		System.out.println("카테고리 파라메터 : " + categoryNO);
		List articlesList = boardService.listArticles(categoryNO);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		mav.addObject("categoryParameter", categoryNO);
		return mav;
	}

	// 검색
	@Override
	@RequestMapping(value = "/board/search.do", method = RequestMethod.POST)
	public ModelAndView searchResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("search.do 호출");
		String viewName = (String) request.getAttribute("viewName");
		String keyword = request.getParameter("keyword");

		System.out.println(keyword);
		List articlesList = boardService.listArticlesByKeyword(keyword);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		mav.addObject("keyword", keyword);
		return mav;
	}

	// ID별 작성 글 읽어오기
	@Override
	@RequestMapping(value = "/board/myArticles.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listArticlesById(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("myArticles.do 호출");
		System.out.println("파라메터 : " + id);
		String viewName = (String) request.getAttribute("viewName");

		List articlesList = boardService.listArticlesById(id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		return mav;
	}

	// 한개 이미지 글쓰기
	@Override
	@RequestMapping(value = "/board/addNewArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		System.out.println(enu);
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}

		String imageFileName = upload(multipartRequest);
		System.out.println("파일명 : " + imageFileName);
		HttpSession session = multipartRequest.getSession();
		MemberDTO memberVO = (MemberDTO) session.getAttribute("member");
		String id = memberVO.getId();
		articleMap.put("id", id); // articleMap에 작성자 id를 넣는다
		articleMap.put("imageFileName", imageFileName); // articleMap에 첨부파일을 넣는다.

		// Map에서 카테고리 번호를 뽑아서 Int형으로 변환한다.
		// 리스트로 돌아가는 파라메터로 사용할 것이다.
		String lowCategoryNO = (String) articleMap.get("categoryNO");
		int categoryNO = Integer.parseInt(lowCategoryNO);
		System.out.println(categoryNO);

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			// 글 번호를 가져온다.
			int articleNO = boardService.addNewArticle(articleMap);
			System.out.println("File Controller : " + articleNO);

			// 수동으로 temp폴더를 만들어 놓지 않으면 SQL IO오류가 발생함. 반드시 폴더를 만들자.
			if (imageFileName != null && imageFileName.length() != 0) {
				System.out.println("이미지 파일이 있음");
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);

				// 글번호 (articleNO)에 맞는 디렉터리를 만들어 줄 것이다.
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			// 파라메터로 넘겨서 목록으로 돌아가기
			message = "<script>";
			message += " alert('새 글을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/listArticles.do?category="
					+ categoryNO + "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/articleForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 한개의 글과 이미지 보여주기
	@Override
	@RequestMapping(value = "/board/viewArticle.do", method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		// 파라메터로 받은 글번호대로 글을 호출한다.
		System.out.println(articleVO.getArticleNO() + "번 글 호출");
		articleVO = boardService.viewArticle(articleNO);

		// 댓글을 가져온다.
		List commentsList = boardService.listCommentsByArticleNO(articleNO);

		// 좋아요 개수를 살펴본다.
		int countLikes = likesService.viewArticleLikesCount(articleNO);

		// 세션을 가져와서 사용자가 로그인했는지 살펴본다.
		HttpSession session = request.getSession(false);
		int LikesCheck = 0;

		// 세션이 비어있지 않는다면 로그인한 사용자 이름으로 좋아요 테이블 중복검사를 한다.
		if (session.getAttribute("member") != null) {
			MemberDTO memberVO = (MemberDTO) session.getAttribute("member");
			String id = memberVO.getId();
			System.out.println("세션 : " + id);

			// 리턴값이 1이면 좋아요를 눌렀고, 0이면 좋아요를 누르지 않았다.
			LikesCheck = likesService.likesCheck(articleNO, id);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("article", articleVO); // article view에 가져온다
		mav.addObject("commentsList", commentsList); // 댓글 목록을 가져온다
		mav.addObject("countLikes", countLikes); // 좋아요 갯수를 가져온다
		mav.addObject("likes", LikesCheck); // 내가 좋아요를 했는지 판별값을 저장해준다.
		return mav;
	}

	// ADMIN용 한개의 글과 이미지 보여주기 (삭제글 포함)
	@RequestMapping(value = "/board/viewArticleAdmin.do", method = RequestMethod.GET)
	public ModelAndView viewArticleAdmin(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		// 파라메터로 받은 글번호대로 글을 호출한다.
		articleVO = boardService.viewArticleAdmin(articleNO);
		System.out.println(articleVO.getArticleNO() + "번 글 호출");

		// 좋아요 개수를 살펴본다.
		int countLikes = likesService.viewArticleLikesCount(articleNO);

		// 세션을 가져와서 사용자가 로그인했는지 살펴본다.
		HttpSession session = request.getSession(false);
		int LikesCheck = 0;

		// 세션이 비어있지 않는다면 로그인한 사용자 이름으로 좋아요 테이블 중복검사를 한다.
		if (session.getAttribute("member") != null) {
			MemberDTO memberVO = (MemberDTO) session.getAttribute("member");
			String id = memberVO.getId();
			System.out.println("세션 : " + id);

			// 리턴값이 1이면 좋아요를 눌렀고, 0이면 좋아요를 누르지 않았다.
			LikesCheck = likesService.likesCheck(articleNO, id);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("article", articleVO); // article view에 가져온다
		mav.addObject("countLikes", countLikes); // 좋아요 갯수를 가져온다
		mav.addObject("likes", LikesCheck); // 내가 좋아요를 했는지 판별값을 저장해준다.

		return mav;
	}

	// 관리자 게시판의 본문 보기
	@Override
	@RequestMapping(value = "/board/viewAdminArticle.do", method = RequestMethod.GET)
	public ModelAndView viewAdminArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		// 파라메터로 받은 글번호대로 글을 호출한다.
		adminArticleVO = boardService.viewAdminArticle(articleNO);

		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("article", adminArticleVO); // article view에 가져온다
		return mav;
	}

	// 좋아요 체크, 좋아요 하기, 좋아요 취소
	@RequestMapping(value = "/board/likes.do", method = RequestMethod.GET)
	public ResponseEntity likesAction(@RequestParam("articleNO") int articleNO, @RequestParam("id") String likes_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int countLikes = likesService.likesCheck(articleNO, likes_id);

			System.out.println(articleVO.getArticleNO() + "번 글 호출");
			System.out.println(countLikes);
			if (countLikes == 1) {
				likesService.likesCancel(articleNO, likes_id);
			} else if (countLikes == 0) {
				likesService.likesEnable(articleNO, likes_id);

			} else {
				System.out.println("boardController : likes.do 에러");
			}

			message = "<script>";
			// message += " alert('확인');";
			message += " location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNO=" + articleNO
					+ "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			// message += " alert('실패');";
			message += " location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNO=" + articleNO
					+ "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}

	// 댓글쓰기
	@RequestMapping(value = "/board/comments.do", method = RequestMethod.POST)
	public ResponseEntity commentsAction(@RequestParam("articleNO") int articleNO,
			@RequestParam("id") String comments_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 댓글이 담길 java map을 만든다.
		Map<String, Object> commentMap = new HashMap<String, Object>();

		// request를 통해 값을 받는다.
		request.setCharacterEncoding("utf-8");

		// request에서 받아온 모든 값을 콘솔에 찍어본다.
		Enumeration enu = request.getParameterNames();
		System.out.println(enu);

		// 값에 내용이 있다면 순서대로 맵에 넣어준다.
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			commentMap.put(name, value);
		}

		// 맵 내용을 콘솔에 찍어본다.
		System.out.println(commentMap);
		// 이런 식으로 들어갈 것이다.
		// {commentId=hong, id=hong, commentContent=댓글 테스트해봅니다., articleNO=1}
		// 댓글 번호가 없는데 DAO단에서 넣어줄 것이다.

		// response를 위한 message header를 생성한다.
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			// 댓글이 담긴 commentMAP을 전송한다.
			int result = 0;
			result = boardService.addComment(commentMap);

			message = "<script>";
			message += " alert('댓글 작성 확인');";
			message += " location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNO=" + articleNO
					+ "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('댓글 작성 실패');";
			message += " location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNO=" + articleNO
					+ "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}

	// 댓글삭제
	@RequestMapping(value = "/board/commentDelete.do", method = RequestMethod.POST)
	public ResponseEntity commentsDeleteAction(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setCharacterEncoding("utf-8");

		// 2021. 10. 08. 18:30 파라메터를 직접 받아오는 GET방식에서 데이터를 숨기는 POST방식으로 수정
		// 댓글이 담길 java map을 만든다.

		Map<String, Object> commentMap = new HashMap<String, Object>();

		// request를 통해 값을 받는다.
		request.setCharacterEncoding("utf-8");

		Enumeration enu = request.getParameterNames();
		// request에서 받아온 모든 값을 콘솔에 찍어본다.
		// System.out.println(enu);

		// 값에 내용이 있다면 순서대로 맵에 넣어준다.
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			commentMap.put(name, value);
		}

		// 맵 내용을 콘솔에 찍어본다.
		// System.out.println(commentMap);

		int commentNO = Integer.parseInt((String) commentMap.get("commentNO"));
		int articleNO = Integer.parseInt((String) commentMap.get("articleNO"));
		// System.out.println("맵에서 뽑아온 값 : " + commentNO + ", " + articleNO);

		// response를 위한 message header를 생성한다.
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			// 댓글 삭제 요청을 한다.
			int result = 0;
			result = boardService.deleteComment(commentNO);

			message = "<script>";
			message += " alert('댓글 삭제 확인');";
			message += " location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNO=" + articleNO
					+ "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('댓글 삭제 실패');";
			message += " location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNO=" + articleNO
					+ "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	/*
	 * //다중 이미지 보여주기
	 * 
	 * @RequestMapping(value="/board/viewArticle.do" ,method = RequestMethod.GET)
	 * public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO,
	 * HttpServletRequest request, HttpServletResponse response) throws Exception{
	 * String viewName = (String)request.getAttribute("viewName"); Map
	 * articleMap=boardService.viewArticle(articleNO); ModelAndView mav = new
	 * ModelAndView(); mav.setViewName(viewName); mav.addObject("articleMap",
	 * articleMap); return mav; }
	 */

	// 수정할 글과 이미지 보여주기
	@RequestMapping(value = "/board/modArticleView.do", method = RequestMethod.GET)
	public ModelAndView modArticleView(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		articleVO = boardService.viewArticle(articleNO);
		System.out.println(articleVO.getArticleNO() + "번 글 호출");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("article", articleVO); // article view에 가져온다

		return mav;
	}

	// 한개의 이미지 수정기능
	@RequestMapping(value = "/board/modArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		System.out.println("modArticle.do 호출");
		System.out.println(enu);

		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}
		String imageFileName = upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberDTO memberVO = (MemberDTO) session.getAttribute("member");
		String id = memberVO.getId();
		articleMap.put("id", id);
		articleMap.put("imageFileName", imageFileName);

		String articleNO = (String) articleMap.get("articleNO");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			// 글과 파일명을 수정할 것이다.
			boardService.modArticle(articleMap);
			if (imageFileName != null && imageFileName.length() != 0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);

				String originalFileName = (String) articleMap.get("originalFileName");
				File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
				oldFile.delete();
			}
			message = "<script>";
			message += " alert('글을 수정했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/viewArticle.do?articleNO="
					+ articleNO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
			srcFile.delete();
			message = "<script>";
			message += " alert('오류가 발생했습니다');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/viewArticle.do?articleNO="
					+ articleNO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}

	// 글 삭제
	// 2021.10.12. POST방식으로 수정
	@Override
	@RequestMapping(value = "/board/removeArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int articleNO = Integer.parseInt(request.getParameter("articleNO"));
		int categoryNO = Integer.parseInt(request.getParameter("categoryNO"));

		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			boardService.removeArticle(articleNO);

			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/listArticles.do?category=" + categoryNO
					+ "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			message = "<script>";
			message += " alert('오류가 발생했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/listArticles.do?category=" + categoryNO
					+ "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// ${contextPath}/board/adminPointArticleChecked.do

	// 포인트 요청 글 처리완료 모듈
	@Override
	@RequestMapping(value = "/board/adminPointArticleChecked.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity adminPointArticleChecked(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");

		int articleNO = Integer.parseInt(request.getParameter("usageArticleNO"));

		String message;
		ResponseEntity resEnt = null;

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			boardService.adminPointArticleChecked(articleNO);

			message = "<script>";
			message += " alert('선택한 글을 완료처리 했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/listAdminArticles.do'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			message = "<script>";
			message += " alert('오류가 발생했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/listAdminArticles.do'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	/*
	 * 글 삭제 : 2021/10/06 사용 안함 delete를 이용하여 진짜로 삭제하므로 사용하지 않을 것이다.
	 * 
	 * @Override
	 * 
	 * @RequestMapping(value="/board/removeArticle.do" ,method = RequestMethod.POST)
	 * 
	 * @ResponseBody public ResponseEntity removeArticle(@RequestParam("articleNO")
	 * int articleNO, HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ response.setContentType("text/html; charset=UTF-8"); String
	 * message; ResponseEntity resEnt=null; HttpHeaders responseHeaders = new
	 * HttpHeaders(); responseHeaders.add("Content-Type",
	 * "text/html; charset=utf-8"); try { boardService.removeArticle(articleNO);
	 * File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
	 * FileUtils.deleteDirectory(destDir);
	 * 
	 * message = "<script>"; message += " alert('글을 삭제했습니다.');"; message +=
	 * " location.href='"+request.getContextPath()+"/board/listArticles.do';";
	 * message +=" </script>"; resEnt = new ResponseEntity(message, responseHeaders,
	 * HttpStatus.CREATED);
	 * 
	 * }catch(Exception e) { message = "<script>"; message +=
	 * " alert('오류가 발생했습니다.');"; message +=
	 * " location.href='"+request.getContextPath()+"/board/listArticles.do';";
	 * message +=" </script>"; resEnt = new ResponseEntity(message, responseHeaders,
	 * HttpStatus.CREATED); e.printStackTrace(); } return resEnt; }
	 */

	// *form.do
	@RequestMapping(value = "/board/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(@RequestParam("category") int categoryNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("form.do : " + categoryNO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("categoryParameter", categoryNO);
		return mav;
	}

	// 파일 업로드
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		String imageFileName = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		System.out.println("upload 호출 : " + fileNames);
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			imageFileName = mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_REPO + "\\" + fileName);
			if (mFile.getSize() != 0) { // File Null Check
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName)); // �ӽ÷� �����
																										// multipartFile��
																										// ���� ���Ϸ�
																										// ����
			}
		}
		return imageFileName;
	}

	@Override
	@RequestMapping(value = "/board/listAdminArticles.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listAdminArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List articlesList = boardService.listAdminArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		return mav;
	}

}
