package com.hhu.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.dao.FaultLogDao;
import com.hhu.entity.Station;
import com.hhu.entity.StationPara;
import com.hhu.enums.FaultDiagnosisEnums;
import com.hhu.service.FaultDiagnosisService;
import com.hhu.service.StationParaService;
import com.hhu.service.StationService;

@Service("faultDiagnosisService")
public class FaultDiagnosisServiceImpl implements FaultDiagnosisService {
	
	@Autowired
	FaultLogDao faultLogDao;
	
	@Autowired
	StationService stationService;
	
	//多站点的查询需求
	@Autowired
	StationParaService stationParaService;
	
	/*
	 * 一级的进出口
	 */
	//进口压力的波动域值
	static final float INLETPRESSUREFLUCTUATION = 1.0f;
	
	//进口压力的上限
	static final float INLETPRESSUREUPPERLIMIT = 5.5f;
	
	//进口压力的下限
	static final float INLETPRESSURELOWLIMIT = 3.6f;
	
	//出口压力的波动域值
	static final float OUTLETPRESSUREFLUCTUATION = 1.0f;
	
	//出口压力的上限
	static final float OUTLETPRESSUREUPPERLIMIT = 4.4f;
	
	//出口压力下限
	static final float OUTLETPRESSURELOWLIMIT = 3.6f;
	
	//温度上限
	static final int TEMPERATUREUPPERLIMIT = 60;
	
	//温度下限
	static final int TEMPERATURELOWERLIMIT = -20;
	
	//温度波动域值
	static final int TEMPERATUREFLUCTUATION = 20;
	
	//流量上限
	static final int FLOWUPPERLIMIT = 60000;
	
	//流量波动域值
	static final int FLOWFLUCTUATION = 10000;
	
	//过滤器压差上限
	static final int FILTERDIFFERENCE = 100;
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	
	
	/*
	 * 二级进出口
	 */
	//二级进口压力的波动域值
	static final float INLETPRESSUREFLUCTUATION2 = 0.4f;
	
	//二级进口压力的上限
	static final float INLETPRESSUREUPPERLIMIT2 = 4.4f;
	
	//二级进口压力的下限
	static final float INLETPRESSURELOWLIMIT2 = 3.6f;
	
	//二级出口压力的波动域值
	static final float OUTLETPRESSUREFLUCTUATION2 = 0.16f;
	
	//二级出口压力的上限
	static final float OUTLETPRESSUREUPPERLIMIT2 = 0.48f;
	
	//出口压力下限
	static final float OUTLETPRESSURELOWLIMIT2 = 0.36f;
	
	
	//一组数据上一次的id,以静态数据的方式存放
	static int id;
	
	//进口压力诊断
	@Override
	public String inletPressureDiagnosis() {
		// TODO Auto-generated method stub
		return null;
	}

	//出口压力诊断
	@Override
	public String outletPressureDiagnosis() {
		// TODO Auto-generated method stub
		return null;
	}

	//温度诊断
	@Override
	public String temperatureDiagnosis() {
		// TODO Auto-generated method stub
		return null;
	}

	//流量诊断
	@Override
	public String flowDiagnosis() {
		// TODO Auto-generated method stub
		return null;
	}

	//过滤器压差诊断
	@Override
	public String filterlDiagnosis() {
		// TODO Auto-generated method stub
		return null;
	}

	//切断诊断
	@Override
	public String cutOffDiagnosis() {
		// TODO Auto-generated method stub
		return null;
	}

	//泄露诊断
	@Override
	public String leakDiagnosis() {
		// TODO Auto-generated method stub
		return null;
	}

	//全局的数据分析
	@Override
	public String datasAnalysis(List<Station> stations) {
		
		//存放进口压力的List,一级入口
		List<Float> inletPressures = new ArrayList<Float>();
		
		//存放二级进口压力的List
		List<Float> inletPressures2 = new ArrayList<Float>();
		
		//存放出口压力的List，一级出口
		List<Float> outletPressures = new ArrayList<Float>();
		
		//存放二级出口的List
		List<Float> outletPressures2 = new ArrayList<Float>();
		
		//存放温度的List
		List<Integer> temperatures = new ArrayList<Integer>();
		
		//存放流量的List
		List<Integer> flows = new ArrayList<Integer>(); 
		
		//存放过滤器压差的List
		List<Integer> filter = new ArrayList<Integer>();
		
		//进行数据遍历分析
		for(int i=0;i<stations.size();i++) {
			//将各个数据存放到各个List中
			inletPressures.add(stations.get(i).getfX());
			inletPressures2.add(stations.get(i).getfX2());
			outletPressures.add(stations.get(i).getfY());
			outletPressures2.add(stations.get(i).getfY2());
			temperatures.add(stations.get(i).getfS0());
			flows.add(stations.get(i).getfS1());
			filter.add(stations.get(i).getfS2());
		}
		
		/*
		 * 一级进出口的参数判定 
		 */
		//监测进口压力是否持续下降
		int downFlag = 0;
		
		//监测出口压力是否持续下降
		int opdownFlag = 0;
		
		
		//监测进口压力是否下降至0
		int downToZero = 0;
		
		//监测出口压力是否持续下降至0
		int opdownToZero = 0;
		
		
		//监测进口压力是否持续上升
		int upFlag = 0;
		
		//监测出口压力是否持续上升
		int opupFlag = 0;
		
		
		//监测进口压力是否持续波动
		int fluctuationFlag = 0;
		
		//监测出口压力是否持续波动
		int opfluctuationFlag = 0;
		
		//监测进口压力是否超限，上限
		int outOfBoundsFlag = 0;
		
		//监测进口压力是否超下限
		int outOfBoundsFlagl = 0;
		
		//监测出口压力是否超限，上限
		int opoutOfBoundsFlag = 0;
		
		//监测出口压力是否超下限
		int opoutOfBoundsFlagl = 0;
		
		//进口压力超限参数，具体数值
		Float outOfBounds = 0f;
		
		//出口压力超限参数，具体超限数值
		Float opoutOfBounds = 0f;
		
		
		//温度是否差超上限
		int temperatureUpperLimitFlag = 0; 
		
		//温度是否超下限
		int temperatureLowerLimitFlag = 0;
		
		//温度是否波动
		int temperatureFluctuationFlag = 0;
		
		
		//流量超上限
		int FlowUpperLimitFlag = 0;
		
		//流量有波动
		int FlowFluctuationFlag = 0;
		
		//流量短时间变为0
		int FlowTurnZero = 0;
		
		
		//过滤器压差超上限
		int filterUpperLimit = 0;
		
		//过滤器压差持续上升
		int filterRise = 0;
		
		//过滤器压差为0
		int filterZero = 0;
		
		
		/*
		 * 二级进出口的参数判定 
		 */
		//监测进口压力是否持续下降
		int downFlag2 = 0;
		
		//监测出口压力是否持续下降
		int opdownFlag2 = 0;
		
		
		//监测进口压力是否下降至0
		int downToZero2 = 0;
		
		//监测出口压力是否持续下降至0
		int opdownToZero2 = 0;
		
		
		//监测进口压力是否持续上升
		int upFlag2 = 0;
		
		//监测出口压力是否持续上升
		int opupFlag2 = 0;
		
		
		//监测进口压力是否持续波动
		int fluctuationFlag2 = 0;
		
		//监测出口压力是否持续波动
		int opfluctuationFlag2 = 0;
		
		//监测进口压力是否超限，上限
		int outOfBoundsFlag2 = 0;
		
		//监测进口压力是否超下限
		int outOfBoundsFlagl2 = 0;
		
		//监测出口压力是否超限，上限
		int opoutOfBoundsFlag2 = 0;
		
		//监测出口压力是否超下限
		int opoutOfBoundsFlagl2 = 0;
		
		//进口压力超限参数
		Float outOfBounds2 = 0f;
		
		//出口压力超限参数
		Float opoutOfBounds2 = 0f;
		
		
		//进口压力判定的参数
		int size = stations.size()-1;
		//这里注意一定是根据计算得出的size到底是多少*************
		size = 2;
		
		System.out.println("从redis中取出的数据总共有" + (stations.size()) + "数据:" + inletPressures);
		
		
		/* 
		 * 1.首先做各个参数超限的判断，
		 * 一级入口压力，这里的报警持续判断是报警次数的判定（上限）
		*/
		if(inletPressures.get(inletPressures.size()-1)>INLETPRESSUREUPPERLIMIT) {
			outOfBoundsFlag = 1;
			//进口压力超限记录
			System.out.println("****故障记录：" + sdf.format(new Date()) + "一级入口压力超上限:" + inletPressures.get(inletPressures.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS.getFaultCause());
		}
		
		//超下限
		if(inletPressures.get(inletPressures.size()-1)<INLETPRESSURELOWLIMIT) {
			outOfBoundsFlagl = 1;
			//进口压力超限记录
			System.out.println("****故障记录："+ sdf.format(new Date()) + "一级入口压力超下限:" + inletPressures.get(inletPressures.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL.getFaultCause());
		}
		
		/*
		 * 一级出口压力超限的判断
		 */
		if(outletPressures.get(outletPressures.size()-1)>OUTLETPRESSUREUPPERLIMIT) {
			opoutOfBoundsFlag = 1;
			System.out.println("****故障记录：" + sdf.format(new Date()) + "一级出口压力超上限:" + outletPressures.get(outletPressures.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS.getFaultCause());
			opoutOfBounds = outletPressures.get(outletPressures.size()-1);
		}
		
		
		/* 
		 * 超限的判断，这里的报警持续判断是报警次数的判定(下限)
		*/
		if(outletPressures.get(outletPressures.size()-1)<OUTLETPRESSURELOWLIMIT) {
			opoutOfBoundsFlagl = 1;
			System.out.println("****故障记录：" + sdf.format(new Date()) + "一级出口压力超下限:" + outletPressures.get(outletPressures.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL.getFaultCause());
		}
		
		
		/*
		 * 二级进口压力超限的判断
		 */
		if(inletPressures2.get(inletPressures2.size()-1)>INLETPRESSUREUPPERLIMIT2) {
			outOfBoundsFlag2 = 1;
			//进口压力超限记录
			System.out.println("****故障记录：" + sdf.format(new Date()) + "二级入口压力超上限:" + inletPressures2.get(inletPressures2.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS2.getFaultCause());
		}
		
		//超下限
		if(inletPressures2.get(inletPressures2.size()-1)<INLETPRESSURELOWLIMIT2) {
			//进口压力超限记录
			System.out.println("****故障记录：" + sdf.format(new Date()) + "二级入口压力超下限:" + inletPressures2.get(inletPressures2.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL2.getFaultCause());
		}
		
		/*
		 * 二级出口压力超限的判断
		 */
		if(outletPressures2.get(outletPressures2.size()-1)>OUTLETPRESSUREUPPERLIMIT2) {
			opoutOfBoundsFlag2 = 1;
			System.out.println("****故障记录：" + sdf.format(new Date()) + "二级出口压力超上限：" + outletPressures2.get(outletPressures2.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS2.getFaultCause());
		}
		
		
		/* 
		 * 超限的判断，这里的报警持续判断是报警次数的判定(下限)
		*/
		if(outletPressures2.get(outletPressures2.size()-1)<OUTLETPRESSURELOWLIMIT2) {
			opoutOfBoundsFlagl2 = 1;
			System.out.println("****故障记录：" + sdf.format(new Date()) + "二级出口压力超下限：" + outletPressures2.get(outletPressures2.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL2.getFaultCause());
		}
		
		
		/*
		 * 温度超限的判断
		 */
		if(temperatures.get(temperatures.size()-1)>TEMPERATUREUPPERLIMIT) {
			//温度超上限
			temperatureUpperLimitFlag = 1;
			//记录
			System.out.println("****故障记录：" + sdf.format(new Date()) + "温度超上限：" + temperatures.get(temperatures.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.TEMPERATURE_UPPERLIMIT.getFaultTypes(), FaultDiagnosisEnums.TEMPERATURE_UPPERLIMIT.getFaultCause());
		}
		 
		
		if(temperatures.get(temperatures.size()-1)<TEMPERATURELOWERLIMIT) {
			//温度超下限
			temperatureLowerLimitFlag = 1;
			//记录
			System.out.println("****故障记录：" + sdf.format(new Date()) + "温度超下限：" + temperatures.get(temperatures.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.TEMPERATURE_LOWERLIMIT.getFaultTypes(), FaultDiagnosisEnums.TEMPERATURE_LOWERLIMIT.getFaultCause());
		}
		
		
		/*
		 * 流量超限的判断
		 */
		//流量短时间变为0
		if(flows.get(flows.size()-1)==0) {
			System.out.println("****故障记录：" + sdf.format(new Date()) + "流量变为0" + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FLOW_TURNTOZEROINSHORT.getFaultTypes(), FaultDiagnosisEnums.FLOW_TURNTOZEROINSHORT.getFaultCause());
		}
		
		/* 
		 * 超限的判断，这里的报警持续判断是报警次数的判定
		*/
		if(flows.get(flows.size()-1)>FLOWUPPERLIMIT) {
			//流量超上限
			FlowUpperLimitFlag = 1;
			//记录
			System.out.println("****故障记录：" + sdf.format(new Date()) + "流量超上限：" + flows.get(flows.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FLOW_UPPERLIMIT.getFaultTypes(), FaultDiagnosisEnums.FLOW_UPPERLIMIT.getFaultCause());
		}
		
		
		
		/*
		 * 过滤器压差超限的判断
		 */
		//无压差
		if(filter.get(filter.size()-1)==0) {
			System.out.println("****故障记录：" + sdf.format(new Date()) + "过滤器压差变成0" + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FILTERPRESSURE_TURNTOZEROINSHORT.getFaultTypes(), FaultDiagnosisEnums.FILTERPRESSURE_TURNTOZEROINSHORT.getFaultCause());
		}
		
		
		/* 
		 * 超限的判断，这里的报警持续判断是报警次数的判定
		*/
		if(filter.get(filter.size()-1)>FILTERDIFFERENCE) {
			filterUpperLimit = 1;
			System.out.println("****故障记录：" + sdf.format(new Date()) + "过滤器压差已超上限：" + filter.get(filter.size()-1) + "****");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FILTERPRESSURE_UPPERLIMIT.getFaultTypes(), FaultDiagnosisEnums.FILTERPRESSURE_UPPERLIMIT.getFaultCause());
		}
		
		
		
		
		/*
		 * 2.做趋势的判断
		 * 
		 * 这里从redis缓存中获取数据进行判断的时候一定能要注意取出数据的顺序，
		 * 在redis中获取的数据一般都先进去的数据打印的打印的时候是在后面的、
		 *也就是说，若果往里面存放数据的时候是34、78，那么从其中获取的时候就是逆序的：78、34
		 *所以这里的判断其实就是相反序列的反判断
		 * 
		 */
		for(int i=1;i<stations.size();i++) {
			
			/*
			 * 1.一级进口压力的判断
			 */
			//持续下降的判断（一级）
			if(inletPressures.get(i)-inletPressures.get(i-1)<0) {
				downFlag++;
				if(inletPressures.get(i)==0&&downFlag!=0) {
					downToZero = 1;
					//进口压力下降至0记录
					System.out.println("****故障记录：" + sdf.format(new Date()) + " 一级入口压力下降至0****");
					faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_DROPTOZERO.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_DROPTOZERO.getFaultCause());
				}
			}
			
			//持续上升的判断
			if(inletPressures.get(i)-inletPressures.get(i-1)>0) {
				upFlag++;
			}
			
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(inletPressures.get(i)-inletPressures.get(0))<=INLETPRESSUREFLUCTUATION) {
				if(Math.abs(inletPressures.get(i)-inletPressures.get(i-1))!=0) {
					fluctuationFlag++;
				}
			}
			
			
			/* 
			 * 超限的判断，这里的报警持续判断是报警次数的判定（上限）
			*/
//			if(inletPressures.get(i)>INLETPRESSUREUPPERLIMIT) {
//				outOfBoundsFlag = 1;
//				//进口压力超限记录
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "一级入口压力超上限:" + inletPressures.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS.getFaultCause());
//				outOfBounds = inletPressures.get(i);
//			}
//			
//			//超下限
//			if(inletPressures.get(i)<INLETPRESSURELOWLIMIT) {
//				outOfBoundsFlagl = 1;
//				//进口压力超限记录
//				System.out.println("****故障记录："+ sdf.format(new Date()) + "一级入口压力超下限:" + inletPressures.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL.getFaultCause());
//			}
			
			
			
			/*
			 * 2.出口压力的判断
			 */
			//一级出口压力持续下降的判断
			if(outletPressures.get(i)-outletPressures.get(i-1)<0) {
				opdownFlag++;
				if(outletPressures.get(i)==0&&opdownFlag!=0) {
					System.out.println("****故障记录：" + sdf.format(new Date()) + "一级出口压力下降至0" + "****");
					faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_DROPTOZERO.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_DROPTOZERO.getFaultCause());
					opdownToZero = 1;
				}
			}
			
			//持续上升的判断
			if(outletPressures.get(i)-outletPressures.get(i-1)>0) {
				opupFlag++;
			}
			
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(outletPressures.get(i)-outletPressures.get(0))<=OUTLETPRESSUREFLUCTUATION) {
				if(Math.abs(outletPressures.get(i)-outletPressures.get(i-1))!=0) {
					opfluctuationFlag++;
				}
			}
			
			
			/* 
			 * 超限的判断，这里的报警持续判断是报警次数的判定(上限)
			*/
//			if(outletPressures.get(i)>OUTLETPRESSUREUPPERLIMIT) {
//				opoutOfBoundsFlag = 1;
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "一级出口压力超上限:" + outletPressures.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS.getFaultCause());
//				opoutOfBounds = outletPressures.get(i);
//			}
//			
//			
//			/* 
//			 * 超限的判断，这里的报警持续判断是报警次数的判定(下限)
//			*/
//			if(outletPressures.get(i)<OUTLETPRESSURELOWLIMIT) {
//				opoutOfBoundsFlagl = 1;
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "一级出口压力超下限:" + outletPressures.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL.getFaultCause());
//			}
			
			
			
			
			/*
			 * 1.2二级进口的参数判断
			 */
			/*
			 * 1.进口压力的判断
			 */
			//持续下降的判断（一级）
			if(inletPressures2.get(i)-inletPressures2.get(i-1)<0) {
				downFlag2++;
				if(inletPressures2.get(i)==0&&downFlag2!=0) {
					downToZero2 = 1;
					//进口压力下降至0记录
					System.out.println("****故障记录：" + sdf.format(new Date()) + " 二级入口压力下降至0****");
					faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_DROPTOZERO2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_DROPTOZERO2.getFaultCause());
				}
			}
			
			//持续上升的判断
			if(inletPressures2.get(i)-inletPressures2.get(i-1)>0) {
				upFlag2++;
			}
			
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(inletPressures2.get(i)-inletPressures2.get(0))<=INLETPRESSUREFLUCTUATION2) {
				if(Math.abs(inletPressures2.get(i)-inletPressures2.get(i-1))!=0) {
					fluctuationFlag2++;
				}
			}
			
			
			/* 
			 * 超限的判断，这里的报警持续判断是报警次数的判定（上限）
			*/
//			if(inletPressures2.get(i)>INLETPRESSUREUPPERLIMIT2) {
//				outOfBoundsFlag2 = 1;
//				//进口压力超限记录
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "二级入口压力超上限:" + inletPressures2.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS2.getFaultCause());
//			}
//			
//			//超下限
//			if(inletPressures2.get(i)<INLETPRESSURELOWLIMIT2) {
//				//进口压力超限记录
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "二级入口压力超下限:" + inletPressures2.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL2.getFaultCause());
//			}
			
			
			
			/*
			 * 2.2 二级出口压力的判断
			 */
			//持续下降的判断
			if(outletPressures2.get(i)-outletPressures2.get(i-1)<0) {
				opdownFlag2++;
				if(outletPressures2.get(i)==0&&opdownFlag2!=0) {
					System.out.println("****故障记录：" + sdf.format(new Date()) + "二级出口压力下降至0" + "****");
					faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_DROPTOZERO2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_DROPTOZERO2.getFaultCause());
					opdownToZero2 = 1;
				}
			}
			
			//持续上升的判断
			if(outletPressures2.get(i)-outletPressures2.get(i-1)>0) {
				opupFlag2++;
			}
			
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(outletPressures2.get(i)-outletPressures2.get(0))<=OUTLETPRESSUREFLUCTUATION2) {
				if(Math.abs(outletPressures2.get(i)-outletPressures2.get(i-1))!=0) {
					opfluctuationFlag2++;
				}
			}
			
			
			/* 
			 * 超限的判断，这里的报警持续判断是报警次数的判定(上限)
			*/
//			if(outletPressures2.get(i)>OUTLETPRESSUREUPPERLIMIT2) {
//				opoutOfBoundsFlag2 = 1;
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "二级出口压力超上限：" + outletPressures2.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS2.getFaultCause());
//			}
//			
//			
//			/* 
//			 * 超限的判断，这里的报警持续判断是报警次数的判定(下限)
//			*/
//			if(outletPressures2.get(i)<OUTLETPRESSURELOWLIMIT2) {
//				opoutOfBoundsFlagl2 = 1;
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "二级出口压力超下限：" + outletPressures2.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL2.getFaultCause());
//			}
			
			
			
			/*
			 * 3.温度的判断
			 */
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(temperatures.get(i)-temperatures.get(0))<=TEMPERATUREFLUCTUATION) {
				if(temperatures.get(i)-temperatures.get(i-1)!=0) {
					temperatureFluctuationFlag++;
				}
			}
			
			
			/* 
			 * 超限的判断，这里的报警持续判断是报警次数的判定
			*/
//			if(temperatures.get(i)>TEMPERATUREUPPERLIMIT) {
//				//温度超上限
//				temperatureUpperLimitFlag = 1;
//				//记录
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "温度超上限：" + temperatures.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.TEMPERATURE_UPPERLIMIT.getFaultTypes(), FaultDiagnosisEnums.TEMPERATURE_UPPERLIMIT.getFaultCause());
//			}
//			 
//			
//			if(temperatures.get(i)<TEMPERATURELOWERLIMIT) {
//				//温度超下限
//				temperatureLowerLimitFlag = 1;
//				//记录
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "温度超下限：" + temperatures.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.TEMPERATURE_LOWERLIMIT.getFaultTypes(), FaultDiagnosisEnums.TEMPERATURE_LOWERLIMIT.getFaultCause());
//			}
			
			
			
			
			/*
			 * 4.流量的判断
			 */
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(flows.get(i)-flows.get(0))<=FLOWFLUCTUATION) {
				if(flows.get(i)-flows.get(i-1)!=0) {
					FlowFluctuationFlag++;
				}
			}
			
//			//流量短时间变为0
//			if(flows.get(i)==0) {
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "流量变为0" + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FLOW_TURNTOZEROINSHORT.getFaultTypes(), FaultDiagnosisEnums.FLOW_TURNTOZEROINSHORT.getFaultCause());
//			}
//			
//			/* 
//			 * 超限的判断，这里的报警持续判断是报警次数的判定
//			*/
//			if(flows.get(i)>FLOWUPPERLIMIT) {
//				//流量超上限
//				FlowUpperLimitFlag = 1;
//				//记录
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "流量超上限：" + flows.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FLOW_UPPERLIMIT.getFaultTypes(), FaultDiagnosisEnums.FLOW_UPPERLIMIT.getFaultCause());
//			}
			
			
			/*
			 * 5.过滤器压差的判断
			 */
			//持续上升的判断
			if(filter.get(i)-filter.get(i-1)>0) {
				filterRise++;
			}
			
//			//无压差
//			if(filter.get(i)==0) {
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "过滤器压差变成0" + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FILTERPRESSURE_TURNTOZEROINSHORT.getFaultTypes(), FaultDiagnosisEnums.FILTERPRESSURE_TURNTOZEROINSHORT.getFaultCause());
//			}
//			
//			
//			/* 
//			 * 超限的判断，这里的报警持续判断是报警次数的判定
//			*/
//			if(filter.get(i)>FILTERDIFFERENCE) {
//				filterUpperLimit = 1;
//				System.out.println("****故障记录：" + sdf.format(new Date()) + "过滤器压差已超上限：" + filter.get(i) + "****");
//				faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FILTERPRESSURE_UPPERLIMIT.getFaultTypes(), FaultDiagnosisEnums.FILTERPRESSURE_UPPERLIMIT.getFaultCause());
//			}
			
		}
		
		/*
		 * 1.一级进口压力的判断
		 */
		//一级入口压力持续下降的判断
		if(downFlag==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":一级进口压力正在持续下降**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUEDECLINE.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUEDECLINE.getFaultCause());
		}
		
		//下降至0在上面迭代的情况中进行判断
		
		//上升趋势的判断
		if(upFlag==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":一级进口压力正在持续上升**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUERISE.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUERISE.getFaultCause());
		}
		
		//范围内的波动判定
		if(fluctuationFlag==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":一级进口压力在指定范围内波动**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUOUSFLUCTUATION.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUOUSFLUCTUATION.getFaultCause());
		}
		
		
		
		/*
		 * 1.二级进口压力的判断
		 * 进行两两比较，这里取得是3个点，如果每次后一次的数据都比前一次要小，
		 * 那么判断此时的是参数是持续下降的,所有的持续性判断都是取的4个点
		 */
		if(downFlag2==size) {//这里是如果有3个点持续下降的话，那么判断为持续下降
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":二级进口压力正在持续下降**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUEDECLINE2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUEDECLINE2.getFaultCause());
		}
		
		//是否下降至0
//		if(downToZero2==1) {
//			System.out.println("**********二级进口压力下降至0**********");
//		}
		
		//上升趋势的判断
		if(upFlag2==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":二级进口压力正在持续上升**********");
//			System.out.println("**********二级进口压力正在持续上升**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUERISE2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUERISE2.getFaultCause());
		}
		
		//波动域值的判断
		if(fluctuationFlag2==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":二级进口压力正在指定范围内波动**********");
//			System.out.println("**********二级进口压力正在指定范围内波动**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUOUSFLUCTUATION2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUOUSFLUCTUATION2.getFaultCause());
		}
		
		
		// 超限的判断
//		if(outOfBoundsFlag==1) {
//			System.out.println("**********一级进口压力已超上限:" + outOfBounds + "**********");
//		}
		
		
//		/*
//		 * 1.1 二级入口压力的判定
//		 */
//		if(downFlag2==size) {//这里是如果有3个点持续下降的话，那么判断为持续下降
//			System.out.println("**********二级进口压力正在持续下降**********");
//			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUEDECLINE2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUEDECLINE2.getFaultCause());
//		}
//		
//		//是否下降至0
//		if(downToZero2==1) {
//			System.out.println("**********二级进口压力下降至0**********");
//		}
//		
//		//上升趋势的判断
//		if(upFlag2==size) {
//			System.out.println("**********二级进口压力正在持续上升**********");
//			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUERISE2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUERISE2.getFaultCause());
//		}
//		
//		//波动域值的判断
//		if(fluctuationFlag2==size) {
//			System.out.println("**********二级进口压力正在指定范围内波动**********");
//			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUOUSFLUCTUATION2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUOUSFLUCTUATION2.getFaultCause());
//		}
//		
//		
//		// 超限的判断
//		if(outOfBoundsFlag2==1) {
////			System.out.println("**********一级进口压力已超上限:" + outOfBounds + "**********");
//		}
		
		
		
		
		
		
		/*
		 * 2.出口压力的判断
		 * 进行两两比较，这里取得是3个点，如果每次后一次的数据都比前一次要小，
		 * 那么判断此时的是参数是持续下降的,所有的持续性判断都是取的4个点
		 */
		if(opdownFlag==size) {//这里是如果有3个点持续下降的话，那么判断为持续下降
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":一级出口压力正在持续下降**********");
//			System.out.println("**********一级出口压力正在持续下降**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUEDECLINE.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUEDECLINE.getFaultCause());
		}
		
		//是否下降至0
//		if(opdownToZero==1) {
//			System.out.println("**********一级出口压力下降至0**********");
//		}
		
		//上升趋势的判断
		if(opupFlag==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":一级出口压力正在持续上升**********");
//			System.out.println("**********一级出口压力正在持续上升**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUERISE.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUERISE.getFaultCause());
		}
		
		//波动域值的判断
		if(opfluctuationFlag==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":一级出口压力正在指定范围内波动**********");
//			System.out.println("**********一级出口压力正在指定范围内波动**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUOUSFLUCTUATION.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUOUSFLUCTUATION.getFaultCause());
		}
		
		
		// 超限的判断
//		if(opoutOfBoundsFlag==1) {
//			System.out.println("**********一级出口压力超上限:" + opoutOfBounds + "**********");
//		}
		
		
		/*
		 * 2.2二级出口压力的判定
		 */
		/*
		 * 2.2出口压力的判断（二级出口）
		 * 进行两两比较，这里取得是3个点，如果每次后一次的数据都比前一次要小，
		 * 那么判断此时的是参数是持续下降的,所有的持续性判断都是取的4个点
		 */
		if(opdownFlag2==size) {//这里是如果有3个点持续下降的话，那么判断为持续下降
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":二级出口压力正在持续下降**********");
//			System.out.println("**********二级出口压力正在持续下降**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUEDECLINE2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUEDECLINE2.getFaultCause());
		}
		
		//是否下降至0
//		if(opdownToZero2==1) {
//			System.out.println("**********二级出口压力下降至0**********");
//		}
		
		//上升趋势的判断
		if(opupFlag2==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":二级出口压力正在持续上升**********");
//			System.out.println("**********二级出口压力正在持续上升**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUERISE2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUERISE2.getFaultCause());
		}
		
		//波动域值的判断
		if(opfluctuationFlag2==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":二级出口压力正在指定范围内波动**********");
//			System.out.println("**********二级出口压力正在指定范围内波动**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUOUSFLUCTUATION2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUOUSFLUCTUATION2.getFaultCause());
		}
		
		
		// 超限的判断
//		if(opoutOfBoundsFlag2==1) {
//			System.out.println("**********一级出口压力超上限:" + opoutOfBounds + "**********");
//		}
		
		
		
		/*
		 * 3.温度的判断
		 */
		//判断温度是否有波动
		if(temperatureFluctuationFlag==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":温度在指定范围内波动**********");
//			System.out.println("**********温度在指定范围内波动**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.TEMPERATURE_FLUCTUATION.getFaultTypes(), FaultDiagnosisEnums.TEMPERATURE_FLUCTUATION.getFaultCause());
		}
		
		
		/*
		 * 4.流量的判断
		 */
		//流量有波动
		if(FlowFluctuationFlag==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":流量在指定范围内波动**********");
//			System.out.println("**********流量在指定范围内波动**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FLOW_FLUCTUATION.getFaultTypes(), FaultDiagnosisEnums.FLOW_FLUCTUATION.getFaultCause());
		}
		
		/*
		 * 5.过滤器压差的判断
		 */
		//过滤器持续上升的判断
		if(filterRise==size) {
			System.out.println("**********故障记录： " + sdf.format(new Date()) + ":过滤器压差在指定范围持续上升**********");
//			System.out.println("**********过滤器压差在指定范围持续上升**********");
			faultLogDao.recordLog(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FILTERPRESSURE_CONTINUERISE.getFaultTypes(), FaultDiagnosisEnums.FILTERPRESSURE_CONTINUERISE.getFaultCause());
		}
		
		return null;
	}

	
	
	/*
	 * 多站点进行分析，利用外键区别,先从站点基本信息表中获取站点id，再利用站点id这个外键去查询监控参数
	 * 这里默认所有的监控参数都是上传到同一张表中，注意：各个站点的上传时间有所不一样
	 */
	@Override
	public void datasAnalysis2(List<Station> stations) {
		
		//遍历分析各个站点的信息参数
//		System.out.println("++++++++++++++数据库中查询到如下站点++++++++++++++++");
//		for(Station s: stations) {
//			System.out.println(s);
//		}
		
		
		for(Station s: stations) {
			//根据各个站点的id分别去遍历各个站点的监控参数
			doProcess(s.getfId());
		}
	}
	
	public void doProcess(int id) {
		//根据站点id查询各个站点的监控参数(这里的站点和前面的站点不一样，datasAnalysis2中的station只包含站点的基本信息，通过他获取站点的id，这里是为了获取各个站点的详细监控参数)
		List<StationPara> stationPara = stationParaService.getStationParaBySid(id);
		if(stationPara.size()==0) {
			//如果前4分钟没查询到数据，那么直接返回
			System.out.println("id为" + id + "的站点前4分钟内没有数据，进入下次循环！！");
			return;
		}
		
		System.out.println("++++++++++++++id为：" + id + "的站点前4分钟内存在如下数据：++++++++++++++++");
		for(StationPara s: stationPara) {
			System.out.println(s);
		}
		
		//存放进口压力的List,一级入口
		List<Float> inletPressures = new ArrayList<Float>();
		
		//存放二级进口压力的List
		List<Float> inletPressures2 = new ArrayList<Float>();
		
		//存放出口压力的List，一级出口
		List<Float> outletPressures = new ArrayList<Float>();
		
		//存放二级出口的List
		List<Float> outletPressures2 = new ArrayList<Float>();
		
		//存放温度的List
		List<Integer> temperatures = new ArrayList<Integer>();
		
		//存放流量的List
		List<Integer> flows = new ArrayList<Integer>(); 
		
		//存放过滤器压差的List
		List<Integer> filter = new ArrayList<Integer>();
		
		//进行数据遍历分析
		for(int i=0;i<stationPara.size();i++) {
			//将各个数据存放到各个List中
			inletPressures.add(stationPara.get(i).getfInput1());
			inletPressures2.add(stationPara.get(i).getfInput2());
			outletPressures.add(stationPara.get(i).getfOutput1());
			outletPressures2.add(stationPara.get(i).getfOutput2());
			temperatures.add(stationPara.get(i).getfTemperature());
			flows.add(stationPara.get(i).getfFlow());
			filter.add(stationPara.get(i).getfFilter());
		}
		
		/*
		 * 一级进出口的参数判定 
		 */
		//监测进口压力是否持续下降
		int downFlag = 0;
		
		//监测出口压力是否持续下降
		int opdownFlag = 0;
		
		
		//监测进口压力是否下降至0
		int downToZero = 0;
		
		//监测出口压力是否持续下降至0
		int opdownToZero = 0;
		
		
		//监测进口压力是否持续上升
		int upFlag = 0;
		
		//监测出口压力是否持续上升
		int opupFlag = 0;
		
		
		//监测进口压力是否持续波动
		int fluctuationFlag = 0;
		
		//监测出口压力是否持续波动
		int opfluctuationFlag = 0;
		
		//监测进口压力是否超限，上限
		int outOfBoundsFlag = 0;
		
		//监测进口压力是否超下限
		int outOfBoundsFlagl = 0;
		
		//监测出口压力是否超限，上限
		int opoutOfBoundsFlag = 0;
		
		//监测出口压力是否超下限
		int opoutOfBoundsFlagl = 0;
		
		//进口压力超限参数，具体数值
		Float outOfBounds = 0f;
		
		//出口压力超限参数，具体超限数值
		Float opoutOfBounds = 0f;
		
		
		//温度是否差超上限
		int temperatureUpperLimitFlag = 0; 
		
		//温度是否超下限
		int temperatureLowerLimitFlag = 0;
		
		//温度是否波动
		int temperatureFluctuationFlag = 0;
		
		
		//流量超上限
		int FlowUpperLimitFlag = 0;
		
		//流量有波动
		int FlowFluctuationFlag = 0;
		
		//流量短时间变为0
		int FlowTurnZero = 0;
		
		
		//过滤器压差超上限
		int filterUpperLimit = 0;
		
		//过滤器压差持续上升
		int filterRise = 0;
		
		//过滤器压差为0
		int filterZero = 0;
		
		
		/*
		 * 二级进出口的参数判定 
		 */
		//监测进口压力是否持续下降
		int downFlag2 = 0;
		
		//监测出口压力是否持续下降
		int opdownFlag2 = 0;
		
		
		//监测进口压力是否下降至0
		int downToZero2 = 0;
		
		//监测出口压力是否持续下降至0
		int opdownToZero2 = 0;
		
		
		//监测进口压力是否持续上升
		int upFlag2 = 0;
		
		//监测出口压力是否持续上升
		int opupFlag2 = 0;
		
		
		//监测进口压力是否持续波动
		int fluctuationFlag2 = 0;
		
		//监测出口压力是否持续波动
		int opfluctuationFlag2 = 0;
		
		//监测进口压力是否超限，上限
		int outOfBoundsFlag2 = 0;
		
		//监测进口压力是否超下限
		int outOfBoundsFlagl2 = 0;
		
		//监测出口压力是否超限，上限
		int opoutOfBoundsFlag2 = 0;
		
		//监测出口压力是否超下限
		int opoutOfBoundsFlagl2 = 0;
		
		//进口压力超限参数
		Float outOfBounds2 = 0f;
		
		//出口压力超限参数
		Float opoutOfBounds2 = 0f;
		
		
		//进口压力判定的参数
//		int size = stations.size()-1;
		//这里注意一定是根据计算得出的size到底是多少*************
		int size = 2;
		
		
		System.out.println("多站点判断：从redis中取出的数据总共有" + (stationPara.size()) + "数据:" + inletPressures);
		
		/* 
		 * 1.首先做各个参数超限的判断，
		 * 一级入口压力，这里的报警持续判断是报警次数的判定（上限）
		*/
		if(inletPressures.get(inletPressures.size()-1)>INLETPRESSUREUPPERLIMIT) {
			outOfBoundsFlag = 1;
			//进口压力超限记录
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "一级入口压力超上限:" + inletPressures.get(inletPressures.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS.getFaultCause(),id);
		}
		
		//超下限
		if(inletPressures.get(inletPressures.size()-1)<INLETPRESSURELOWLIMIT) {
			outOfBoundsFlagl = 1;
			//进口压力超限记录
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "一级入口压力超下限:" + inletPressures.get(inletPressures.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL.getFaultCause(),id);
		}
		
		/*
		 * 一级出口压力超限的判断
		 */
		if(outletPressures.get(outletPressures.size()-1)>OUTLETPRESSUREUPPERLIMIT) {
			opoutOfBoundsFlag = 1;
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "一级出口压力超上限:" + outletPressures.get(outletPressures.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS.getFaultCause(),id);
			opoutOfBounds = outletPressures.get(outletPressures.size()-1);
		}
		
		
		/* 
		 * 超限的判断，这里的报警持续判断是报警次数的判定(下限)
		*/
		if(outletPressures.get(outletPressures.size()-1)<OUTLETPRESSURELOWLIMIT) {
			opoutOfBoundsFlagl = 1;
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "一级出口压力超下限:" + outletPressures.get(outletPressures.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL.getFaultCause(),id);
		}
		
		
		/*
		 * 二级进口压力超限的判断
		 */
		if(inletPressures2.get(inletPressures2.size()-1)>INLETPRESSUREUPPERLIMIT2) {
			outOfBoundsFlag2 = 1;
			//进口压力超限记录
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "二级入口压力超上限:" + inletPressures2.get(inletPressures2.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDS2.getFaultCause(),id);
		}
		
		//超下限
		if(inletPressures2.get(inletPressures2.size()-1)<INLETPRESSURELOWLIMIT2) {
			//进口压力超限记录
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "二级入口压力超下限:" + inletPressures2.get(inletPressures2.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_OUTOFBOUNDSL2.getFaultCause(),id);
		}
		
		/*
		 * 二级出口压力超限的判断
		 */
		if(outletPressures2.get(outletPressures2.size()-1)>OUTLETPRESSUREUPPERLIMIT2) {
			opoutOfBoundsFlag2 = 1;
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "二级出口压力超上限：" + outletPressures2.get(outletPressures2.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDS2.getFaultCause(),id);
		}
		
		
		/* 
		 * 超限的判断，这里的报警持续判断是报警次数的判定(下限)
		*/
		if(outletPressures2.get(outletPressures2.size()-1)<OUTLETPRESSURELOWLIMIT2) {
			opoutOfBoundsFlagl2 = 1;
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "二级出口压力超下限：" + outletPressures2.get(outletPressures2.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_OUTOFBOUNDSL2.getFaultCause(),id);
		}
		
		
		/*
		 * 温度超限的判断
		 */
		if(temperatures.get(temperatures.size()-1)>TEMPERATUREUPPERLIMIT) {
			//温度超上限
			temperatureUpperLimitFlag = 1;
			//记录
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "温度超上限：" + temperatures.get(temperatures.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.TEMPERATURE_UPPERLIMIT.getFaultTypes(), FaultDiagnosisEnums.TEMPERATURE_UPPERLIMIT.getFaultCause(),id);
		}
		 
		
		if(temperatures.get(temperatures.size()-1)<TEMPERATURELOWERLIMIT) {
			//温度超下限
			temperatureLowerLimitFlag = 1;
			//记录
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "温度超下限：" + temperatures.get(temperatures.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.TEMPERATURE_LOWERLIMIT.getFaultTypes(), FaultDiagnosisEnums.TEMPERATURE_LOWERLIMIT.getFaultCause(),id);
		}
		
		
		/*
		 * 流量超限的判断
		 */
		//流量短时间变为0
		if(flows.get(flows.size()-1)==0) {
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "流量变为0" + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FLOW_TURNTOZEROINSHORT.getFaultTypes(), FaultDiagnosisEnums.FLOW_TURNTOZEROINSHORT.getFaultCause(),id);
		}
		
		/* 
		 * 超限的判断，这里的报警持续判断是报警次数的判定
		*/
		if(flows.get(flows.size()-1)>FLOWUPPERLIMIT) {
			//流量超上限
			FlowUpperLimitFlag = 1;
			//记录
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "流量超上限：" + flows.get(flows.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FLOW_UPPERLIMIT.getFaultTypes(), FaultDiagnosisEnums.FLOW_UPPERLIMIT.getFaultCause(),id);
		}
		
		
		
		/*
		 * 过滤器压差超限的判断
		 */
		//无压差
		if(filter.get(filter.size()-1)==0) {
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "过滤器压差变成0" + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FILTERPRESSURE_TURNTOZEROINSHORT.getFaultTypes(), FaultDiagnosisEnums.FILTERPRESSURE_TURNTOZEROINSHORT.getFaultCause(),id);
		}
		
		
		/* 
		 * 超限的判断，这里的报警持续判断是报警次数的判定
		*/
		if(filter.get(filter.size()-1)>FILTERDIFFERENCE) {
			filterUpperLimit = 1;
			System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "过滤器压差已超上限：" + filter.get(filter.size()-1) + "****");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FILTERPRESSURE_UPPERLIMIT.getFaultTypes(), FaultDiagnosisEnums.FILTERPRESSURE_UPPERLIMIT.getFaultCause(),id);
		}
		
		
		
		
		/*
		 * 2.做趋势的判断
		 * 
		 * 这里从redis缓存中获取数据进行判断的时候一定能要注意取出数据的顺序，
		 * 在redis中获取的数据一般都先进去的数据打印的打印的时候是在后面的、
		 *也就是说，若果往里面存放数据的时候是34、78，那么从其中获取的时候就是逆序的：78、34
		 *所以这里的判断其实就是相反序列的反判断
		 * 
		 */
		for(int i=1;i<stationPara.size();i++) {
			
			/*
			 * 1.一级进口压力的判断
			 */
			//持续下降的判断（一级）
			if(inletPressures.get(i)-inletPressures.get(i-1)<0) {
				downFlag++;
				if(inletPressures.get(i)==0&&downFlag!=0) {
					downToZero = 1;
					//进口压力下降至0记录
					System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + " 一级入口压力下降至0****");
					faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_DROPTOZERO.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_DROPTOZERO.getFaultCause(),id);
				}
			}
			
			//持续上升的判断
			if(inletPressures.get(i)-inletPressures.get(i-1)>0) {
				upFlag++;
			}
			
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(inletPressures.get(i)-inletPressures.get(0))<=INLETPRESSUREFLUCTUATION) {
				if(Math.abs(inletPressures.get(i)-inletPressures.get(i-1))!=0) {
					fluctuationFlag++;
				}
			}
			
			
			
			/*
			 * 2.出口压力的判断
			 */
			//一级出口压力持续下降的判断
			if(outletPressures.get(i)-outletPressures.get(i-1)<0) {
				opdownFlag++;
				if(outletPressures.get(i)==0&&opdownFlag!=0) {
					System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "一级出口压力下降至0" + "****");
					faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_DROPTOZERO.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_DROPTOZERO.getFaultCause(),id);
					opdownToZero = 1;
				}
			}
			
			//持续上升的判断
			if(outletPressures.get(i)-outletPressures.get(i-1)>0) {
				opupFlag++;
			}
			
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(outletPressures.get(i)-outletPressures.get(0))<=OUTLETPRESSUREFLUCTUATION) {
				if(Math.abs(outletPressures.get(i)-outletPressures.get(i-1))!=0) {
					opfluctuationFlag++;
				}
			}
			
			
			/*
			 * 1.2二级进口的参数判断
			 */
			/*
			 * 1.进口压力的判断
			 */
			//持续下降的判断（一级）
			if(inletPressures2.get(i)-inletPressures2.get(i-1)<0) {
				downFlag2++;
				if(inletPressures2.get(i)==0&&downFlag2!=0) {
					downToZero2 = 1;
					//进口压力下降至0记录
					System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + " 二级入口压力下降至0****");
					faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_DROPTOZERO2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_DROPTOZERO2.getFaultCause(),id);
				}
			}
			
			//持续上升的判断
			if(inletPressures2.get(i)-inletPressures2.get(i-1)>0) {
				upFlag2++;
			}
			
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(inletPressures2.get(i)-inletPressures2.get(0))<=INLETPRESSUREFLUCTUATION2) {
				if(Math.abs(inletPressures2.get(i)-inletPressures2.get(i-1))!=0) {
					fluctuationFlag2++;
				}
			}
			
			
			/*
			 * 2.2 二级出口压力的判断
			 */
			//持续下降的判断
			if(outletPressures2.get(i)-outletPressures2.get(i-1)<0) {
				opdownFlag2++;
				if(outletPressures2.get(i)==0&&opdownFlag2!=0) {
					System.out.println("****故障记录：站点id为" + id + sdf.format(new Date()) + "二级出口压力下降至0" + "****");
					faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_DROPTOZERO2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_DROPTOZERO2.getFaultCause(),id);
					opdownToZero2 = 1;
				}
			}
			
			//持续上升的判断
			if(outletPressures2.get(i)-outletPressures2.get(i-1)>0) {
				opupFlag2++;
			}
			
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(outletPressures2.get(i)-outletPressures2.get(0))<=OUTLETPRESSUREFLUCTUATION2) {
				if(Math.abs(outletPressures2.get(i)-outletPressures2.get(i-1))!=0) {
					opfluctuationFlag2++;
				}
			}
			
			
			/*
			 * 3.温度的判断
			 */
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(temperatures.get(i)-temperatures.get(0))<=TEMPERATUREFLUCTUATION) {
				if(temperatures.get(i)-temperatures.get(i-1)!=0) {
					temperatureFluctuationFlag++;
				}
			}
			
			
			/*
			 * 4.流量的判断
			 */
			//范围内波动的判断,上下不能这里是域值为前面定义的
			if(Math.abs(flows.get(i)-flows.get(0))<=FLOWFLUCTUATION) {
				if(flows.get(i)-flows.get(i-1)!=0) {
					FlowFluctuationFlag++;
				}
			}
			
			
			/*
			 * 5.过滤器压差的判断
			 */
			//持续上升的判断
			if(filter.get(i)-filter.get(i-1)>0) {
				filterRise++;
			}
			
			
		}
		
		/*
		 * 1.一级进口压力的判断
		 */
		//一级入口压力持续下降的判断
		if(downFlag>=size) {
			System.out.println("**********故障记录： 站点id为" + id + sdf.format(new Date()) + ":一级进口压力正在持续下降**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUEDECLINE.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUEDECLINE.getFaultCause(),id);
		}
		
		//下降至0在上面迭代的情况中进行判断
		
		//上升趋势的判断
		if(upFlag>=size) {
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":一级进口压力正在持续上升**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUERISE.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUERISE.getFaultCause(),id);
		}
		
		//范围内的波动判定
		if(fluctuationFlag>=size) {
			System.out.println("**********故障记录： 站点id为" + id + sdf.format(new Date()) + ":一级进口压力在指定范围内波动**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUOUSFLUCTUATION.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUOUSFLUCTUATION.getFaultCause(),id);
		}
		
		
		
		/*
		 * 1.二级进口压力的判断
		 * 进行两两比较，这里取得是3个点，如果每次后一次的数据都比前一次要小，
		 * 那么判断此时的是参数是持续下降的,所有的持续性判断都是取的4个点
		 */
		if(downFlag2>=size) {//这里是如果有3个点持续下降的话，那么判断为持续下降
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":二级进口压力正在持续下降**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUEDECLINE2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUEDECLINE2.getFaultCause(),id);
		}
		
		//上升趋势的判断
		if(upFlag2>=size) {
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":二级进口压力正在持续上升**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUERISE2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUERISE2.getFaultCause(),id);
		}
		
		//波动域值的判断
		if(fluctuationFlag2>=size) {
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":二级进口压力正在指定范围内波动**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.INPRESSURE_CONTINUOUSFLUCTUATION2.getFaultTypes(), FaultDiagnosisEnums.INPRESSURE_CONTINUOUSFLUCTUATION2.getFaultCause(),id);
		}
		
		
		/*
		 * 2.出口压力的判断
		 * 进行两两比较，这里取得是3个点，如果每次后一次的数据都比前一次要小，
		 * 那么判断此时的是参数是持续下降的,所有的持续性判断都是取的4个点
		 */
		if(opdownFlag>=size) {//这里是如果有3个点持续下降的话，那么判断为持续下降
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":一级出口压力正在持续下降**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUEDECLINE.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUEDECLINE.getFaultCause(),id);
		}
		
		
		//上升趋势的判断
		if(opupFlag>=size) {
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":一级出口压力正在持续上升**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUERISE.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUERISE.getFaultCause(),id);
		}
		
		//波动域值的判断
		if(opfluctuationFlag>=size) {
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":一级出口压力正在指定范围内波动**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUOUSFLUCTUATION.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUOUSFLUCTUATION.getFaultCause(),id);
		}
		
		
		/*
		 * 2.2二级出口压力的判定
		 */
		/*
		 * 2.2出口压力的判断（二级出口）
		 * 进行两两比较，这里取得是3个点，如果每次后一次的数据都比前一次要小，
		 * 那么判断此时的是参数是持续下降的,所有的持续性判断都是取的4个点
		 */
		if(opdownFlag2>=size) {//这里是如果有3个点持续下降的话，那么判断为持续下降
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":二级出口压力正在持续下降**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUEDECLINE2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUEDECLINE2.getFaultCause(),id);
		}
		
		
		//上升趋势的判断
		if(opupFlag2>=size) {
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":二级出口压力正在持续上升**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUERISE2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUERISE2.getFaultCause(),id);
		}
		
		//波动域值的判断
		if(opfluctuationFlag2>=size) {
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":二级出口压力正在指定范围内波动**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.OUTPRESSURE_CONTINUOUSFLUCTUATION2.getFaultTypes(), FaultDiagnosisEnums.OUTPRESSURE_CONTINUOUSFLUCTUATION2.getFaultCause(),id);
		}
		
		
		/*
		 * 3.温度的判断
		 */
		//判断温度是否有波动
		if(temperatureFluctuationFlag>=size) {
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":温度在指定范围内波动**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.TEMPERATURE_FLUCTUATION.getFaultTypes(), FaultDiagnosisEnums.TEMPERATURE_FLUCTUATION.getFaultCause(),id);
		}
		
		
		/*
		 * 4.流量的判断
		 */
		//流量有波动
		if(FlowFluctuationFlag>=size) {
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":流量在指定范围内波动**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FLOW_FLUCTUATION.getFaultTypes(), FaultDiagnosisEnums.FLOW_FLUCTUATION.getFaultCause(),id);
		}
		
		/*
		 * 5.过滤器压差的判断
		 */
		//过滤器持续上升的判断
		if(filterRise>=size) {
			System.out.println("**********故障记录：站点id为" + id + sdf.format(new Date()) + ":过滤器压差在指定范围持续上升**********");
			faultLogDao.recordLog2(new Timestamp(new Date().getTime()), FaultDiagnosisEnums.FILTERPRESSURE_CONTINUERISE.getFaultTypes(), FaultDiagnosisEnums.FILTERPRESSURE_CONTINUERISE.getFaultCause(),id);
		}
		
	}

}






