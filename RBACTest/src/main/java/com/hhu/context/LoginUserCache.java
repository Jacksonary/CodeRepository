package com.hhu.context;

import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.hhu.dto.Accordion;
import com.hhu.entity.User;

/*
 * 这里提供了一种本地缓存时间的设定
 * 
 * 问题处理：
 * 1.如何判断过期时间
 * 2.如何让关闭浏览器重新打开后仍然处于登录状态（甚至是关机）
 * 
 */
public class LoginUserCache {
	
	/* 方法1：已废弃，存在文件头的问题，故作方法2的优化
	private static Map<Long,LoginUser> cache = new HashMap<Long, LoginUser>();

	//expire表示超时时间
	public static void put(User user, long expire) {
		//设置缓存的时间，单位为秒
		long expireTime = Calendar.getInstance().getTime().getTime() + expire * 1000;
		LoginUser loginUser = new LoginUser();
		loginUser.setUser(user);
		loginUser.setExpire(expireTime);
		cache.put(user.getId(), loginUser);
	}
	
	public static void remove() {
		
	}
	
	private static class LoginUser {
		private long expire;
		private User user;
		public long getExpire() {
			return expire;
		}
		public void setExpire(long expire) {
			this.expire = expire;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
	}*/
	
	private static Map<String, User> cache = new HashMap<String, User>();
	private static Map<String,List<Accordion>> userAccordionMap = new HashMap<String, List<Accordion>>();
	
	public static void put(User user) {
		cache.put(user.getName(), user);
		UserContext.setCurrent(user);
		setCookie(user);
	}
	
	public static User get(String userName) {
		return cache.get(userName);
	}
	
	//将其放到Cookie中
	public static void setCookie(User user) {
		int expire = 1800;//单位为秒
		String source = user.getName() + "$" + user.getPwd();
		//jdk8中提供了Base64的工具类
		byte[] result = Base64.getEncoder().encode(source.getBytes());
		Cookie cookie = new Cookie("auth", new String(result));
		//设置Cookie的生存周期
		cookie.setMaxAge(expire);
		ResponseContext.getCurrent().addCookie(cookie);
	}
	
	public static void remove(String userName) {
		cache.remove(userName);
		Cookie cookie = new Cookie("auth",null);
		ResponseContext.getCurrent().addCookie(cookie);
		UserContext.setCurrent(null);
	}
	
	public static void setAccordions(String userName, List<Accordion> accordions) {
		userAccordionMap.put(userName, accordions);
	}
	
	public static List<Accordion> getAccordions(String userName) {
		return userAccordionMap.get(userName);
	}
	
}
