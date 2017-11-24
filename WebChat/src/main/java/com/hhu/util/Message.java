package com.hhu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

/**
 * 消息包装类
 * @author Weiguo Liu
 *
 */
public class Message {
	private String welcom;
	
	private List<String> usernames;
	
	private String content;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void setContent(String name,String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		this.content = sdf.format(new Date()) + "<br/>" + name + ":" + msg + "<br/>";
	}

	public String getWelcom() {
		return welcom;
	}

	public void setWelcom(String welcom) {
		this.welcom = welcom;
	}

	public List<String> getUsernames() {
		return usernames;
	}

	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}
	
	public Message() {
		super();
	}
	
	private static Gson gson = new Gson();
	
	public String toJson() {
		return gson.toJson(this);
	}
	
	@Override
	public String toString() {
		return "Message [welcom=" + welcom + ", usernames=" + usernames + "]";
	}
	
}
