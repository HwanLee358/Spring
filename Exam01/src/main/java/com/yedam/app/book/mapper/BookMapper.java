package com.yedam.app.book.mapper;

import java.util.List;

import com.yedam.app.book.service.BookVO;
import com.yedam.app.book.service.RentVO;

public interface BookMapper {
	// 전체 조회
	public List<BookVO> selectBookAll();
	// 번호
	public int getBook();
	// 등록
	public int insertBookInfo(BookVO bookVO);
	//대여 조회
	public List<RentVO> selectRentAll();
}
