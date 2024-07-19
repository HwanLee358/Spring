package com.yedam.app.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;
import com.yedam.app.book.service.RentVO;

@Controller
public class BookController {
	
	private BookService bookService;
	
	@Autowired
	public BookController (BookService bookService) {
		this.bookService = bookService;
	}
	
	// 전체 조회
	@GetMapping("bookList")
	public String bookList(Model model) {
		List<BookVO> list = bookService.bookList();
		model.addAttribute("bookList", list);
		return "book/bookList";
	}
	
	// 등록
	@GetMapping("bookInsert")
	public String bookInsertForm(Model model) {
		int bno = bookService.getBook();
		BookVO bookVO = new BookVO();
		bookVO.setBookNo(bno);
		model.addAttribute("bookForm", bookVO);
		return "book/bookInsert";
	}
	// 등록 - 처리
	@PostMapping("bookInsert")
	public String bookInsertProcess(BookVO bookVO) {
		bookService.insertBook(bookVO);
		return "redirect:bookList";
	}
	
	//도서 대여조회
	@GetMapping("RentList")
	public String RentList(Model model) {
		List<RentVO> list = bookService.RentList();
		model.addAttribute("RentList", list);
		return "book/RentList";
	}
}
