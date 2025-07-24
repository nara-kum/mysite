package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;

@Controller
public class AttachController {

	@Autowired
	private AttachService attachService;

	// 파일업로드폼
	@RequestMapping(value = "/attach/form", method = { RequestMethod.GET, RequestMethod.POST })
	public String form() {
		System.out.println("AttachController.form()");

		return "attach/form";
	}

	// 업로드(보내준 파일을 받아서 저장)
	@RequestMapping(value = "/attach/upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String upload(@RequestParam(value = "file") MultipartFile file, Model model) {
		System.out.println("AttachController.upload()");
		
		String saveName = attachService.exeUpload(file);
		model.addAttribute("saveName",saveName);

		return "attach/result";
	}

	// 업로드결과
	@RequestMapping(value = "/attach/result", method = { RequestMethod.GET, RequestMethod.POST })
	public String result() {
		System.out.println("AttachController.result()");

		return "attach/result";
	}

}
