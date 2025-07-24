package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.repository.GalleryRepository;
import com.javaex.vo.GalleryVO;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryRepository galleryRepository;
	
	//리스트 가져오기
	public List<GalleryVO> exeList(){
		System.out.println("GalleryService.exeList()");
		
		List<GalleryVO> galleryVO = galleryRepository.selectGallery();
		System.out.println(galleryVO);
		
		return galleryVO;
		
	}
	
	//리스트 등록
	public String exeUpload(MultipartFile file, GalleryVO galleryVO) {
		System.out.println("GalleryService.exeUpload()");
		
		//현재 os 정보
		String osName = System.getProperty("os.name").toLowerCase();
		
		//파일저장경로
		String savaDir = "";
		
		if(osName.contains("win")) {//윈도우면
			savaDir = "C:\\javaStudy\\galleryupload\\";
		}else {//리눅스면
			savaDir = "/data/upload/";
		}
		
		//1.파일정보 추출 저장(DB)
		//오리지널 파일명
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		
		String exName = orgName.substring(orgName.lastIndexOf(".")+1);
		System.out.println(exName);
		
		//저장 파일명(겹치지 않는 파일명 - 덮어쓰기 방지용)
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+"."+exName;
		System.out.println(saveName);
		
		//파일 경로
		String filePath = savaDir+saveName;
		System.out.println(filePath);
		
		//파일 사이즈
		long fileSize = file.getSize();
		
		//VO에 묶는다
		galleryVO.setFilePath(filePath);
		galleryVO.setOrgName(orgName);
		galleryVO.setSaveName(saveName);
		galleryVO.setFileSize(fileSize);
		
		System.out.println(galleryVO);

		//db에 저장
		int count = galleryRepository.insertGallery(galleryVO);

		//이름 조회후 리턴
		String writeName = galleryRepository.selectNo(galleryVO.getNo());
		System.out.println(writeName);

		//2.실물 파일을 하드디스크에 저장
		
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
	}
	
	//삭제
	public int exeRemove(int no) {
		System.out.println("GalleryService.exeRemove()");
		
		int count = galleryRepository.deleteGallery(no);
		
		return count;
	}

}
