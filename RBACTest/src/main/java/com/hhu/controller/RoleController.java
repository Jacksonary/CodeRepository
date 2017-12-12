package com.hhu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hhu.common.AjaxResult;
import com.hhu.entity.Role;
import com.hhu.entity.RoleFunction;
import com.hhu.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("index")
	public String index() {
		return "security/role/role_list";
	}
	
	@RequestMapping(value="/getRoles",method=RequestMethod.POST)
	@ResponseBody//这个注解一般用于ajax的异步请求，不会被解析为路径跳转
	public List<Role> getRoles(Integer page, Integer size) {
		return roleService.getRoles(page, size);
	}
	
	@RequestMapping(value="/addEditRole", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addEditRole(Role role) {
		String functionIds = role.getFunctionIds();
		String[] idArray = functionIds.split(",");
		List<RoleFunction> rfList = new ArrayList<RoleFunction>();
		for(int i=0;i<idArray.length;i++) {
			RoleFunction rf = new RoleFunction();
			rf.setFunctionId(Long.valueOf(idArray[i]));
			rf.setStatus(1);
			rfList.add(rf);
		}
		if(null==role.getId()) {
			roleService.addRole(role, rfList);
		} else {
			roleService.editRole(role, rfList);
		}
		return AjaxResult.success();
	}
	
	@RequestMapping(value="/deleteRole",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult deleteRole(Long id) {
		roleService.deleteRole(id);
		return AjaxResult.success();
	}
	
}
