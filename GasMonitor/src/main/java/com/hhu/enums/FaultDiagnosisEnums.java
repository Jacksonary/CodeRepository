package com.hhu.enums;

public enum FaultDiagnosisEnums {
	
	/*
	 * 一级进出口的错误情况
	 */
	//进口压力
	INPRESSURE_DROPTOZERO(10,"一级入口压力下降至零","调压器正常，进口上游关闭，放散排污直通大气或管道泄漏；上游关闭，同时调压器直通，放散排污直通大气或管道泄漏"),
	INPRESSURE_CONTINUEDECLINE(11,"一级入口压力持续下降","上游供气暂停或供气流量、压力降低，满足不了终端需求"),
	INPRESSURE_CONTINUERISE(12,"一级入口压力持续上升","上游调压器关不住"),
	INPRESSURE_OUTOFBOUNDS(13,"一级入口压力超上限","上游调压器关不住或调压器精度降低"),
	INPRESSURE_OUTOFBOUNDSL(130,"一级入口压力超下限","上游调压器关不住或调压器精度降低"),
	INPRESSURE_CONTINUOUSFLUCTUATION(14,"一级入口压力在一定范围内连续波动","上游调压器喘动"),
	
	
	//出口压力
	OUTPRESSURE_DROPTOZERO(20,"一级出口压力下降至零","调压、切断或阀门关闭"),
	OUTPRESSURE_CONTINUEDECLINE(21,"一级出口压力持续下降","进口供气不足，或终端用气量过大"),
	OUTPRESSURE_CONTINUERISE(22,"一级出口压力持续上升","调压器关不住"),
	OUTPRESSURE_OUTOFBOUNDS(23,"一级出口压力超上限","调压器关不住或关高或调压器精度降低"),
	OUTPRESSURE_OUTOFBOUNDSL(230,"一级出口压力超下限","调压器关不住或关高或调压器精度降低"),
	OUTPRESSURE_CONTINUOUSFLUCTUATION(24,"一级出口压力在一定范围内连续波动","调压器喘动"), 
	OUTPRESSURE_CONTINUOUSFLUCTUATETIME(25,"一级出口压力在一定时间段连续波动","终端用气量集中"),
	OUTPRESSURE_HIGHINTIME(26,"一级出口压力在一段是时间内超高，但没有达到上限，其他时间段正常","调压器关闭压力高"),
	
	/*
	 * 二级级进出口错误情况
	 */
	//进口压力
	INPRESSURE_DROPTOZERO2(210,"二级入口压力下降至零","调压器正常，进口上游关闭，放散排污直通大气或管道泄漏；上游关闭，同时调压器直通，放散排污直通大气或管道泄漏"),
	INPRESSURE_CONTINUEDECLINE2(211,"二级入口压力持续下降","上游供气暂停或供气流量、压力降低，满足不了终端需求"),
	INPRESSURE_CONTINUERISE2(212,"二级入口压力持续上升","上游调压器关不住"),
	INPRESSURE_OUTOFBOUNDS2(213,"二级入口压力超限","上游调压器关不住或调压器精度降低"),
	INPRESSURE_OUTOFBOUNDSL2(213,"二级入口入口压力超下限","上游调压器关不住或调压器精度降低"),
	INPRESSURE_CONTINUOUSFLUCTUATION2(214,"二级入口压力在一定范围内连续波动","上游调压器喘动"),
	
	
	//出口压力
	OUTPRESSURE_DROPTOZERO2(220,"二级出口压力下降至零","调压、切断或阀门关闭"),
	OUTPRESSURE_CONTINUEDECLINE2(221,"二级出口压力持续下降","进口供气不足，或终端用气量过大"),
	OUTPRESSURE_CONTINUERISE2(222,"二级出口压力持续上升","调压器关不住"),
	OUTPRESSURE_OUTOFBOUNDS2(223,"二级出口压力超上限","调压器关不住或关高或调压器精度降低"),
	OUTPRESSURE_OUTOFBOUNDSL2(223,"二级出口压力超下限","调压器关不住或关高或调压器精度降低"),
	OUTPRESSURE_CONTINUOUSFLUCTUATION2(224,"二级出口压力在一定范围内连续波动","调压器喘动"), 
	OUTPRESSURE_CONTINUOUSFLUCTUATETIME2(225,"二级出口压力在一定时间段连续波动","终端用气量集中"),
	OUTPRESSURE_HIGHINTIME2(226,"二级出口压力在一段是时间内超高，但没有达到上限，其他时间段正常","调压器关闭压力高"),
	
	
	//温度
	TEMPERATURE_FLUCTUATION(30,"温度波动","气体含水量大"),
	TEMPERATURE_UPPERLIMIT(31,"温度超上限","加热器或电伴热失效"),
	TEMPERATURE_LOWERLIMIT(32,"温度超下限","冰堵，换热器不工作"),
	 
	//流量
	FLOW_UPPERLIMIT(40,"流量超高限","终端用气量过大或放散、排污阀打开"),
	FLOW_TURNTOZEROINSHORT(41,"流量短时间变为零","调压器或切断阀或阀门关闭"),
	FLOW_FLUCTUATION(42,"流量有波动","终端用气量不稳定"),
	FLOW_DIFFERENCE(43,"不同流量计参数有差异","计量精度误差或设定修正仪参数有误"),
	
	 
	//切断
	 
	//泄漏
	 
	//过滤器压差
	FILTERPRESSURE_WARNING(50,"过滤器压差有报警","滤芯堵塞或取压管堵塞"),
	FILTERPRESSURE_UPPERLIMIT(51,"过滤器压差超上限","滤芯堵塞或取压管堵塞"),
	FILTERPRESSURE_CONTINUERISE(52,"过滤器压差持续上升","滤芯堵塞或取压管堵塞"),
	FILTERPRESSURE_TURNTOZEROINSHORT(53,"过滤器压差突然变零","滤芯损坏或取压管堵塞");
	

    private int code;
    private String faultTypes;
    private String faultCause;

    FaultDiagnosisEnums(int code, String faultTypes,String faultCause) {
        this.code = code;
        this.faultTypes = faultTypes;
        this.faultCause = faultCause;
    }

    public int getCode() {
		return code;
	}

	public String getFaultTypes() {
		return faultTypes;
	}

	public String getFaultCause() {
		return faultCause;
	}

	public static FaultDiagnosisEnums stateOf(int index)
    {
        for (FaultDiagnosisEnums fault : values())
        {
            if (fault.getCode()==index)
            {
                return fault;
            }
        }
        return null;
    }
}
