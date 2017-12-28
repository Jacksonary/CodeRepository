package com.hhu.context;

import com.hhu.entity.User;

public class UserContext {
	
	private static ThreadLocal<UserContext> tl = new ThreadLocal<>();
	
	private User user;

	public UserContext(User user) {
		super();
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	static void setCurrent(User user) {
		tl.set(new UserContext(user));
	}
	
	public static UserContext getCurrent() {
		return tl.get();
	}
	

}
