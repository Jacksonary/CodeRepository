package com.java1234.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���������͵�װ���ַ�������,���м򵥵ķ�װ
 * @author Weiguo Liu
 *
 */
public class DateToStringUtil {

	//将date转换为String类型
	public static String formatDate(Date date,String format){
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(date!=null){
			result = sdf.format(date);
		}
		return result;
	}
	
	//将String转换为Date类型
	public static Date formatString(String str,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}
}
