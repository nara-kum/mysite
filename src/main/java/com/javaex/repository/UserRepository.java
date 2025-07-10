package com.javaex.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVO;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입
	public int userInsert(UserVO userVO) {
		System.out.println("UserRepository.userInsert()");
		
		int count = sqlSession.insert("user.insert",userVO);
		
		return count;
	}
	
	//아이디 중복체크
	public UserVO userSelectById(String id){
		System.out.println("UserRepository.userSelectById()");
		
		UserVO userVO = sqlSession.selectOne("user.SelectById",id);
		
		return userVO;
	}
	
	//회원정보 수정폼(1명데이터)
	public UserVO userSelectByNo(int no) {
		System.out.println("UserRepository.userSelectByNo()");
		
		UserVO userVO = sqlSession.selectOne("user.selectByNo",no);
		
		return userVO;
		
	}
	
	//회원정보 수정
	public int userUpdate(UserVO userVO) {
		System.out.println("UserRepository.userUpdate()");

		int count = sqlSession.update("user.update",userVO);
		
		return count;
		
		
	}

	//로그인
	public UserVO userSelectOneByIdPw(UserVO userVO) {
		System.out.println("UserRepository.userSelectOneByIdPw()");
		
		UserVO authUser = sqlSession.selectOne("user.SelectOneByIdPw",userVO);
		
		return authUser;

	}
}
