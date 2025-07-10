package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	// 회원가입폼
	@RequestMapping(value = "/joinform", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("UserController.joinForm()");

		return "user/joinform";

	}
	
	//회원가입폼-아이디 중복체크 -->데이터만 응답
	@ResponseBody
	@RequestMapping(value = "/idcheck", method = { RequestMethod.GET, RequestMethod.POST })
	public String idCheck(@RequestParam(value="id") String id) {
		System.out.println("UserController.idcheck()");
		
		boolean isUse = userService.exeIdcheck(id);
		System.out.println(isUse);
		
		//response body에 보내줘야 함
		String result = "{\"isUse\":" +isUse+ "}" ;
		System.out.println(result);
		
		//jsp에 전달하는 것임
		//model.addAttribute("isUse",isUse);
		
		return result;
	}
	

	// 회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
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
	@RequestMapping(value = "/joinok", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinOk() {
		System.out.println("UserController.joinok()");

		return "user/joinok";

	}

	// 로그인폼
	@RequestMapping(value = "/loginform", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController.loginform()");

		return "user/loginform";
	}

	// 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVO userVO, HttpSession session) {
		System.out.println("UserController.login()");
		
		UserVO authUser = userService.exeLogin(userVO);
		System.out.println(authUser);
		
		//세션에 로그인 확인용 값을 넣어준다(id/pw가 들어있는 주소)-->로그인
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}
	
	//로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("UserController.logout");
		
		//authUser만 날림
		//session.removeAttribute("authUser");
		
		//전부 날림
		session.invalidate();

		return "redirect:/";
	}

	// 회원정보수정폼
	@RequestMapping(value = "/editform", method = { RequestMethod.GET, RequestMethod.POST })
	public String editForm(HttpSession session, Model model) {
		System.out.println("UserController.editform()");

		//세션값 가져오기
		UserVO authUser = (UserVO)session.getAttribute("authUser");

		//로그인 여부 체크
		if(authUser==null) {
			return "redirect:/";
		}else {
			
			//no값 꺼내기
			int no = authUser.getNo();
			
			//수정
			UserVO userVO = userService.exeEditData(no);

			model.addAttribute("userVO", userVO);

			return "user/editform";
			
		}
		
	}

	// 회원정보수정
	@RequestMapping(value = "/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(@ModelAttribute UserVO userVO, HttpSession session) {
		System.out.println("UserController.edit()");
		
		//세션에서 꺼내기
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		//userVO에 세션에서 꺼낸 no추가
		userVO.setNo(no);
		userService.exeEdit(userVO);

		authUser.setName(userVO.getName());
		
		return "redirect:/";
	}

}
