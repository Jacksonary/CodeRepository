package com.hhu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.dao.HistoryDateDao;
import com.hhu.entity.HistoryDates;
import com.hhu.service.HistoryDateService;

@Service("historyDateService")
public class HistoryDateServiceImpl implements HistoryDateService {

	@Autowired
	HistoryDateDao historyDateDao;
	
	@Override
	public HistoryDates getDate(int fId) {
		return historyDateDao.getDate(fId);
	}

	@Override
	public List<HistoryDates> getAllDates() {
		return historyDateDao.getAllDates();
	}

}
