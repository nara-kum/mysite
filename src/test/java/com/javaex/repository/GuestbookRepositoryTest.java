package com.javaex.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaex.vo.GuestVO;

@SpringBootTest
public class GuestbookRepositoryTest {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
	@Test
	public void selectAll() {
		List<GuestVO> gList = guestbookRepository.selectList();
		System.out.println("------------------");
		System.out.println(gList);
		System.out.println("------------------");
		
		assertThat(gList).isNotNull();
	}
	
}
