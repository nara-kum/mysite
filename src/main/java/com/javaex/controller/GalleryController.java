package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.util.JsonResult;
import com.javaex.vo.GalleryVO;
import com.javaex.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	//리스트 가져오기
	@RequestMapping(value = "/gallery", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GalleryController.list()");
		
		List<GalleryVO> galleryVO = galleryService.exeList();
		System.out.println(galleryVO);
		
		model.addAttribute("galleryVO",galleryVO);
		
		return "gallery/list";
		
	}
	
	//리스트 등록
	@RequestMapping(value = "/gallery/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam(value = "file") MultipartFile file, 
						 GalleryVO galleryVO,
						 HttpSession session,
						 Model model) {
		System.out.println("GalleryController.upload()");
		
		//세션에서 userId 꺼내기
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		
		//GalleryVO에 userId담기
		galleryVO.setUserNo(userNo);
		System.out.println(galleryVO);
		
		galleryService.exeUpload(file, galleryVO);
		
		return "redirect:/gallery";
	}

	//이미지 삭제
	@ResponseBody
	@RequestMapping(value = "/gallery/remove/{no}", method = {RequestMethod.DELETE})
	public JsonResult remove(@PathVariable(value = "no") int no) {
		System.out.println("GalleryController.remove()");
		
		int count = galleryService.exeRemove(no);

		return JsonResult.success(count);
		
	}

}
