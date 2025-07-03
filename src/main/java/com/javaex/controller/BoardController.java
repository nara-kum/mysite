package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVO;
import com.javaex.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 게시판 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list( Model model) {
		System.out.println("BoardController.list()");
		
		List<BoardVO> bList = boardService.exeList();
		model.addAttribute("bList",bList);
		System.out.println(bList);

		return "board/list";
	}

	// 게시판 리스트2(페이징)
	@RequestMapping(value = "/list2", method = { RequestMethod.GET, RequestMethod.POST })
	public String list2(@RequestParam(value = "crtpage", required = false, defaultValue = "1") int crtPage, Model model) {
		System.out.println("BoardController.list2()");

		Map<String, Object> pMap = boardService.exeList2(crtPage);
		
		model.addAttribute("pMap", pMap);

		return "board/list2";
	}

	//------------게시판 리스트333(페이징+검색)-----------------
	@RequestMapping(value = "/list3", method = { RequestMethod.GET, RequestMethod.POST })
	public String list3(@RequestParam(value = "crtpage", required = false, defaultValue = "1") int crtPage, 
						@RequestParam(value = "kwd", required = false, defaultValue = "") String kwd, Model model) {
		System.out.println("BoardController.list3()");
		
		Map<String, Object> pMap = boardService.exeList3(crtPage, kwd);
		
		model.addAttribute("pMap", pMap);

		return "board/list3";
	}

	// 글삭제
	@RequestMapping(value = "/remove", method = { RequestMethod.GET, RequestMethod.POST })
	public String remove(@ModelAttribute BoardVO boardVO, HttpSession session) {
		System.out.println("BoardController.remove()");

		UserVO authUser = (UserVO) session.getAttribute("authUser");

		boardVO.setUserNo(authUser.getNo());

		int count = boardService.exeRemove(boardVO);

		return "redirect:list";
	}

	// 게시판 등록폼
	@RequestMapping(value = "/writeform", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("BoardController.writeForm()");

		return "board/writeform";
	}

	// 게시판 등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVO boardVO, HttpSession session) {
		System.out.println("BoardController.write()");

		UserVO authUser = (UserVO) session.getAttribute("authUser");

		boardVO.setUserNo(authUser.getNo());

		int count = boardService.exeWrite(boardVO);
		System.out.println(boardVO);

		return "redirect:list";
	}

	// 게시판 읽기
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@RequestParam(value = "no") int no, Model model) {
		System.out.println("BoardController.read()");

		BoardVO boardVO = boardService.exeRead(no);
		model.addAttribute("boardVO", boardVO);

		return "board/read";
	}

	// 게시판 수정폼
	@RequestMapping(value = "/editform", method = { RequestMethod.GET, RequestMethod.POST })
	public String editForm(@RequestParam(value = "no") int no, Model model) {
		System.out.println("BoardController.editForm()");

		BoardVO boardVO = boardService.exeRead(no);

		model.addAttribute("boardVO", boardVO);

		return "board/editform";
	}

	// 글수정
	@RequestMapping(value = "/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(@ModelAttribute BoardVO boardVO, HttpSession session) {
		System.out.println("BoardController.edit()");
		UserVO authUser = (UserVO) session.getAttribute("authUser");

		boardVO.setUserNo(authUser.getNo());

		int count = boardService.exeEdit(boardVO);
		

		return "redirect:list";
	}
	

	//------------게층형 게시판-----------------
	@RequestMapping(value = "/rlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String rList( Model model) {
		System.out.println("BoardController.rList()");

		boardService.exeRlist();
		
		return "";
	}
}
