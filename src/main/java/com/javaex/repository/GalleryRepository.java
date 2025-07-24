package com.javaex.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVO;

@Repository
public class GalleryRepository {
	
	@Autowired
	private SqlSession sqlSession; 
	
	//리스트 가져오기
	public List<GalleryVO> selectGallery(){
		System.out.println("GalleryRepository.selectGallery()");
		
		List<GalleryVO> galleryVO = sqlSession.selectList("gallery.selectList");
		
		return galleryVO;
	}
	
	//사진 등록
	public int insertGallery(GalleryVO galleryVO) {
		System.out.println("GalleryRepository.insertGallery()");
		
		int count = sqlSession.insert("gallery.insertKey",galleryVO);
		
		return count;
	}
	
	//작성자 이름 가져오기
	public String selectNo(int no) {
		System.out.println("GalleryRepository.selectNo()");
		
		String writeName = sqlSession.selectOne("gallery.selectOne", no);
		
		return writeName;
	}
	
	//데이터 삭제
	public int deleteGallery(int no) {
		System.out.println("GalleryRepository.deleteGallery()");
		
		int count = sqlSession.delete("gallery.delete", no);
		
		return count;
	}

}
