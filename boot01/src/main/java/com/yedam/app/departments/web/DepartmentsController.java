package com.yedam.app.departments.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.departments.service.DepartmentsService;
import com.yedam.app.departments.service.DepartmentsVO;

@Controller

public class DepartmentsController {
	
	@Autowired
	DepartmentsService departmentsService;
	
	// 전체조회
	@GetMapping("departmentsList")
	public String departmentsList(Model model) {
		List<DepartmentsVO> list = departmentsService.departmentsList();
		model.addAttribute(list);
		return "departments/list";
	}
	
	// 단건조회
	@GetMapping("departmentsInfo")
	public String departmentsInfo(DepartmentsVO departmentsVO, Model model) {
		DepartmentsVO findVO = departmentsService.departmentsInfo(departmentsVO);
		model.addAttribute(findVO);
		return "departments/Info";
	}
	
	// 등록
	@GetMapping("departmentsInsert")
	public String departmentsInsertForm() {
		return "departments/insert";
	}
	
	// 등록 - 처리
	@PostMapping("departmentsInsert")
	public String departmentsInsertProcess(DepartmentsVO departmentsVO) {
		int did = departmentsService.departmentsInsert(departmentsVO);
		String url = null;
		if(did > -1) {
			url = "redirect:departmentsInfo?departmentid=" + did;
		}else {
			url = "redirect:departmentsList";
		}
		return url;			
	}
}
