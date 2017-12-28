package com.hhu.entity;

import com.hhu.base.BaseEntity;

public class RoleFunction extends BaseEntity{
	
	private Long id;
	
	private Long roleId;
	
	private Long functionId;
	
	private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public RoleFunction(Long roleId, Long functionId, int status) {
		super();
		this.roleId = roleId;
		this.functionId = functionId;
		this.status = status;
	}

	public RoleFunction() {
		super();
	}

}
