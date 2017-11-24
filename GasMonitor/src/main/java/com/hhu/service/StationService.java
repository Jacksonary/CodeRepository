package com.hhu.service;

import java.util.List;

import com.hhu.entity.Station;

public interface StationService {

	public Station getStationById(int fId);
	
	public List<Station> getAllStation(int p1,int p2);
	
	public List<Station> getAllStation2();
	
	public int getStationNumber();
	
	public List<Integer> getfOrgId();
	
	public List<String> getfCaption();
	
	public Station getLastStation();
}
