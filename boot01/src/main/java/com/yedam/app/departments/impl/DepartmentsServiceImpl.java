package com.yedam.app.departments.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.departments.mapper.DepartmentsMapper;
import com.yedam.app.departments.service.DepartmentsService;
import com.yedam.app.departments.service.DepartmentsVO;

@Service

public class DepartmentsServiceImpl implements DepartmentsService{

	@Autowired // 필드 주입
	private DepartmentsMapper departmentsMapper;
	
	@Override
	public List<DepartmentsVO> departmentsList() {
		return departmentsMapper.selectDeparmentsAll();
	}

	@Override
	public DepartmentsVO departmentsInfo(DepartmentsVO departmentsVO) {
		return departmentsMapper.selectDepartmentsInfo(departmentsVO);
	}

	@Override
	public int departmentsInsert(DepartmentsVO departmentsVO) {
		int result = departmentsMapper.insertDepartmentsInfo(departmentsVO);
		return result == 1 ? departmentsVO.getDepartmentId() : -1;
	}

	@Override
	public Map<String, Object> departmentsUpdate(DepartmentsVO departmentsVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = departmentsMapper.updateDepartmentsInfo(departmentsVO.getDepartmentId(), departmentsVO);
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", departmentsVO);
		return map;
	}

	@Override
	public Map<String, Object> departmentsdelate(DepartmentsVO departmentsVO) {
		Map<String, Object> map = new HashMap<>();
		
		int result = departmentsMapper.deleteDepartmentsInfo(departmentsVO.getDepartmentId());
		if(result == 1) {
			map.put("departmentId", departmentsVO.getDepartmentId());
		}
		
		return map;
	}

}
