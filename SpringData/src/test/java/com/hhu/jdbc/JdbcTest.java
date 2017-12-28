package com.hhu.jdbc;

import com.hhu.dao.Student;
import com.hhu.util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * 测试JDBC
 */
public class JdbcTest {

    @Test
    public void getConnectionTest() throws Exception {
        Connection conn = JDBCUtil.getConnection();
        System.out.println(conn);
    }

    @Test
    public void insertTest() {
        StudentDao studentDao = new StudentDao();
        Student s = new Student("testAdd", 20);
        studentDao.insertStudent(s);
    }

    @Test
    public void deleteTest() {
        StudentDao studentDao = new StudentDao();
        studentDao.deleteById(2);
    }

    @Test
    public void updateTest() {
        StudentDao studentDao = new StudentDao();
        studentDao.updateById("janey3", 34, 3);
    }

    @Test
    public void findAllTest() {
        StudentDao studentDao = new StudentDao();
        List list = studentDao.findAll();
        System.out.println(list);
    }

}
