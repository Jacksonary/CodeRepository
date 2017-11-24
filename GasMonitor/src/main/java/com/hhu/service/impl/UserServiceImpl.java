package com.hhu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.dao.UserDao;
import com.hhu.entity.User;
import com.hhu.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUser(String userName, String password) {
		return userDao.getUser(userName, password);
	}

}
