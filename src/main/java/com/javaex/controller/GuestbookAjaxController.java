package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GuestbookAjaxController {
	
	@RequestMapping(value = "/guestbook/ajaxguestbook", method = {RequestMethod.GET, RequestMethod.POST})
	public String ajaxIndex() {
		System.out.println("GuestbookAjaxController.ajaxIndex()");
		
		return "ajaxjuestbook/index";
	}

}
