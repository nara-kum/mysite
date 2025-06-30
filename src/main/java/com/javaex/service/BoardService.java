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
	
	public List<BoardVO> exeList(){
		System.out.println("BoardService.exeList()");
		
		List<BoardVO> bList = boardRepository.boardSelectList();
		
		return bList;
	}

}
