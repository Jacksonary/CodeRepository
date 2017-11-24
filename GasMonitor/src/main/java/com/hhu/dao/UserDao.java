package com.hhu.dao;

import com.hhu.entity.User;

public interface UserDao {

	public User getUser(String userName, String password);
	
}
