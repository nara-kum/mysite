package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	private UserService serService;
	
	//회원가입폼
	@RequestMapping(value = "/user/joinform", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "user/joinform";
		
	}
	
	//회원가입
	@RequestMapping(value = "/user/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVO userVO) {
		System.out.println("UserController.join()");
		
		serService.exeJoin(userVO);
		
		return "redirect:joinok";
		
	}

	//회원가입성공폼
	@RequestMapping(value = "/user/joinok", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinOk() {
		System.out.println("UserController.joinok()");
		
		return "user/joinok";
		
	}
}
