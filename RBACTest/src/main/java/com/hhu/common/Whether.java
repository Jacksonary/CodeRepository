package com.hhu.common;

public enum Whether {
	
	YES(1),
	NO(0);
	
	private int value;
	
	private Whether(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
