package com.hhu.dao;

import java.util.List;

import com.hhu.entity.Station;

/**
 * 站点查询接口
 * @author Weiguo Liu
 *
 */
public interface StationDao {

	public Station getStationById(int fId);
	
	//分页查询记录，pn表示查询第pn页的记录，r表示每页有多少条记录
	public List<Station> getAllStation(int p1,int p2);
	
	//这里只查询站点名称和id的对应关系
	public List<Station> getAllStation2();
	
	public int getStationNumber();
	
	public List<Integer> getfOrgId();
	
	public List<String> getfCaption();
	
	public Station getLastStation();
	
	//模拟进口压力
	public void writeDate(float d);
	
	//模拟对象写入
	public void writeStation(float ip, float ipt, float op, float opt, int tp,int f,int fd);
}
