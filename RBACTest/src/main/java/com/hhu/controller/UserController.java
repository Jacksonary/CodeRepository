package com.hhu.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hhu.common.AjaxResult;
import com.hhu.entity.User;
import com.hhu.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String userList() {
		return "/security/user/user_list";
	}
	
	@RequestMapping(value="/addEditUser",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addEditUser(User user) {
		if(null==user.getId()) {
			userService.addUser(user);
		} else {
			userService.updateUser(user);
		}
		return AjaxResult.success();
	}
	
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult deleteUser(Long id) {
		userService.deleteUserById(id);
		return AjaxResult.success();
	}
	
	@RequestMapping(value="/getUser",method=RequestMethod.POST)
	@ResponseBody
	public Collection<User> getUsers(Integer page, Integer size) {
		return userService.getUsers(page, size);
	}

}
