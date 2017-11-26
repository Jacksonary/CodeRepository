package com.hhu.threads;

import java.util.Random;

import org.springframework.web.socket.WebSocketSession;

import com.hhu.dao.StationDao;
import com.hhu.util.SpringBeanUtil;

public class DataSimulationThread extends Thread {

	//定义模拟数据的生成范围
	static final  float min = 1.0f;
	static final float max = 55.0f;
	static float m;
	
	//公用的获取SpringBean的工具类
	static SpringBeanUtil su = new SpringBeanUtil();
	
	//公共的服务类
	static StationDao stationDao = (StationDao) su.getBeanByName("stationDao");
	
	public void run() {    
    	
    	while(true) {
    		
    		System.out.println("成功进入数据模拟线程：" + Thread.currentThread().getName());
    		
    		//进行模拟数据的生成,模拟数据的范围是min-1到max+1的float型数据
    		m = min + ((max - min) * new Random().nextFloat());
    		
    		stationDao.writeDate(m);
    		
    		try {
    			/*
    			 * 让线程睡眠9s,即每9s向数据库中写入随机数,这个线程的休眠时间应该小于推送线程
    			 * 的休眠时间，这样保证每隔10s数据中是有新的数据向前台推送的
    			 */
				Thread.sleep(9000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    } 
	
}
