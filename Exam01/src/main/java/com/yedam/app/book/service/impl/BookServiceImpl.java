package com.yedam.app.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.book.mapper.BookMapper;
import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;
import com.yedam.app.book.service.RentVO;

@Service
public class BookServiceImpl implements BookService{
	
	private BookMapper bookMapper;
	@Autowired
	BookServiceImpl(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}
	
	@Override
	public int getBook() {
		// TODO Auto-generated method stub
		return bookMapper.getBook();
	}
	
	@Override
	public List<BookVO> bookList() {
		return bookMapper.selectBookAll();
	}

	@Override
	public int insertBook(BookVO bookVO) {
		return bookMapper.insertBookInfo(bookVO);
	}

	@Override
	public List<RentVO> RentList() {
		return bookMapper.selectRentAll();
	}

	

}
