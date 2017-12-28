package com.hhu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.dao.UserDao;
import com.hhu.dao.UserRoleDao;
import com.hhu.entity.User;
import com.hhu.entity.UserRole;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	public void addUser(User user) {
		userDao.save(user);
	}
	
	public void updateUser(User user) {
		userDao.update(user);
	}
	
	public void deleteUserById(Long id) {
		userDao.deleteById(id);
	}
	
	public User getUser(String name, String pwd) {
		return userDao.getUser(name, pwd);
	}
	
	public Collection<User> getUsers(int page, int size) {
		return userDao.findUsers(page, size);
	}
	
	public Collection<User> getUsers(Collection<Long> ids) {
		return userDao.find(ids);
	}
	
	public List<UserRole> getUserRoles(Integer page, Integer rows) {
		return userRoleDao.findUserRoles(page, rows);
	}
	
	public List<UserRole> getUserRoleByUserId(Long userId) {
		return userRoleDao.findByUserId(userId);
	}
	
	public void addUserRoles(Long userId, Long[] roleIds) {
		List<UserRole> userRoles = new ArrayList<UserRole>();
		
		Arrays.asList(roleIds).forEach((roleId)->{
			UserRole userRole = new UserRole();
			userRole.setRole(roleId);
			userRole.setUserId(userId);
			userRoles.add(userRole);
		});
		userRoleDao.saveUserRoles(userRoles);
	}
	
}
