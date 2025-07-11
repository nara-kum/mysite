package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVO;

//데이터로 응답하는 애들
@Controller
public class GuestbookApiController {

	@Autowired
	private GuestbookService guestbookService;

	//방명록 리스트
	@ResponseBody
	@RequestMapping(value = "/api/guestbook/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestVO> list() {
		System.out.println("GuestbookApiController.list()");
		
		List<GuestVO> gList = guestbookService.exeList();
		System.out.println(gList);

		return gList;
	}

	//방명록 추가
	@ResponseBody
	@RequestMapping(value = "/api/guestbook/add", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestVO add(@ModelAttribute GuestVO guestVO) {
		System.out.println("GuestbookApiController.add()");
		System.out.println(guestVO);
		
		//guestVO(3) --> gVO(4)
		GuestVO gVO = guestbookService.exeAddKey(guestVO);

		return gVO;
	}

	//방명록 삭제
	@ResponseBody
	@RequestMapping(value = "/api/guestbook/remove", method = { RequestMethod.GET, RequestMethod.POST })
	public int remove(@ModelAttribute GuestVO guestVO) {
		System.out.println("GuestbookApiController.remove()");
		System.out.println(guestVO);

		int count = guestbookService.exeRemove(guestVO);
		
		return count;
	}

}
