package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.FileVO;

@Service
public class AttachService {
	
	public String exeUpload(MultipartFile file) {
		System.out.println("AttachService.exeUpload()");
		
		String savaDir = "C:\\javaStudy\\upload\\";
		
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
		System.out.println(fileSize);
		
		//VO에 묶는다
		FileVO fileVO = new FileVO(orgName,exName,saveName,filePath,fileSize);
		System.out.println(fileVO);
		
		//db에 저장
		
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

}
