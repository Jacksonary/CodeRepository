package com.hhu.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 将后台的分页信息发送到前台
 * @author Weiguo Liu
 *
 */
public class Datagrid {

	  private long total; 
	  private List rows = new ArrayList<>(); 
	  public Datagrid() { 
	    super(); 
	  } 
	  public Datagrid(long total, List rows) { 
	    super(); 
	    this.total = total; 
	    this.rows = rows; 
	  } 
	  public long getTotal() { 
	    return total; 
	  } 
	  public void setTotal(long total) { 
	    this.total = total; 
	  } 
	  public List getRows() { 
	    return rows; 
	  } 
	  public void setRows(List rows) { 
	    this.rows = rows; 
	  }
	
}
