package com.yedam.app.departments.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.departments.service.DepartmentsVO;

public interface DepartmentsMapper {
	//테이블을 기준
	//전체 조회
	public List<DepartmentsVO> selectDeparmentsAll();
	//단건 조회
	public DepartmentsVO selectDepartmentsInfo(DepartmentsVO departmentsVO);
	//등록
	public int insertDepartmentsInfo(DepartmentsVO departmentsVO);
	//수정
	public int updateDepartmentsInfo(@Param("id") int departmentId, @Param("departments")DepartmentsVO departmentsVO);
	//삭제
	public int deleteDepartmentsInfo(int departmentId);
}
