package com.hhu.dao;

import java.util.List;

import com.hhu.entity.StationPara;

public interface StationParaDao {
	
	public List<StationPara> getStationParaBySid(int sid); 
	
}
