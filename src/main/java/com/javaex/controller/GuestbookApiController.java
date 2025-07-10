package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@ResponseBody
	@RequestMapping(value = "/api/guestbook/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestVO> list() {
		System.out.println("GuestbookApiController.list()");
		
		List<GuestVO> gList = guestbookService.exeList();
		System.out.println(gList);

		return gList;
	}

}
