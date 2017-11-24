package com.hhu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.dao.StationParaDao;
import com.hhu.entity.StationPara;
import com.hhu.service.StationParaService;

@Service("StationParaService")
public class StationParaServiceImpl implements StationParaService {
	
	@Autowired
	StationParaDao stationParaDao;

	@Override
	public List<StationPara> getStationParaBySid(int sid) {
		return stationParaDao.getStationParaBySid(sid);
	}

}
