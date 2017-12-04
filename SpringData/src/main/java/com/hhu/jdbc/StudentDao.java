package com.hhu.jdbc;

import com.hhu.dao.Student;
import com.hhu.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 利用jdbc对数据库进行增删改查
 */
public class StudentDao {

    /**
     * 增操作
     */
    public void insertStudent(Student s) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "insert into student(name, age) values(?,?)";
        try {
            conn = JDBCUtil.getConnection();
            //预编译sql语句
            pst = conn.prepareStatement(sql);
            //给占位符设参
            pst.setString(1, s.getName());
            pst.setInt(2, s.getAge());
            //执行SQL
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(rs, pst, conn);
        }
    }

    /**
     * 删操作
     */
    public void deleteById(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "delete from student where id=?";
        try {
            conn = JDBCUtil.getConnection();
            //预编译sql语句
            pst = conn.prepareStatement(sql);
            //给占位符设参
            pst.setInt(1, id);
            //执行SQL
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(rs, pst, conn);
        }
    }

    /**
     * 该操作
     */
    public void updateById(String name, int age, int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "update student set name=?,age=? where id=?";
        try {
            conn = JDBCUtil.getConnection();
            //预编译sql语句
            pst = conn.prepareStatement(sql);
            //给占位符设参
            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setInt(3, id);
            //执行SQL
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(rs, pst, conn);
        }
    }

    /**
     * 查操作
     */
    public List<Student> findAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select id,name,age from student";
        List<Student> list = new ArrayList<Student>();
        try {
            conn = JDBCUtil.getConnection();
            System.out.println(conn);
            //预编译sql语句
            pst = conn.prepareStatement(sql);
            //执行SQL
            rs = pst.executeQuery(sql);
            System.out.println(rs);
            while(rs.next()) {
                Student s = new Student(rs.getString("name"),rs.getInt("age"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(rs, pst, conn);
        }
        return list;
    }

}
