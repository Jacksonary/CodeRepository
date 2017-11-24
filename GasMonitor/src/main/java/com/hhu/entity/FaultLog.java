package com.hhu.entity;

import java.sql.Timestamp;

public class FaultLog {

	//日志ID
	private int faultId;
	
	//站点id
	private int stationId;
	
	//故障时间
	private Timestamp time;
	//故障类型
	private String faultTypes;
	//故障的可能原因
	private String faultCause;

	public int getFaultId() {
		return faultId;
	}

	public void setFaultId(int faultId) {
		this.faultId = faultId;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getFaultTypes() {
		return faultTypes;
	}

	public void setFaultTypes(String faultTypes) {
		this.faultTypes = faultTypes;
	}

	public String getFaultCause() {
		return faultCause;
	}

	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}

	@Override
	public String toString() {
		return "FaultLog [faultId=" + faultId + ", stationId=" + stationId + ", time=" + time + ", faultTypes="
				+ faultTypes + ", faultCause=" + faultCause + "]";
	}

}
