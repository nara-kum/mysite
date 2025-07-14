package com.javaex.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaex.vo.GuestVO;

@SpringBootTest
public class GuestbookServiceTest {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@Test
	public void delete() {
		GuestVO guestVO = new GuestVO();
		guestVO.setNo(1);
		guestVO.setPassword("1234");
		
		int count = guestbookService.exeRemove(guestVO);
		assertThat(count).isEqualTo(0);
	}

}
