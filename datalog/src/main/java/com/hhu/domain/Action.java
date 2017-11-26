package com.hhu.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/*
 * 操作行为的实体类，用于记录用户操作行为的属性
 */
public class Action {

	private String id;//这里的id是MongoDB中的id
	private Long objectId;
	private String objectClass;
	private String operator;
	private Date operatorTime;
	private ActionType actionType;
	/*
	 * 这个List是用于存储更改Product具体属性信息的新旧值
	 * 比如修改其中一个Product的一个具体属性：价格
	 * 那么其中的存储内容就为价格这个属性的新旧值：属性名、旧值、新值（如果操作了就记录，没有操作就不记录类似于将Product的每个属性单独搞一个Item存放这个List中）
	 * 
	 * 新增操作：Product中有8个属性，所以新增时会这个List中会有8个Item，所有的属性旧值为空，新值即为插入的各个属性
	 * 更新操作：对某个Product的某几个属性进行更新时，那么只会记录更新的某几个属性，那么List中将只会存放这几个更新前后的值
	 * 删除操作：对某个Product执行删除操作时，那么会记录这个Product的所有属性（8个），旧值即为原有的属性值，新值即为空
	 * 
	 */
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
