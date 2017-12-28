package com.hhu.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.entity.Function;
import com.hhu.entity.Role;
import com.hhu.service.FunctionService;

/*
 * 将用户的一些权限信息进行本地缓存，集群的情况不适用
 */
@Service
public class NativeCache {
	
	private Map<Long, Function> functionMap = new HashMap<>();
	
	private Map<Long,List<Role>> userRoleMap = new HashMap<>();
	
	@Autowired
	private FunctionService functionService;
	
	@PostConstruct//表示在初始化所在类时首先调用这个方法
	public void init() {
		List<Function> functionList = functionService.getAllFunctions();
		functionList.forEach((function)->functionMap.put(function.getId(), function));
	}
	
	public List<Function> getFunctions() {
		if(functionMap.isEmpty()) {
			init();
		}
		return new ArrayList<Function>(functionMap.values());
	}
	
	//解决缓存更新的问题
	public void addFunction(Function function) {
		functionMap.put(function.getId(), function);
	}
	
	public void replaceFunction(Function function) {
		if(functionMap.containsKey(function.getId())) {
			functionMap.remove(function.getId());
			functionMap.put(function.getId(), function);
		}
	}
	
	public void removeFunction(Long functionId) {
		if(functionMap.containsKey(functionId)) {
			functionMap.remove(functionId);
		}
	}
	
	public void setRoles(Long userId, List<Role> roles) {
		userRoleMap.put(userId, roles);
	}
	
	public List<Role> getRoles(Long userId) {
		return userRoleMap.get(userId);
	}
	
	public Function getFunction(Long id) {
		return functionMap.get(id);
	}

}
