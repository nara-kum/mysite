package com.javaex.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVO;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	//불러오기
	public List<GuestVO> selectList() {
		System.out.println("guestbookRepository.selectList()");

		List<GuestVO> gList = sqlSession.selectList("guest.selectList");
		System.out.println(gList);
		
		return gList;
		
	}
	
	//저장
	public int insertGuest(GuestVO guestVO) {
		
		System.out.println("uestbookRepository.insertGuest()");
		
		int count = sqlSession.insert("guest.insert",guestVO);
		
		return count;
	}
	
	//저장 하고 키값 가져와
	public int insertKeyGuest(GuestVO guestVO) {
		System.out.println("uestbookRepository.insertKeyGuest()");
		
		int count = sqlSession.insert("guest.insertKey",guestVO);
		
		return count;
	}
	
	//추가된 방명록 가져와
	public GuestVO selectOneGuest(int no) {
		System.out.println("uestbookRepository.selectOneGuest()");
		
		GuestVO guestVO = sqlSession.selectOne("guest.selectOne",no);
		
		return guestVO;
	}

	//삭제
	public int deleteGuest(GuestVO guestVO) {
		System.out.println("guestbookRepository.deleteGuest()");
		
		int count = sqlSession.delete("guest.delete",guestVO);
		
		return count;
	}
	

}
