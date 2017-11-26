package com.hhu.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp2String {

	public String formatTimeStramp(Timestamp timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(timestamp);
	}
	
	public static void main(String[] args) {
		Date date = new Date(2016, 9, 7, 8, 57, 23);
		Date now = new Date();
		System.out.println(date + "---" + now);
	}
}
