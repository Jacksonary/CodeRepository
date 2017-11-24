package com.hhu.service;

import java.util.List;

import com.hhu.entity.Station;

/**
 * 故障诊断接口
 * @author Weiguo Liu
 *
 */
public interface FaultDiagnosisService {

	//进口压力监测
	public String inletPressureDiagnosis();
	
	//出口压力检测
	public String outletPressureDiagnosis();
	
	//温度检测
	public String temperatureDiagnosis();
	
	//流量监测
	public String flowDiagnosis();
	
	//过滤器压差监测
	public String filterlDiagnosis();
	
	//切断检测
	public String cutOffDiagnosis();
	
	//泄露监测
	public String leakDiagnosis();
	
	//全局的数据分析(单个站点)
	public String datasAnalysis(List<Station> stations);
	
	//全局的数据分析(多个站点)
	public void datasAnalysis2(List<Station> stations);
	
}
