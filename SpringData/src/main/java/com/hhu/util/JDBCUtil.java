package com.hhu.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * jdbc的工具类，用于Connection的获取和资源的销毁
 */
public class JDBCUtil {


    /**
     * 将方法定义成静态方法，这样可以直接使用“类名+方法名”的方式方便调用
     * @return 获取的驱动
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //读取jdbc的配置文件
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        //将输入流载入properties
        properties.load(is);

        String userName = properties.getProperty("jdbc.userName");
        String password = properties.getProperty("jdbc.password");
        String url = properties.getProperty("jdbc.url");
        String driverClass = properties.getProperty("jdbc.driverClass");

        //加载驱动
        Class.forName(driverClass);

        Connection conn = DriverManager.getConnection(url, userName, password);

        return conn;
    }

    /**
     * 关闭连接资源
     * @param rs 结果集
     * @param st 编译对象
     * @param conn 连接对象
     */
    public static void closeResource(ResultSet rs, Statement st, Connection conn) {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(st!=null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
