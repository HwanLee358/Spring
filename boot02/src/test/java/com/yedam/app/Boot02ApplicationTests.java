package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;

@SpringBootTest
class Boot02ApplicationTests {
	@Autowired
	BoardMapper boardMapper;
	
	@Test
	void selectAll() {
		List<BoardVO> list = boardMapper.selectBoardAll();
	}
	
	@Test
	void contextLoads() {
	}

}
