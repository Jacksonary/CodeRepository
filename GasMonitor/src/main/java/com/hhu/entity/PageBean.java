package com.hhu.entity;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * 添加Page实体类来存放分页的信息
 * 
 * @author Weiguo Liu
 *
 */
public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private long total;// 总记录数
	private List<T> list;// 存放查询结果的集合
	private int pageNum;// 页码序号
	private int pageSize;// 每页的显示记录数
	private int pages;// 总页数

	public PageBean(List<T> list) {
		if (list instanceof Page) {
			Page<T> page = (Page<T>) list;
			this.pageNum = page.getPageNum();
			this.pageSize = page.getPageSize();
			this.total = page.getTotal();
			this.pages = page.getPages();
			this.list = page;
		}
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
