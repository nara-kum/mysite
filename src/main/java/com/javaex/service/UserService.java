package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.repository.UserRepository;
import com.javaex.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	//회원가입(저장)
	public int exeJoin(UserVO userVO) {
		System.out.println("UserService.exeJoin()");

		int count = userRepository.userInsert(userVO);

		return count;

	}
	
	//회원정보 수정폼(1명정보)
	public UserVO exeEditData(int no) {
		System.out.println("UserService.exeEditData()");
		
		UserVO userVO = userRepository.userSelectOne(no);
		
		return userVO;

	}
	
	//회원정보 수정
	public int exeEdit(UserVO userVO) {
		System.out.println("UserService.exeEdit()");
		
		int count = userRepository.userUpdate(userVO);

		return count;

	}

	//로그인
	public UserVO exeLogin(UserVO userVO) {
		System.out.println("UserService.exeLogin()");
		
		UserVO authUser = userRepository.userSelectOneByIdPw(userVO);
		
		return authUser;

	}

}
