package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.repository.BoardRepository;
import com.javaex.vo.BoardVO;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	//게시판리스트전체
	public List<BoardVO> exeList(){
		System.out.println("BoardService.exeList()");
		
		List<BoardVO> bList = boardRepository.selectList();
		
		return bList;
	}
	
	//글쓰기
	public int exeWrite(BoardVO boardVO){
		System.out.println("BoardService.exeWrite()");
		
		int count = boardRepository.insertBoard(boardVO);
		
		return count;
	}
	
	//선택한 글 데이터 불러오기
	public BoardVO exeRead(int no){
		System.out.println("BoardService.exeRead()");
		
		BoardVO boardVO = boardRepository.selectListOne(no);
		
		return boardVO;
	}
	
	//수정할 데이터 불러오기
	public int exeEditForm(BoardVO boardVO){
		System.out.println("BoardService.exeEditForm()");
		
		int count = boardRepository.selectMyOne(boardVO);
		
		return count;
	}

}
