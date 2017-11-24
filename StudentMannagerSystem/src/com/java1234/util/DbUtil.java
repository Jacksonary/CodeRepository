package com.java1234.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ���ݿ����ӹ�����ķ�װ
 * @author Weiguo Liu
 *
 */
public class DbUtil {

	private String username = "root";
	//这里改用阿里的RDS远程数据库
	//private String password = "921228jack";
	//private String url = "jdbc:mysql://localhost:3306/db_studentinfo";
	private String url = "jdbc:mysql://rm-uf687854w4i9izna5o.mysql.rds.aliyuncs.com:3306/myrds";
	private String password = "921228Jack";
	private String driverClass = "com.mysql.jdbc.Driver";
	private Connection conn = null;
	
	public Connection getConnection(){
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("数据库连接对象：" + conn.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
		return conn;
	}
	
	//�ر����ݿ�����
	public void closeConnection(Connection conn) throws Exception{
		if(conn!=null||!conn.equals("")){
			conn.close();
		}
	}
	
	//���ڲ������ݿ������Ƿ�����
	public static void main(String[] args){
		DbUtil dbUtil = new DbUtil();
		dbUtil.getConnection();
	}
}
