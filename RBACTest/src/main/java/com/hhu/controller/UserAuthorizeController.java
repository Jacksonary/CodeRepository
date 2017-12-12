package com.hhu.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hhu.base.BaseEntity;
import com.hhu.common.AjaxResult;
import com.hhu.dto.Authorize;
import com.hhu.entity.Role;
import com.hhu.entity.User;
import com.hhu.entity.UserRole;
import com.hhu.service.RoleService;
import com.hhu.service.UserService;

/**
 * 授权控制器
 * @author Weiguo Liu
 * @data 2017年12月11日
 */
@RestController
@RequestMapping("/authorize")
public class UserAuthorizeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/index")
	public String index() {
		return "/security/authorize/authorize_list";
	}
	
	@RequestMapping("/userRole")
	public String authorizeIndex() {
		return "/security/authorize/user_role_list";
	}
	
	@RequestMapping(value="/getAuthorizes",method=RequestMethod.POST)
	@ResponseBody
	public List<Authorize> getAuthorizes(Integer page, Integer rows) {
		List<UserRole> userRoles = userService.getUserRoles(page, rows);
		Collection<Long> userIds = new HashSet<>();
		Collection<Long> roleIds = new HashSet<>();
		userRoles.forEach((ur)->{
			userIds.add(ur.getUserId());
			roleIds.add(ur.getRole());
		});
		
		Collection<User> users = userService.getUsers(userIds);
		List<Role> roles = roleService.getRoles(roleIds);
		
		Map<Long,User> userMap = BaseEntity.idEntityMap(users);
		Map<Long,Role> roleMap = BaseEntity.idEntityMap(roles);
		
		List<Authorize> authorizes = new LinkedList<>();
		userRoles.forEach((ur)-> {
			Authorize authorize = new Authorize();
			authorize.setUserRoleId(ur.getId());
			authorize.setUserId(ur.getUserId());
			authorize.setUserName(userMap.get(ur.getUserId()).getName());
			authorize.setRoleId(ur.getRole());
			authorize.setRoleName(roleMap.get(ur.getRole()).getName());
			authorizes.add(authorize);
		});
		return authorizes;
	}
	
	@RequestMapping(value="/getUserRoleByUserId",method=RequestMethod.POST)
	@ResponseBody
	public List<UserRole> getUserRoleByUserId(Long userId) {
		return userService.getUserRoleByUserId(userId);
	}
	
	public AjaxResult setAuthorize(User user, String roleIds) {
		String[] temp = roleIds.split(",");
		Long[] roleIdArray = new Long[temp.length];
		for(int i=0;i<roleIdArray.length;i++) {
			roleIdArray[i] = Long.valueOf(temp[i]); 
		}
		userService.addUserRoles(user.getId(), roleIdArray);
		return AjaxResult.success();
	}
	
}
