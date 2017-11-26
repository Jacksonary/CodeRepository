package com.hhu.threads;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.hhu.dao.StationDao;
import com.hhu.dao.cache.RedisDao;
import com.hhu.entity.Station;
import com.hhu.service.FaultDiagnosisService;
import com.hhu.service.StationService;
import com.hhu.util.SpringBeanUtil;

/**
 * 保证三个操作是按顺序执行的
 * @author Weiguo Liu
 *
 */
public class AllThread extends Thread {

	//定义模拟数据的生成范围,一级入口压力,正常范围为3.6~5.5
	//模拟下限
	static final  float minip = 2.6f;
	//模拟上限
	static final float maxip = 6.5f;
	//随机一级入口压力
	static float mip;
	
	//模拟二级入口压力，正常范围为3.6~4.4
	static final float minipt = 2.6f;
	static final float maxipt = 5.4f;
	static float mipt;
	
	//模拟一级出口压力，正常范围为3.6~4.4
	static final float minop = 2.6f;
	static final float maxop = 5.4f;
	static float mop;
	
	//模拟二级出口压力，正常范围为0.36~0.48
	static final float minopt = 0.26f;
	static final float maxopt = 0.58f;
	static float mopt;
	
	//模拟温度，正常范围为-20~60
	static final int mintp = -30;
	static final int maxtp = 70;
	static int mtp;
	
	//模拟流量，这个不需要报警设置
	static final int minf = 0;
	static final int maxf = 80000;
	static int mf;
	
	//模拟过滤器压差，正常为10，超过100报警
	static final int minfd = 10;
	static final int maxfd = 200;
	static int mfd;
	
	//公用的获取SpringBean的工具类
	static SpringBeanUtil su = new SpringBeanUtil();
	
	//公共的服务类
	static StationDao stationDao = (StationDao) su.getBeanByName("stationDao");
	
	
	//接受的webSocketSession参数
	private WebSocketSession session;
	
	public AllThread(WebSocketSession session) {
		this.session = session;
	}
	
	//公共的服务类
	static StationService stationService = (StationService) su.getBeanByName("stationService");
	
	//公用的redis类
	static RedisDao redisDao = (RedisDao)su.getBeanByName("redisDao");
	
	//公用数据分析类
	static FaultDiagnosisService faultDiagnosisService = (FaultDiagnosisService)su.getBeanByName("faultDiagnosisService");
	
	
	@Override
	public void run() {
		
		while(true) {//这里的死循环不能丢
			//这里的顺序是先将模拟数据写到数据库中，然后进行读取逻辑判断
			
			//进行模拟数据的生成,模拟数据的范围是min-1到max+1的float型数据
			mip = minip + ((maxip - minip) * new Random().nextFloat());
			mipt = minipt + ((maxipt-minipt) * new Random().nextFloat());
			//首先向数据库中写入数据
			//stationDao.writeDate(mip);
			
			//模拟出口压力
			mop = minop + ((maxop - minop) * new Random().nextFloat());
			mopt = minopt + ((maxopt-minopt) * new Random().nextFloat());
			
			//模拟温度
			mtp =  new Random().nextInt(maxtp+30) - 30;
			
			//模拟流量
			mf = new Random().nextInt(maxf);
			
			//模拟压差
			mfd =  new Random().nextInt(maxfd);
			
			//写入数据库
			System.out.println("向数据库中写入模拟数据：一入：" + mip + " 一出：" + mop + " 二入:" + mipt + " 二出：" + mopt + " 温度：" + mtp + " 流量：" + mf + " 压差:" + mfd);
			stationDao.writeStation(mip, mipt, mop, mopt, mtp, mf, mfd);
			
			//然后进行数据库的监测工作,获取数据中最新的一条记录
			Station station = stationService.getLastStation();
			
			//封装成JSON
			JSONObject stationjson = new JSONObject();
			stationjson.put("fX", station.getfX());//一级入口压力
			stationjson.put("fy", station.getfY());//一级出口压力
			stationjson.put("fX2", station.getfX2());//二级入口压力
			stationjson.put("fy2", station.getfY2());//二级出口压力
			stationjson.put("fs0", station.getfS0());//温度
			stationjson.put("fs1", station.getfS1());//流量
			stationjson.put("fs2", station.getfS2());//压差
			
			//将数据推送到前端
			try {
				session.sendMessage(new TextMessage(stationjson.toString()));
				System.out.println("向前台推送数据中最新数据（并将此数据存入redis中）：" + stationjson.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//将取到的数据（这里的数据是从数据库中取到的）放到redis中
			redisDao.putStation(station);
			
			
			/*
			 * 进行数据监测
			 */
			//将redis中的数据全部取出进行分析
			List<Station> stationList = redisDao.getAllStation();
			
			//调用检测的服务层对象进行数据分析
			System.out.println("进入故障诊断模块,从redis中获取的数据为（逆序）：");
			for(Station s:stationList) {
				System.out.print(" 一入：" + s.getfX());
				System.out.print(" 一出：" + s.getfY());
				System.out.print(" 二入：" + s.getfX2());
				System.out.print(" 二出：" + s.getfY2());
				System.out.print(" 温度：" + s.getfS0());
				System.out.print(" 流量" + s.getfS1());
				System.out.print(" 压差：" + s.getfS2());
				System.out.println();
			}
			
			//1.进入分析线程（针对单个站点进行分析）
//			String result = faultDiagnosisService.datasAnalysis(stationList);
			
			//2.进入分析线程（针对多站点的情况）,获取所有站点的基本信息，实际只是要其中的id和站点的对应关系
			List<Station> allStation = stationService.getAllStation2();
			faultDiagnosisService.datasAnalysis2(allStation);
			
			
			//select * from T_StationPara where F_SID = 2 AND F_TIME>(select dateadd(MINUTE,-10,GETDATE())) ORDER BY F_TIME 
			
			
			//将此线程睡眠10s，10s进行一次数据的写入
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
}
