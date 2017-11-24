package com.hhu.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.dao.FaultLogDao;
import com.hhu.entity.FaultLog;
import com.hhu.service.FaultLogService;

@Service("faultLogService")
public class FaultLogServiceImpl implements FaultLogService {

	@Autowired
	FaultLogDao faultLogDao;
	
	@Override
	public int getLogNumber() {
		return faultLogDao.getLogNumber();
	}

	@Override
	public List<FaultLog> getAllLogs(Timestamp st,Timestamp et) {
		return faultLogDao.getAllLogs(st,et);
	}

	@Override
	public List<FaultLog> getAllLogsNoTime() {
		return faultLogDao.getAllLogsNoTime();
	}

	@Override
	public int getLogNumberWithSearch(Timestamp st, Timestamp et) {
		return faultLogDao.getLogNumberWithSearch(st, et);
	}

}
