package com.hhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.dao.FunctionDao;
import com.hhu.entity.Function;

@Service
public class FunctionService {

	@Autowired
	private FunctionDao functionDao;
	
	public void addFunction(Function function) {
		functionDao.save(function);
	}
	
	public void updateUrl(Long id, String url) {
		functionDao.update(id, url);
	}
	
	public List<Function> getFunctions(int page, int size, Long parentId) {
		return functionDao.findFunctions(page, size, parentId);
	}
	
	public void deleteFunctionById(Long id) {
		functionDao.deleteById(id);
	}
	
	public List<Function> getAllFunctions() {
		return functionDao.getAllFunctions();
	}
	
}
