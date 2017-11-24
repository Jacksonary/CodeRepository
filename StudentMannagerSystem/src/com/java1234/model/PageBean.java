package com.java1234.model;

/**
 * 分页显示的封装
 * @author Weiguo Liu
 *
 */
public class PageBean {

	private int page; // 第几页
	private int rows; // 每页的记录数
	private int startNum; // 起始页
	
	public PageBean(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getStartNum() {
		return (page-1)*rows;
	}
	
}
