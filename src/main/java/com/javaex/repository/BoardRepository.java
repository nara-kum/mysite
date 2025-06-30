package com.javaex.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVO;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession; 
	
	//게시판 전체 리스트
	public List<BoardVO> selectList(){
		System.out.println("BoardRepository.selectList()");
		
		List<BoardVO> bList = sqlSession.selectList("board.selectList");
		
		return bList;
	}
	
	//게시판 글 등록
	public int insertBoard(BoardVO boardVO){
		System.out.println("BoardRepository.insertBoard()");

		System.out.println(boardVO);
		int count = sqlSession.insert("board.insert", boardVO);
		
		return count;
	}
	
	//선택한 글 데이터 불러오기
	public BoardVO selectListOne(int no){
		System.out.println("BoardRepository.selectListOne()");
		
		BoardVO boardVO = sqlSession.selectOne("board.selectListOne", no);
		
		return boardVO;
	}
	
	//수정할 데이터 불러오기
	public int selectMyOne(BoardVO boardVO){
		System.out.println("BoardRepository.selectMyOne()");
		
		int count = sqlSession.selectOne("board.selectMyOne", boardVO);
		
		return count;
	}

}
