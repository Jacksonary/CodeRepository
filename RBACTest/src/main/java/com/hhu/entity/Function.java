package com.hhu.entity;

import com.hhu.base.BaseEntity;

public class Function extends BaseEntity{
	
	private Long id;
	
	private String name;
	
	private Long parentId;
	
	private String url;
	
	private int serialNum;
	
	private int accordiont;

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public int getAccordiont() {
		return accordiont;
	}

	public void setAccordiont(int accordiont) {
		this.accordiont = accordiont;
	}

	public Function(String name, Long parentId, String url, int serialNum, int accordiont) {
		super();
		this.name = name;
		this.parentId = parentId;
		this.url = url;
		this.serialNum = serialNum;
		this.accordiont = accordiont;
	}

	public Function() {
		super();
	}
	
}
