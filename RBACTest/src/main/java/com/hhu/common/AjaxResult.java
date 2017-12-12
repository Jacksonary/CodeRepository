package com.hhu.common;

import java.io.Serializable;

public class AjaxResult implements Serializable{
	
	private static final long serialVersionUID = 4827912956130174405L;
	
	public static final Integer AJAX_STATUS_CODE_SUCCESS = 0;
	public static final Integer AJAX_STATUS_CODE_WARN = 1;
	public static final Integer AJAX_STATUS_CODE_ERROR = 2;
	
	private Integer statusCode;
	
	private String message;
	
	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AjaxResult() {
		super();
	}
	
	public static AjaxResult success() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatusCode(AJAX_STATUS_CODE_SUCCESS);
		ajaxResult.setMessage("操作成功");
		return ajaxResult;
	}
	
	public static AjaxResult warn() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatusCode(AJAX_STATUS_CODE_WARN);
		return ajaxResult;
	}
	
	public static AjaxResult error() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatusCode(AJAX_STATUS_CODE_ERROR);
		ajaxResult.setMessage("操作失败");
		return ajaxResult;
	}
	
}
