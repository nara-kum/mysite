package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//게시판리스트전체2(페이징)
	public Map<String, Object> exeList2(int crtPage){
		System.out.println("BoardService.exeList2()");
		
		/********************리스트 가져오기********************/
		//한페이지 출력갯수
		int listcnt = 10;
		//글 시작번호
		int startRowNo = (crtPage -1)*listcnt ;
		
		//두 데이터를 map으로 묶는다
		Map<String, Integer> limitMap= new HashMap<String, Integer>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listcnt", listcnt);
		
		//레파지토리로 보낸다
		List<BoardVO> bList = boardRepository.selectList2(limitMap);
		
		
		/********************페이징 버튼********************/
		
		//페이지당 버튼 갯수
		int pgaeBtncount = 5;

		//페이지의 마지막 버튼 번호
		int endPageBtnNo = ((int)Math.ceil(crtPage/((double)pgaeBtncount)))*pgaeBtncount;
		
		//페이지의 시작 버튼 번호
		int startPgaeBtnNo = (endPageBtnNo-pgaeBtncount)+ 1;
		
		int totcnt = boardRepository.selectTotalCount();
		
		//다음 화살표 유무
		boolean next = false;
		if(listcnt*endPageBtnNo < totcnt) {
			next = true;
		}else {//마지막 버튼 번호 다시 계산
			endPageBtnNo = (int)Math.ceil(totcnt/((double)listcnt));
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPgaeBtnNo!=1) {
			prev = true;
		}
		

		/********************모두 묶어서 컨트롤러에 리턴********************/
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("bList", bList);					//리스트
		pMap.put("prev", prev);						//이전버튼유무
		pMap.put("next", next);						//다음버튼유무
		pMap.put("startPgaeBtnNo", startPgaeBtnNo);	//시작버튼번호
		pMap.put("endPageBtnNo", endPageBtnNo);		//마지막버튼번호
		
		return pMap;
	}
	

	//------------게시판 리스트333(페이징+검색)-----------------
	public Map<String, Object> exeList3(int crtPage, String kwd){
		System.out.println("BoardService.exeList3()");
		
		/********************리스트 가져오기********************/
		//한페이지 출력갯수
		int listcnt = 10;
		//글 시작번호
		int startRowNo = (crtPage -1)*listcnt ;
		
		//두 데이터를 map으로 묶는다
		Map<String, Object> limitMap= new HashMap<String, Object>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listcnt", listcnt);;
		limitMap.put("kwd", kwd);
		
		//레파지토리로 보낸다
		List<BoardVO> bList = boardRepository.selectList3(limitMap);
		
		
		/********************페이징 버튼********************/
		
		//페이지당 버튼 갯수
		int pgaeBtncount = 5;

		//페이지의 마지막 버튼 번호
		int endPageBtnNo = ((int)Math.ceil(crtPage/((double)pgaeBtncount)))*pgaeBtncount;
		
		//페이지의 시작 버튼 번호
		int startPgaeBtnNo = (endPageBtnNo-pgaeBtncount)+ 1;
		
		//전체 건수
		int totcnt = boardRepository.selectTotalCount();
		//kwd건수
		int kTotcnt = boardRepository.selectKwdTotalCount(kwd);
		
		//다음 화살표 유무
		boolean next = false;
		if(listcnt*endPageBtnNo < kTotcnt) {
			next = true;
		}else {//마지막 버튼 번호 다시 계산
			endPageBtnNo = (int)Math.ceil(kTotcnt/((double)listcnt));
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPgaeBtnNo!=1) {
			prev = true;
		}
		

		/********************모두 묶어서 컨트롤러에 리턴********************/
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("bList", bList);					//리스트
		pMap.put("prev", prev);						//이전버튼유무
		pMap.put("next", next);						//다음버튼유무
		pMap.put("startPgaeBtnNo", startPgaeBtnNo);	//시작버튼번호
		pMap.put("endPageBtnNo", endPageBtnNo);		//마지막버튼번호
		
		return pMap;
	}
	
	//글삭제
	public int exeRemove(BoardVO boardVO) {
		System.out.println("BoardService.exeRemove()");
		
		int count  = boardRepository.deleteBoard(boardVO);
		
		return count;
	}
	
	//글쓰기
	public int exeWrite(BoardVO boardVO){
		System.out.println("BoardService.exeWrite()");
		
		int count = boardRepository.insertBoard(boardVO);
		
		return count;
	}
	
	//선택한 글 데이터 불러오기/수정데이터 불러오기
	public BoardVO exeRead(int no){
		System.out.println("BoardService.exeRead()");
		
		BoardVO boardVO = boardRepository.selectListOne(no);
		
		return boardVO;
	}
	
	//글수정
	public int exeEdit(BoardVO boardVO){
		System.out.println("BoardService.exeRead()");
		
		int count = boardRepository.updateBoard(boardVO);
		
		return count;
	}
	

	//게시판리스트전체
	public List<BoardVO> exeRelist(){
		System.out.println("BoardService.exeRelist()");
		
		List<BoardVO> bList = boardRepository.selectRelist();
		
		return bList;
	}
	
	//계층형 계시판 글 등록
	public int exeRewrite(BoardVO boardVO){
		System.out.println("BoardService.exeRewrite()");
		
		//본글 등록
		int count = boardRepository.insertReboard(boardVO);
		System.out.println(boardVO);
		
		//본글 groupNo 업데이트
		boardRepository.updateReboard(boardVO.getNo());
		System.out.println(boardVO);
		
		return count;
	}
	
	//계층형 계시판 댓글 등록
	public int exeRwrite(BoardVO boardVO){
		System.out.println("BoardService.exeRwrite()");
		
		//부모의 그룹넘, 오더넘, 뎁스를 받아온다
		
		//댓글등록시
		//1(부모글 오더넘  + 1) 과 같거나 큰 글들의 오더넘에 모두 +1 업데이트
		//2.부모의 그룹넘, (부모글 오더넘  + 1), (부모글 뎁스 + 1) 로 인서트 한다.		
		
		return 0;
	}

}
