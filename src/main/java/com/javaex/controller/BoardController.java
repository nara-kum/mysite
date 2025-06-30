package com.javaex.controller;

import java.util.List;

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
	
	//게시판 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("BoardController.list()");
		
		List<BoardVO> bList = boardService.exeList();
		model.addAttribute("bList",bList);
		
		return "board/list";
	}

	//게시판 등록폼
	@RequestMapping(value = "/writeform", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardWriteForm() {
		System.out.println("BoardController.boardWriteForm()");
		
		
		return "board/writeform";
	}
	
	//게시판 등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVO boardVO, HttpSession session) {
		System.out.println("BoardController.write()");
		
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		
		boardVO.setUserNo(authUser.getNo());
		
		int count = boardService.exeWrite(boardVO);
		System.out.println(boardVO);
		
		return "redirect:list";
	}
	
	//게시판 읽기
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@RequestParam(value = "no") int no, Model model) {
		System.out.println("BoardController.read()");

		BoardVO boardVO = boardService.exeRead(no);
		model.addAttribute("boardVO",boardVO);
		
		return "board/read";
	}
	
	//게시판 수정폼
	@RequestMapping(value = "/editform", method = { RequestMethod.GET, RequestMethod.POST })
	public String editForm(@ModelAttribute BoardVO boardVO) {
		System.out.println("BoardController.editForm()");

		int count = boardService.exeEditForm(boardVO);
		
		return "board/editform";
	}
	
	//게시판 수정
	@RequestMapping(value = "/edit")
	public String boardEdit() {
		System.out.println("BoardController.boardEdit()");
		
		
		return "";
	}
}
