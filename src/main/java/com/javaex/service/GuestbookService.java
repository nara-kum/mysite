package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.repository.GuestbookRepository;
import com.javaex.vo.GuestVO;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	//불러오기
	public List<GuestVO> exeList() {
		System.out.println("guestbookService.exeAddList()");
		
		List<GuestVO> gList = guestbookRepository.selectList();

		return gList;
		
	}
	
	//저장
	public int exeAdd(GuestVO guestVO) {
		System.out.println("GuestbookService.exeAdd()");
		
		int count = guestbookRepository.insertGuest(guestVO);
		
		return count;
		
	}
	
	//저장 하고 키값 가져와
	public GuestVO exeAddKey(GuestVO guestVO) {
		System.out.println("GuestbookService.exeAddKey()");

		//저장
		int count = guestbookRepository.insertKeyGuest(guestVO);
		
		//추가된 방명록 가져오기
		GuestVO gVO = guestbookRepository.selectOneGuest(guestVO.getNo());
		
		return gVO;
		
	}
	
	//삭제
	public int exeRemove(GuestVO guestVO) {
		System.out.println("GuestbookService.exeRemove()");
		
		int count = guestbookRepository.deleteGuest(guestVO);
		
		return count;
		
	}

}
