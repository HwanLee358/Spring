package com.yedam.app.departments.service;

import java.util.List;
import java.util.Map;

// 사용자에게 제공하는 기능
public interface DepartmentsService {
	// 전체 부서정보 조회
	public List<DepartmentsVO> departmentsList();
	// 부서정보 조회
	public DepartmentsVO departmentsInfo(DepartmentsVO departmentsVO);
	// 부서정보 등록
	public int departmentsInsert(DepartmentsVO departmentsVO);
	// 부서정보 수정
	public Map<String, Object> departmentsUpdate(DepartmentsVO departmentsVO);
	// 부서정보 삭제
	public Map<String, Object> departmentsdelate(DepartmentsVO departmentsVO);
}
