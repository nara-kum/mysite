package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	//필드
	//생성자
	//메소드-gs
	
	//메소드
	
	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		System.out.println("MainController.index()");
		
		return "main/index";
	}
	
//	@RequestMapping(value="/{id}", method = {RequestMethod.GET, RequestMethod.POST})
//	public String index2(@PathVariable(value = "id") String id) {
//		System.out.println("MainController.index2()");
//		
//		System.out.println(id+"회원");
//		
//		return "main/index";
//	}

}
