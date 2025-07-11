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
	
	//------------게시판 리스트333(페이징+검색)-----------------
	public List<BoardVO> selectList3(Map<String, Object> limitMap){
		System.out.println("BoardRepository.selectList2()");

		List<BoardVO> bList = sqlSession.selectList("board.selectList3",limitMap);
		
		return bList;
	}
	
	//개시판 검색 전체 갯수
	public int selectKwdTotalCount(String kwd) {
		System.out.println("BoardRepository.selectKwdTotalCount()");
		
		int kTotcnt = sqlSession.selectOne("board.selectKwdTotalCount",kwd);
		
		return kTotcnt;
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
		
		System.out.println(boardVO);
		int count = sqlSession.update("board.update", boardVO);
		
		return count;
	}
	

	//게시판 전체 리스트
	public List<BoardVO> selectRelist(){
		System.out.println("BoardRepository.selectRelist()");
		
		List<BoardVO> bList = sqlSession.selectList("board.selectRelist");
		
		return bList;
	}
	
	//계층형 게시판 글 등록
	public int insertReboard(BoardVO boardVO){
		System.out.println("BoardRepository.insertReboard()");

		int count = sqlSession.insert("board.insertReboard", boardVO);
		
		return count;
	}
	
	//계층형 게시판 댓글 등록시 본글 그룹넘 업데이트
	public int updateReboard(int no){
		System.out.println("BoardRepository.updateReboard()");

		int count = sqlSession.update("board.updateReboard", no);
		
		return count;
	}

	//계층형 게시판 댓글 동일그룹 update
	public int updateRboard(BoardVO boardVO){
		System.out.println("BoardRepository.updateRboard()");

		System.out.println(boardVO);
		sqlSession.update("board.updateRboard", boardVO);
		
		return 0;
	}

	//계층형 게시판 댓글 등록
	public int insertRboard(BoardVO boardVO){
		System.out.println("BoardRepository.insertRboard()");

		System.out.println(boardVO);
		sqlSession.insert("board.insertRboard", boardVO);
		
		return 0;
	}

}
