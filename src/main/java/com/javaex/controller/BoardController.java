package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//게시판 리스트
	@RequestMapping(value = "/board/list")
	public String list(Model model) {
		System.out.println("BoardController.list()");
		
		List<BoardVO> bList = boardService.exeList();
		model.addAttribute("bList",bList);
		
		return "board/list";
	}

	//게시판 등록폼
	@RequestMapping(value = "/board/writeform")
	public String boardWriteForm() {
		System.out.println("BoardController.boardWriteForm()");
		
		
		return "board/writeform";
	}
	
	//게시판 등록
	@RequestMapping(value = "/board/write")
	public String boardWrite() {
		System.out.println("BoardController.boardWrite()");
		
		
		return "";
	}
	
	//게시판 읽기
	@RequestMapping(value = "/board/read")
	public String boardRead() {
		System.out.println("BoardController.boardRead()");
		
		
		return "";
	}
	
	//게시판 수정폼
	@RequestMapping(value = "/board/editform")
	public String boardEditForm() {
		System.out.println("BoardController.boardEditForm()");
		
		
		return "";
	}
	
	//게시판 수정
	@RequestMapping(value = "/board/edit")
	public String boardEdit() {
		System.out.println("BoardController.boardEdit()");
		
		
		return "";
	}
}
