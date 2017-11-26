package com.hhu.service;

import java.sql.Timestamp;
import java.util.List;

import com.hhu.entity.FaultLog;

/*
 * 获取数据库中日志的记录服务层
 */
public interface FaultLogService {

	public int getLogNumber();
	
	public int getLogNumberWithSearch(Timestamp st, Timestamp et);
	
	public List<FaultLog> getAllLogs(Timestamp st, Timestamp et);
	
	public List<FaultLog> getAllLogsNoTime();
	
}
