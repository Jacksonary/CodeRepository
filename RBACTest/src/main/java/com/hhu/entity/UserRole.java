package com.hhu.entity;

import com.hhu.base.BaseEntity;

public class UserRole extends BaseEntity{
	
	private Long id;
	
	private Long role;
	
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserRole(Long role, Long userId) {
		super();
		this.role = role;
		this.userId = userId;
	}

	public UserRole() {
		super();
	}

}
