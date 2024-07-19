package com.yedam.app.book.service;

import lombok.Data;

@Data
public class RentVO {
	private Integer bookNo;//도서번호
	private String bookName;//도서명
	private int rentPrice;//대여총계
	private int rentCnt;//대여횟수
}
