package com.hhu.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/*
 * 操作行为的实体类
 */
public class Action {

	private String id;
	private Long objectId;
	private String objectClass;
	private String operator;
	private Date operatorTime;
	private ActionType actionType;
	private List<ChangeItem> changes = new ArrayList<>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public String getObjectClass() {
		return objectClass;
	}
	public void setObjectClass(String objectClass) {
		this.objectClass = objectClass;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	public ActionType getActionType() {
		return actionType;
	}
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	public List<ChangeItem> getChanges() {
		return changes;
	}
	public void setChanges(List<ChangeItem> changes) {
		this.changes = changes;
	}
	
	
	
}
