package com.hhu.dao;

import java.sql.Timestamp;
import java.util.List;

import com.hhu.entity.FaultLog;

public interface FaultLogDao {
	
	public void recordLog(Timestamp time,String faultTypes,String faultCause);
	
	public void recordLog2(Timestamp time,String faultTypes,String faultCause, int id);
	
	public List<FaultLog> getAllLogs(Timestamp st, Timestamp et);
	
	public List<FaultLog> getAllLogsNoTime();
	
	public int getLogNumber();
	
	public int getLogNumberWithSearch(Timestamp st, Timestamp et);
	
}
