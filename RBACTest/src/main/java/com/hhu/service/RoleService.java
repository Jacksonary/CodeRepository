package com.hhu.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.dao.RoleDao;
import com.hhu.dao.RoleFunctionDao;
import com.hhu.entity.Role;
import com.hhu.entity.RoleFunction;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleFunctionDao roleFunctionDao;
	
	public void addRole(Role role, Collection<RoleFunction> roleFunctions) {
		roleDao.save(role);
		roleFunctions.forEach((rf)->rf.setRoleId(role.getId()));
		roleFunctionDao.saveRoleFunctions(roleFunctions);
	}
	
	public void editRole(Role role, Collection<RoleFunction> roleFunctions) {
		//首先更新角色
		roleDao.update(role);
		//将关联关系删除
		roleFunctionDao.deleteByRoleId(role.getId());
		//这个是1.8之后的forEach的新写法
		roleFunctions.forEach((rf)->rf.setRoleId(role.getId()));
		roleFunctionDao.saveRoleFunctions(roleFunctions);
	}
	
	//再删除角色的同时还要将功能和角色的对应关系删除掉
	public void deleteRole(Long roleId) {
		roleDao.deleteById(roleId);
		roleFunctionDao.deleteByRoleId(roleId);
	}
	
	public List<Role> getRoles(int page, int size) {
		List<Role> roles = roleDao.findRoles(page, size);
		roles.forEach((role)->{
			List<RoleFunction> roleFunctions = roleFunctionDao.findRoleFunctionsByRoleId(role.getId());
			StringBuilder functionIds = new StringBuilder();
			roleFunctions.forEach((rf)->{
				functionIds.append(rf.getFunctionId()).append(",");
			});
			
			if(functionIds.length()>1) {
				role.setFunctionIds(functionIds.deleteCharAt(functionIds.length()-1).toString());
			}
		});
		return roles;
	}
	
	public List<Role> getRoles(Collection<Long> ids) {
		return roleDao.find(ids);
	}
	
	public List<RoleFunction> getRolesFunctions(Long roleId) {
		return roleFunctionDao.findRoleFunctionsByRoleId(roleId);
	}

}
