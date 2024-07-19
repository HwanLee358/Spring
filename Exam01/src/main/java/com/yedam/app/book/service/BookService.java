package com.yedam.app.book.service;

import java.util.List;

public interface BookService {
	// 전체조회
	public List<BookVO> bookList();
	// 다음번호 조회
	public int getBook();
	// 등록
	public int insertBook(BookVO bookVO);
	// 대여조회
	public List<RentVO> RentList();
}
