package com.javaex.repository;

import java.util.List;
import java.util.Map;

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
	
	//게시판 전체 리스트2(페이징)
	public List<BoardVO> selectList2(Map<String, Integer> limitMap){
		System.out.println("BoardRepository.selectList2()");

		List<BoardVO> bList = sqlSession.selectList("board.selectList2",limitMap);
		
		return bList;
	}
	
	//개시판 전체 갯수
	public int selectTotalCount() {
		System.out.println("BoardRepository.selectTotalCount()");
		
		int totcnt = sqlSession.selectOne("board.selectTotalCount");
		
		return totcnt;
	}
	
	//글삭제
	public int deleteBoard(BoardVO boardVO) {
		System.out.println("deleteBoard.deleteBoard()");
		
		int count  = sqlSession.delete("board.delete",boardVO);
		
		return count;
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
		
		System.out.println(no);
		BoardVO boardVO = sqlSession.selectOne("board.selectListOne", no);
		
		return boardVO;
	}
	
	//글수정
	public int updateBoard(BoardVO boardVO){
		System.out.println("BoardRepository.updateBoard()");
		
		int count = sqlSession.update("board.update", boardVO);
		
		return count;
	}

}
