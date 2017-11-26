package com.hhu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.dao.StationDao;
import com.hhu.dao.cache.RedisDao;
import com.hhu.entity.Station;
import com.hhu.service.StationService;

@Service("stationService")
public class StationServiceImpl implements StationService{

	@Autowired
	RedisDao redisDao;
	
	@Autowired
	StationDao stationDao;
	
	@Override
	public Station getStationById(int fId) {
		return stationDao.getStationById(fId);
	}

	@Override
	public List<Station> getAllStation(int p1, int p2) {
		return stationDao.getAllStation(p1, p2);
	}

	@Override
	public int getStationNumber() {
		return stationDao.getStationNumber();
	}

	@Override
	public List<Station> getAllStation2() {
		return stationDao.getAllStation2();
	}

	@Override
	public List<Integer> getfOrgId() {
		return stationDao.getfOrgId();
	}

	@Override
	public List<String> getfCaption() {
		return stationDao.getfCaption();
	}

	@Override
	public Station getLastStation() {
		//优化点：缓存优化，一致性维护，建立子超时的基础上
		/*
		 * get from cache
		 * if null
		 * get db
		 * else
		 * put catch
		 * logic
		 */
		//获取最大的Id
//		int max = stationDao.getLastStation().getfId();
//		
//		Station station = redisDao.getStation(now);
//		
//		if(station==null){
//			//1.如果为空，则访问数据库去数据
//			station = stationDao.getStationByTime(now);
//			if(station==null){
//				System.out.println("数据库中也没有这个数据");
//				return null;
//			}else{
//				//将取到的数据放到redis缓存中存在，方便下次快速取到
//				redisDao.putStation(station);
//			}
//		}
//		return null;
		
		//返回最新的数据
		return stationDao.getLastStation();
	}

}
