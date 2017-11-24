package com.java1234.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ��Resultset�������վת����JsonArray����Ĺ�����
 * @author Weiguo Liu
 *
 */
public class JsonUtil {

	/**
	 * ��ResultSet����ת����JsonArray����
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
		ResultSetMetaData md=rs.getMetaData();//��ȡ����Ϣ
		int num=md.getColumnCount();
		JSONArray array=new JSONArray();
		while(rs.next()){
			JSONObject mapOfColValues=new JSONObject();
			for(int i=1;i<=num;i++){
				//�����������ڸ�ʽ�Ļᱨ����Ҫ�����жϲ��Ҵ���:������ת���ַ���Ȼ��������JSON�м���,����û���������͵����ݣ���ô����ʡȥ
				//�ж϶����������instanceof,������Ƕ�ResultSet���ص����͵����ݶ���Ľ��ж�������ж�
				Object o = rs.getObject(i);
				if(o instanceof Date){
					mapOfColValues.put(md.getColumnName(i), DateToStringUtil.formatDate((Date)o, "yyyy年MM月dd日"));
				}else{
					//�������ͨ���͵��ַ���ֱ������ȥ������ģ��Ѱ�ÿ����ֵ�Է�װ��ȥ
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
				}
				
			}
			array.add(mapOfColValues);
		}
		return array;
	}
}
