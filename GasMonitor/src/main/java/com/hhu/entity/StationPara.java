package com.hhu.entity;

import java.sql.Timestamp;

public class StationPara {

	private int fId;
	private int fSid;
	private float fInput1;
	private float fInput2;
	private float fOutput1;
	private float fOutput2;
	private int fTemperature;
	private int fFlow;
	private int fFilter;
	private Timestamp fTime;

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public int getfSid() {
		return fSid;
	}

	public void setfSid(int fSid) {
		this.fSid = fSid;
	}

	public float getfInput1() {
		return fInput1;
	}

	public void setfInput1(float fInput1) {
		this.fInput1 = fInput1;
	}

	public float getfInput2() {
		return fInput2;
	}

	public void setfInput2(float fInput2) {
		this.fInput2 = fInput2;
	}

	public float getfOutput1() {
		return fOutput1;
	}

	public void setfOutput1(float fOutput1) {
		this.fOutput1 = fOutput1;
	}

	public float getfOutput2() {
		return fOutput2;
	}

	public void setfOutput2(float fOutput2) {
		this.fOutput2 = fOutput2;
	}

	public int getfTemperature() {
		return fTemperature;
	}

	public void setfTemperature(int fTemperature) {
		this.fTemperature = fTemperature;
	}

	public int getfFlow() {
		return fFlow;
	}

	public void setfFlow(int fFlow) {
		this.fFlow = fFlow;
	}

	public int getfFilter() {
		return fFilter;
	}

	public void setfFilter(int fFilter) {
		this.fFilter = fFilter;
	}

	public Timestamp getfTime() {
		return fTime;
	}

	public void setfTime(Timestamp fTime) {
		this.fTime = fTime;
	}

	@Override
	public String toString() {
		return "StationPara [fId=" + fId + ", fSid=" + fSid + ", fInput1=" + fInput1 + ", fInput2=" + fInput2
				+ ", fOutput1=" + fOutput1 + ", fOutput2=" + fOutput2 + ", fTemperature=" + fTemperature + ", fFlow="
				+ fFlow + ", fFilter=" + fFilter + ", fTime=" + fTime + "]";
	}

}
