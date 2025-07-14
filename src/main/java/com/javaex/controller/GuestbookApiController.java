package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.util.JsonResult;
import com.javaex.vo.GuestVO;

//데이터로 응답하는 애들
//@Controller
@RestController
public class GuestbookApiController {

	@Autowired
	private GuestbookService guestbookService;

	//방명록 리스트
//	@ResponseBody
//	@GetMapping(value = "/api/guestbooks")
	@GetMapping("/api/guestbooks")
	public JsonResult list() {
		System.out.println("GuestbookApiController.list()");
		
		List<GuestVO> gList = guestbookService.exeList();
		
		if(gList != null) {
			return JsonResult.success(gList);
		}else {
			return JsonResult.fail("알 수 없는 오류");
		}
	}

	//방명록 추가
//	@ResponseBody
	@PostMapping("/api/guestbooks")
	public JsonResult add(@ModelAttribute GuestVO guestVO) {
		System.out.println("GuestbookApiController.add()");
		
		//guestVO(3) --> gVO(4)
		GuestVO gVO = guestbookService.exeAddKey(guestVO);
		
		if(gVO != null) {
			return JsonResult.success(gVO);
		}else {
			return JsonResult.fail("등록 실패");
		}
	}

	//방명록 삭제
//	@ResponseBody
	@DeleteMapping("/api/guestbooks/{no}")
	public JsonResult remove(@ModelAttribute GuestVO guestVO, @PathVariable(value = "no") int no) {
		System.out.println("GuestbookApiController.remove()");

		//guestVO는 패스워드값만 넘어옴
		System.out.println(guestVO);
		//따로 넘어온 no값 확인
		System.out.println(no);
		//guestVO에 no값 넣어줌
		guestVO.setNo(no);
		System.out.println(guestVO);
		
		int count = guestbookService.exeRemove(guestVO);
		
		if(count==1) {
			return JsonResult.success(count);
		}else {
			return JsonResult.fail("잘못된 패스워드");
		}
	
	}

}
