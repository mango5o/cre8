package com.spring.cre8.member.controller;

import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.cre8.member.service.MemberService;
import com.spring.cre8.point.dto.PointDTO;
import com.spring.cre8.point.service.PointService;
import com.spring.cre8.board.service.BoardService;
import com.spring.cre8.likes.service.likesService;
import com.spring.cre8.likes.dto.LikesDTO;
import com.spring.cre8.member.dto.MemberDTO;
import com.spring.cre8.board.dto.ArticleVO;

@Controller("memberController")

public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private BoardService boardService;
	@Autowired
	MemberDTO memberVO;
	@Autowired
	private likesService likesService;
	@Autowired
	private PointService pointService;
	@Autowired
	private ArticleVO articleVO;

	@RequestMapping(value = { "/", "/main.do" }, method = RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		int categoryNO1 = 1;
		int categoryNO2 = 2;
		int categoryNO3 = 3;
		int categoryNO4 = 4;
		int categoryNO5 = 5;
	
		List articlesList1 = boardService.listHotArticles(categoryNO1);
		List articlesList2 = boardService.listHotArticles(categoryNO2);
		List articlesList3 = boardService.listHotArticles(categoryNO3);
		List articlesList4 = boardService.listHotArticles(categoryNO4);
		List articlesList5 = boardService.listHotArticles(categoryNO5);
		ModelAndView mav = new ModelAndView(viewName);
		mav.setViewName(viewName);
		mav.addObject("articlesList1", articlesList1);
		mav.addObject("articlesList2", articlesList2);
		mav.addObject("articlesList3", articlesList3);
		mav.addObject("articlesList4", articlesList4);
		mav.addObject("articlesList5", articlesList5);
		return mav;
	}
	
	@RequestMapping(value = "/about.do", method = RequestMethod.GET)
	public ModelAndView aboutCre8(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.setViewName(viewName);
		return mav;
	}

	
	
	
	@Override
	@RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	// ???????????????
	@Override
	@RequestMapping(value = "/member/mypage.do", method = RequestMethod.GET)
	public ModelAndView mypage(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		// ?????? 1?????? ????????? ????????? ?????????.
		String viewName = (String) request.getAttribute("viewName");
		MemberDTO memberOne = memberService.memberOne(id);
		int point = pointService.currentPoint(id);
		int countLikes = memberService.ArticleLikesCount(id);
		int countMyArticle = boardService.listArticlesCountById(id);
		int donateCount = pointService.donateCountbyID(id);
		System.out.println(id + " : " + point);
		System.out.println(id + " : " + countLikes);

		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("memberOne", memberOne);
		mav.addObject("mypoint", point);
		mav.addObject("myArticle", countMyArticle);
		mav.addObject("countLikes", countLikes);
		mav.addObject("mydonate", donateCount);

		return mav;

	}

	// ???????????????
	@Override
	@RequestMapping(value = "/member/mypoint.do", method = RequestMethod.GET)
	public ModelAndView mypoint(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("mypoint.do ??????");
		String viewName = (String) request.getAttribute("viewName");
		List pointList = pointService.pointList(id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("pointList", pointList);
		return mav;
	}

	// ?????? ?????????
	@Override
	@RequestMapping(value = "/member/mylikes.do", method = RequestMethod.GET)
	public ModelAndView mylikes(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("mylikes.do ??????");
		String viewName = (String) request.getAttribute("viewName");
		List mylikesList = likesService.myLikes(id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("mylikesList", mylikesList);
		return mav;
	}

	// ?????? ????????????
	@Override
	@RequestMapping(value = "/member/mydonate.do", method = RequestMethod.GET)
	public ModelAndView mydonate(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("mydonate.do ??????");
		String viewName = (String) request.getAttribute("viewName");
		List donateList = pointService.mydonateList(id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("donateList", donateList);
		return mav;
	}

	// ???????????? ??????
	@Override
	@RequestMapping(value = "/member/modMemberAction.do", method = RequestMethod.POST)
	public ModelAndView modMemberAction(@ModelAttribute("member") MemberDTO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("modMemberAction ??????" + member.getId());
		int result = 0;
		String id = member.getId();
		result = memberService.updateMember(member);
		// ????????? ?????????

		ModelAndView mav = new ModelAndView("redirect:/member/mypage.do?id=" + id);
		return mav;
	}

	// ?????? ??????
	@Override
	@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberDTO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		System.out.println("?????? ?????? ??????");

		/*
		 * Enumeration<String> paramKeys = request.getParameterNames(); while
		 * (paramKeys.hasMoreElements()) { String key = paramKeys.nextElement();
		 * System.out.println(key+":"+request.getParameter(key)); }
		 */

		int result = 0;
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/loginForm.do");
		return mav;
	}

	// ???????????? 1??? ??????
	@Override
	@RequestMapping(value = "/member/modMember.do", method = RequestMethod.GET)
	public ModelAndView modMember(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("???????????? modmembers : " + id);

		String viewName = (String) request.getAttribute("viewName");
		MemberDTO memberOne = memberService.memberOne(id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("memberOne", memberOne);
		return mav;

	}
	// ?????? ??????
	@Override
	@RequestMapping(value = "/member/resignMember.do", method = RequestMethod.GET)
	public ModelAndView resignMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");

		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	// ?????? ?????? ??????
	@Override
	@RequestMapping(value = "/member/resignMemberAction.do", method = RequestMethod.POST)
	public ResponseEntity resignMemberAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");

		System.out.println(id + "?????? ?????? ??????");

		// response??? ?????? message header??? ????????????.
				response.setContentType("text/html; charset=UTF-8");
				String message;
				ResponseEntity resEnt = null;
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add("Content-Type", "text/html; charset=utf-8");

				try {
					memberService.joinoutMember(id);
					
					message = "<script>";
					message += " alert('?????? ?????? ???????????????.');";
					message += " location.href='" + request.getContextPath() + "/member/logout.do"
							+ "'";
					message += " </script>";
					resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
				} catch (Exception e) {
					message = "<script>";
					message += " alert('?????? ????????? ????????? ??????????????????.\n??????????????? ????????? ???????????????.');";
					message += " location.href='" + request.getContextPath() + "/member/logout.do"
							+ "'";
					message += " </script>";
					resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
				}

				return resEnt;
		}
	
	// ?????? ?????? (????????????????????? ????????? ?????? ????????? ???????????? ????????????)
	@Override
	@RequestMapping(value = "/member/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	/*
	 * @RequestMapping(value = { "/member/loginForm.do", "/member/memberForm.do" },
	 * method = RequestMethod.GET)
	 * 
	 * @RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	 * public ModelAndView form(HttpServletRequest request, HttpServletResponse
	 * response) throws Exception { String viewName = getViewName(request);
	 * ModelAndView mav = new ModelAndView(); mav.setViewName(viewName); return mav;
	 * }
	 */

	// ?????????
	@Override
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberDTO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);

		// state??? 1????????? ????????? ??????????????? ???????????? ??? ??? ??????.
		if (memberVO != null) {
			if (memberVO.getState() == 1) {
				rAttr.addAttribute("result", "loginFailed");
				mav.setViewName("redirect:/member/loginForm.do");			
			}		
			else {			
			
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);

			String action = (String) session.getAttribute("action");
			session.removeAttribute("action");
			if (action != null) {
				mav.setViewName("redirect:" + action);
			} else {
				mav.setViewName("redirect:/member/mypage.do?id=" + member.getId());
				}
			}
		} else {
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}

	// ????????????
	@Override
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}

	// ????????????
	@Override
	@RequestMapping(value = "/member/donate.do", method = RequestMethod.GET)
	public ModelAndView donate(String id, int articleNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		// ??? ????????? articleVO??? ????????????.
		articleVO = boardService.viewArticle(articleNO);
		// ???????????? ???????????? ????????????.
		int point = pointService.currentPoint(id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("article", articleVO); // article view??? ????????????
		mav.addObject("mypoint", point);
		return mav;
	}

	// ????????????
	@RequestMapping(value = "/member/donateAction.do", method = RequestMethod.POST)
	public ResponseEntity donateAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		Map<String, Object> donateMap = new HashMap<String, Object>();

		// request??? ?????? ?????? ?????????.
		request.setCharacterEncoding("utf-8");

		Enumeration enu = request.getParameterNames();
		// request?????? ????????? ?????? ?????? ????????? ????????????.
		// System.out.println(enu);

		// ?????? ????????? ????????? ???????????? ?????? ????????????.
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			donateMap.put(name, value);
		}

		// ??? ????????? ????????? ????????????.
		System.out.println(donateMap);

		// ???????????? ?????? ID
		String donateFromId = (String) donateMap.get("donateFromId");

		// ???????????? ?????????
		int donatePoint = Integer.parseInt((String) donateMap.get("donatePoint"));

		// ???????????? ?????? ID
		String donateToId = (String) donateMap.get("donateToId");

		// ???????????? ??? ??????
		int articleNO = Integer.parseInt((String) donateMap.get("articleNO"));

		System.out.println("??????ID : " + donateFromId + " ??????????????? : " + donatePoint);
		System.out.println("????????????ID : " + donateToId + " ???????????? ??? : " + articleNO);

		// response??? ?????? message header??? ????????????.
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			// ????????? ??????????????? ??????.
			pointService.actionDonate(donateMap);
			
			message = "<script>";
			message += " alert('?????? ??????');";
			message += " location.href='" + request.getContextPath() + "/member/donateResult.do?id=" + donateFromId
					+ "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('?????? ??????');";
			message += " location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNO=" + articleNO
					+ "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// ?????? ??????
	@Override
	@RequestMapping(value = "/member/donateResult.do", method = RequestMethod.GET)
	public ModelAndView donateResult(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List donateList = pointService.mydonateList(id);
				
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("donateList", donateList);
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(@RequestParam(value = "result", required = false) String result,
			@RequestParam(value = "action", required = false) String action, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}

	// ????????? ??????
	@Override
	@RequestMapping(value = "/member/myIncomePoint.do", method = RequestMethod.GET)
	public ModelAndView myIncomePoint(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		System.out.println("myIncomePoint.do ??????");
		String viewName = (String) request.getAttribute("viewName");
		int point = pointService.currentPoint(id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("mypoint", point);
		System.out.println(viewName);
		return mav;
	}
	
	// ????????? ????????????
	@Override
		@RequestMapping(value = "/member/myOutgoPoint.do", method = RequestMethod.GET)
		public ModelAndView myOutgoPoint(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
			System.out.println("myOutgoPoint.do ??????");
			String viewName = (String) request.getAttribute("viewName");
			
			int point = pointService.currentPoint(id);
			ModelAndView mav = new ModelAndView(viewName);
			mav.addObject("mypoint", point);
			System.out.println(viewName);
			return mav;
		}
	
	// ????????? ?????? ????????? ??????
	@Override
	@RequestMapping(value = "/member/adminPointHandle.do", method = RequestMethod.GET)
	public ModelAndView adminPointHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("adminPoint.do ??????");
		String viewName = (String) request.getAttribute("viewName");
		// List pointList = pointService.pointList(id);
		ModelAndView mav = new ModelAndView(viewName);
		// mav.addObject("pointList", pointList);
		System.out.println(viewName);
		return mav;
	}
	
	// ????????? ?????? ?????? ??????
		@RequestMapping(value = "/member/myIncomePointAction.do", method = RequestMethod.POST)
		public ModelAndView myIncomePointAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

			System.out.println("RequestMethod.POST");
			ModelAndView mav = new ModelAndView();

			String id = request.getParameter("incomeID");
			String name = request.getParameter("incomeName");
			int incomePoint = Integer.parseInt(request.getParameter("incomePoint"));
			String content = "?????? ?????? ????????? : " + incomePoint + "\n?????? ?????? ID : " + id + "\n?????? ?????? ?????????(??????) : " + name;			
			System.out.println("myIncomePointAction");
						
			Map<String, Object> articleMap = new HashMap<String, Object>();
			articleMap.put("id", id);
			articleMap.put("title", id + "??? ????????? ?????? ??????");
			articleMap.put("content", content);
			System.out.println(articleMap);
			int result = boardService.addAdminArticle(articleMap);
			mav.setViewName("/member/myIncomePointResult");
			mav.addObject("myIncomePoint", incomePoint);
			return mav;
		}
	
		// ????????? ????????? ?????? ??????
				@RequestMapping(value = "/member/myOutgoPointAction.do", method = RequestMethod.POST)
				public ModelAndView myOutgoPointAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

					System.out.println("RequestMethod.POST");
					ModelAndView mav = new ModelAndView();

					String id = request.getParameter("outgoID");
					String name = request.getParameter("outgoName");
					int outgoPoint = Integer.parseInt(request.getParameter("outgoPoint"));
					int outgoPointToWon = Integer.parseInt(request.getParameter("pointToWon"));
					String outgoBank = request.getParameter("outgoBank");
					String outgoBankAccount = request.getParameter("outgoBankAccount");
					String outgoBankAccountStr = outgoBank + outgoBankAccount;
					String content = "?????? ?????? ????????? : " + outgoPoint + "\n?????? ?????? ?????? : " + outgoPointToWon + "???\n?????? ?????? ID : " + id + "\n?????? ?????? ?????????(??????) : " + name + "\n?????? ?????? ???????????? : " + outgoBank + " " + outgoBankAccount;			
					System.out.println("myOutgoPointAction");
								
					Map<String, Object> articleMap = new HashMap<String, Object>();
					articleMap.put("id", id);
					articleMap.put("title", id + "??? ????????? ?????? ??????");
					articleMap.put("content", content);
					System.out.println(articleMap);
					int result = boardService.addAdminArticle(articleMap);
					mav.setViewName("/member/myOutgoPointResult");
					mav.addObject("myOutgoPoint", outgoPoint);
					mav.addObject("outgoBank", outgoBankAccountStr);
					mav.addObject("outgoBankToWon", outgoPointToWon);
					mav.addObject("outgoName", name);
					return mav;
				}
	

	// ????????? ?????? ????????? ?????? ??????
	@RequestMapping(value = "/member/adminPointAction.do", method = RequestMethod.POST)
	public ModelAndView adminPointAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("RequestMethod.POST");
		ModelAndView mav = new ModelAndView();
		int param = Integer.parseInt(request.getParameter("parameter"));
		System.out.println(param);

		if (param == 1) {
			String id = request.getParameter("memberId");
			int memberPoint = pointService.currentPoint(id);

			mav.setViewName("/member/adminPointHandle");
			mav.addObject("memberId", id);
			mav.addObject("memberPoint", memberPoint);

		} else if (param == 2) {
			String id = request.getParameter("pointIncomeId");
			String incomePoint = request.getParameter("incomePoint");
			System.out.println(id);
			System.out.println(incomePoint);

			int myPoint = pointService.currentPoint(id);
			System.out.println(myPoint);

			Map<String, Object> incomeMap = new HashMap<String, Object>();
			incomeMap.put("incomeID", id);
			incomeMap.put("myPoint", myPoint);
			incomeMap.put("incomePoint", incomePoint);
			System.out.println(incomeMap);

			int incomeResult = 0;
			incomeResult = pointService.actionIncomePoint(incomeMap);

			mav.setViewName("/member/adminPointHandle");

		} else if (param == 3) {
			System.out.println(param);
			String id = request.getParameter("pointOutgoId");
			String outgoPoint = request.getParameter("outgoPoint");
			System.out.println(id);
			System.out.println(outgoPoint);

			int myPoint = pointService.currentPoint(id);
			System.out.println(myPoint);

			Map<String, Object> outgoMap = new HashMap<String, Object>();
			outgoMap.put("outgoID", id);
			outgoMap.put("myPoint", myPoint);
			outgoMap.put("outgoPoint", outgoPoint);
			System.out.println(outgoMap);

			int outgoResult = 0;
			outgoResult = pointService.actionOutgoPoint(outgoMap);

			mav.setViewName("/member/adminPointHandle");
		} else {
			mav.setViewName("/member/adminPointHandle");
		}

		return mav;
	}

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}

}
