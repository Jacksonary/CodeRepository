package com.hhu.domain;

/*
 * 用户存放具体属性的新旧属性
 */
public class ChangeItem {
	
	private String field;
	
	private String fieldShowName;
	
	private String oldValue;
	
	private String newValue;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldShowName() {
		return fieldShowName;
	}

	public void setFieldShowName(String fieldShowName) {
		this.fieldShowName = fieldShowName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	
}
