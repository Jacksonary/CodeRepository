package com.hhu.threads;

import java.util.List;

import org.springframework.web.socket.WebSocketSession;

import com.hhu.dao.cache.RedisDao;
import com.hhu.entity.Station;
import com.hhu.service.FaultDiagnosisService;
import com.hhu.util.SpringBeanUtil;

//监测线程
public class MonitorThread extends Thread {
	
	private WebSocketSession session;
	
	static SpringBeanUtil su = new SpringBeanUtil();
	
	//获取Spring管理的redis的Bean实例
	static RedisDao redisDao = (RedisDao)su.getBeanByName("redisDao"); 
	
	//获取检测的服务层对象
	static FaultDiagnosisService faultDiagnosisService = (FaultDiagnosisService) su.getBeanByName("faultDiagnosisService");
	
	public MonitorThread(WebSocketSession session) {
		this.session = session;
	}
	
	public void run() {    
    	
    	while(true) {
    		
    		System.out.println("成功进入检测线程：" + Thread.currentThread().getName());
    		
    		//将redis中的数据全部取出进行分析
    		List<Station> stationList = redisDao.getAllStation();
    		
    		//调用检测的服务层对象进行数据分析
    		String result = faultDiagnosisService.datasAnalysis(stationList);
    		
    		System.out.println("分析的结果为：" + result);
    		
//    		//将数据推送到前端
//    		try {
//				session.sendMessage(new TextMessage(result));
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
    		try {
    			//让线程睡眠10s,就是线程扫描数据库的时间间隔
				Thread.sleep(10050);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    } 
	
}
