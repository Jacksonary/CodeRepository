package com.hhu.entity;

import com.hhu.base.BaseEntity;

public class User extends BaseEntity{
	
	private Long id;
	
	private String name;
	
	private String pwd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public User(String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
	}
	
	public User() {
		super();
	}

}
