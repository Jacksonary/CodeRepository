package com.hhu.dao;

import java.util.List;

import com.hhu.entity.HistoryDates;

/**
 * 获取管道节点上数据的接口
 * @author Weiguo Liu
 *
 */
public interface HistoryDateDao {

	public HistoryDates getDate(int fId);
	
	public List<HistoryDates> getAllDates();
}
