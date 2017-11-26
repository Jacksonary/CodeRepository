package com.hhu.threads;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.hhu.dao.cache.RedisDao;
import com.hhu.entity.Station;
import com.hhu.service.StationService;
import com.hhu.util.SpringBeanUtil;

/**
 * 将数据推送到前台（这里的推送是指的动态制图的推送）和redis缓存中，并且进行超限的判断
 * @author Weiguo Liu
 *
 */
public class StationThread extends Thread {

	//接受的webSocketSession参数
	private WebSocketSession session;
	
	//公用的获取SpringBean的工具类
	static SpringBeanUtil su = new SpringBeanUtil();
	
	//公共的服务类
	static StationService stationService = (StationService) su.getBeanByName("stationService");
	
	//公用的redis类
	static RedisDao redisDao = (RedisDao)su.getBeanByName("redisDao");
	
	public StationThread(WebSocketSession session) {
		this.session = session;
	}
	
	public void run() {    
    	
    	while(true) {
    		
    		System.out.println("成功进入reids线程：" + Thread.currentThread().getName());
    		Station station = stationService.getLastStation();
    		
    		//将取到的数据（这里的数据是从数据库中取到的）放到redis中
    		redisDao.putStation(station);
    		
    		//封装成JSON
    		JSONObject stationjson = new JSONObject();
    		stationjson.put("fX", station.getfX());
    		stationjson.put("iD", station.getfId());
    		stationjson.put("caption", station.getfCaption());
    		
    		System.out.println("推送的数据：" + stationjson);
    		
    		//进行超限的判断,推送的时候将判断信息封装在一起即可
    		if(station.getfX()>50f) {
    			System.out.println("**********监测的数据已经超限:" + station.getfX() + "********");
    		}
    		
    		//将数据推送到前端
    		try {
				session.sendMessage(new TextMessage(stationjson.toString()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		try {
    			//让线程睡眠10s,就是线程扫描数据库的时间间隔
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    } 
	
}
