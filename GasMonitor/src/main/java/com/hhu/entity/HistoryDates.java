package com.hhu.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 管道节点采集的历史数据
 * 
 * @author Weiguo Liu
 *
 */
public class HistoryDates {

	private int fId; 
	private Timestamp fTm;
	private String fStCode;
	private BigDecimal fV0;
	private int fS0;
	private BigDecimal fV1;
	private int fS1;
	private BigDecimal fV2;
	private int fS2;
	private BigDecimal fV3;
	private int fS3;
	private BigDecimal fV4;
	private int fS4;
	private BigDecimal fF0;
	private BigDecimal fF1;
	private BigDecimal fC;
	private BigDecimal fP;
	private BigDecimal fF2;
	private BigDecimal fS12;

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public Timestamp getfTm() {
		return fTm;
	}

	public void setfTm(Timestamp fTm) {
		this.fTm = fTm;
	}

	public String getfStCode() {
		return fStCode;
	}

	public void setfStCode(String fStCode) {
		this.fStCode = fStCode;
	}

	public BigDecimal getfV0() {
		return fV0;
	}

	public void setfV0(BigDecimal fV0) {
		this.fV0 = fV0;
	}

	public int getfS0() {
		return fS0;
	}

	public void setfS0(int fS0) {
		this.fS0 = fS0;
	}

	public BigDecimal getfV1() {
		return fV1;
	}

	public void setfV1(BigDecimal fV1) {
		this.fV1 = fV1;
	}

	public int getfS1() {
		return fS1;
	}

	public void setfS1(int fS1) {
		this.fS1 = fS1;
	}

	public BigDecimal getfV2() {
		return fV2;
	}

	public void setfV2(BigDecimal fV2) {
		this.fV2 = fV2;
	}

	public int getfS2() {
		return fS2;
	}

	public void setfS2(int fS2) {
		this.fS2 = fS2;
	}

	public BigDecimal getfV3() {
		return fV3;
	}

	public void setfV3(BigDecimal fV3) {
		this.fV3 = fV3;
	}

	public int getfS3() {
		return fS3;
	}

	public void setfS3(int fS3) {
		this.fS3 = fS3;
	}

	public BigDecimal getfV4() {
		return fV4;
	}

	public void setfV4(BigDecimal fV4) {
		this.fV4 = fV4;
	}

	public int getfS4() {
		return fS4;
	}

	public void setfS4(int fS4) {
		this.fS4 = fS4;
	}

	public BigDecimal getfF0() {
		return fF0;
	}

	public void setfF0(BigDecimal fF0) {
		this.fF0 = fF0;
	}

	public BigDecimal getfF1() {
		return fF1;
	}

	public void setfF1(BigDecimal fF1) {
		this.fF1 = fF1;
	}

	public BigDecimal getfC() {
		return fC;
	}

	public void setfC(BigDecimal fC) {
		this.fC = fC;
	}

	public BigDecimal getfP() {
		return fP;
	}

	public void setfP(BigDecimal fP) {
		this.fP = fP;
	}

	public BigDecimal getfF2() {
		return fF2;
	}

	public void setfF2(BigDecimal fF2) {
		this.fF2 = fF2;
	}

	public BigDecimal getfS12() {
		return fS12;
	}

	public void setfS12(BigDecimal fS12) {
		this.fS12 = fS12;
	}

	@Override
	public String toString() {
		return "HistoryDates [fId=" + fId + ", fTm=" + fTm + ", fStCode=" + fStCode + ", fV0=" + fV0 + ", fS0=" + fS0
				+ ", fV1=" + fV1 + ", fS1=" + fS1 + ", fV2=" + fV2 + ", fS2=" + fS2 + ", fV3=" + fV3 + ", fS3=" + fS3
				+ ", fV4=" + fV4 + ", fS4=" + fS4 + ", fF0=" + fF0 + ", fF1=" + fF1 + ", fC=" + fC + ", fP=" + fP
				+ ", fF2=" + fF2 + ", fS12=" + fS12 + "]";
	}

}
