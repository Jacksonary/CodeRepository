package com.hhu.service;

import java.util.List;

import com.hhu.entity.HistoryDates;

public interface HistoryDateService {

	public HistoryDates getDate(int fId);
	
	public List<HistoryDates> getAllDates();
}
