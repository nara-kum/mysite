package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// 회원가입폼
	@RequestMapping(value = "/user/joinform", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("UserController.joinForm()");

		return "user/joinform";

	}

	// 회원가입
	@RequestMapping(value = "/user/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVO userVO) {
		System.out.println("UserController.join()");

		try {
			userService.exeJoin(userVO);

			return "redirect:joinok";
		} catch (Exception e) {

			return "redirect:joinform";
		}

	}

	// 회원가입성공
	@RequestMapping(value = "/user/joinok", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinOk() {
		System.out.println("UserController.joinok()");

		return "user/joinok";

	}

	// 로그인폼
	@RequestMapping(value = "/user/loginform", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController.loginform()");

		return "user/loginform";
	}

	// 로그인
	@RequestMapping(value = "/user/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVO userVO, HttpSession session) {
		System.out.println("UserController.login()");
		
		UserVO authUser = userService.exeLogin(userVO);
		System.out.println(authUser);
		
		//세션에 로그인 확인용 값을 넣어준다(id/pw가 들어있는 주소)-->로그인
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}
	
	//로그아웃
	@RequestMapping(value = "/user/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("UserController.logout");
		
		//authUser만 날림
		//session.removeAttribute("authUser");
		
		//전부 날림
		session.invalidate();

		return "redirect:/";
	}

	// 회원정보수정폼
	@RequestMapping(value = "/user/editform", method = { RequestMethod.GET, RequestMethod.POST })
	public String editForm(HttpSession session, Model model) {
		System.out.println("UserController.editform()");

		//세션에서 꺼내기
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		//수정
		UserVO userVO = userService.exeEditData(no);

		model.addAttribute("userVO", userVO);

		return "user/editform";
	}

	// 회원정보수정
	@RequestMapping(value = "/user/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(@ModelAttribute UserVO userVO, HttpSession session) {
		System.out.println("UserController.edit()");
		
		//세션에서 꺼내기
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		//userVO에 세션에서 꺼낸 no추가
		userVO.setNo(no);
		userService.exeEdit(userVO);

		authUser = userService.exeLogin(userVO);
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}

}
