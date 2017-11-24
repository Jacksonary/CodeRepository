package com.java1234.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.PageBean;
import com.java1234.model.Student;
import com.java1234.util.DateToStringUtil;
import com.java1234.util.StringUtil;

/**
 * 学生数据库的操作类
 * @author Weiguo Liu
 *
 */
public class StudentDao {

	//查询所有学生类,整合search功能
	public ResultSet studentList(Connection conn,PageBean pageBean,Student student,String bbirthday,String ebirthday) throws Exception{
		//这里的select * from t_student s,t_grade g where s.gradeId=g.id是关联查询的语句
		StringBuffer sb = new StringBuffer("select * from t_student s,t_grade g where s.gradeId=g.id");
		if(StringUtil.isNotEmpty(student.getStuNo())){
			//这里 的sql语句是模糊查询的意思
			sb.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
		}
		if(StringUtil.isNotEmpty(student.getStuName())){
			//这里 的sql语句是模糊查询的意思
			sb.append(" and s.stuName like '%" + student.getStuName() + "%'");
		}
		if(StringUtil.isNotEmpty(student.getSex())){
			//这里 的sql语句是具体查询，不是模糊查询
			sb.append(" and s.sex='" + student.getSex() + "'");
		}
		if(student.getGradeId()!=-1){
			sb.append(" and s.gradeId='" + student.getGradeId() + "'");
		}
		if(StringUtil.isNotEmpty(bbirthday)){
			//这里 的sql语句是将日期转换成具体的天数方便进行日期范围的判断，取下限
			sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
		}
		if(StringUtil.isNotEmpty(ebirthday)){
			//这里 的sql语句也是将日期转换成具体的天数方便进行范围的判断，取上限
			sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
		}
		if(pageBean!=null){
			sb.append(" limit " + pageBean.getStartNum() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	//查询表中所有的记录条数,也是关联查询
	public int studentCount(Connection conn,Student student,String bbirthday,String ebirthday) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_student s,t_grade g where s.gradeId=g.id");
		
		if(StringUtil.isNotEmpty(student.getStuNo())){
			//这里 的sql语句是模糊查询的意思
			sb.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
		}
		if(StringUtil.isNotEmpty(student.getStuName())){
			//这里 的sql语句是模糊查询的意思
			sb.append(" and s.stuName like '%" + student.getStuName() + "%'");
		}
		if(StringUtil.isNotEmpty(student.getSex())){
			//这里 的sql语句是具体查询，不是模糊查询
			sb.append(" and s.sex='" + student.getSex() + "'");
		}
		if(student.getGradeId()!=-1){
			sb.append(" and s.gradeId='" + student.getGradeId() + "'");
		}
		if(StringUtil.isNotEmpty(bbirthday)){
			//这里 的sql语句是将日期转换成具体的天数方便进行日期范围的判断，取下限
			sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
		}
		if(StringUtil.isNotEmpty(ebirthday)){
			//这里 的sql语句也是将日期转换成具体的天数方便进行范围的判断，取上限
			sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
		}
		
		//将and替换成where
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){//如果有记录存在的话
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	//添加学生类
	public int studentAdd(Connection conn,Student t) throws Exception{
		String sql = "insert into t_student values(null,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, t.getStuName());
		pstmt.setString(2, t.getStuNo());
		pstmt.setString(3, t.getSex());
		//将日期格式转成String格式放进去
		pstmt.setString(4, DateToStringUtil.formatDate(t.getBirthday(),"yyyy-MM-dd"));
		pstmt.setInt(5, t.getGradeId());
		pstmt.setString(6, t.getEmail());
		pstmt.setString(7, t.getStuDesc());
		return pstmt.executeUpdate();
	}
	
	//删除指定id的学生,可批量删除
	public int studentDelete(Connection conn,String delIds)throws Exception{
		String sql = "delete from t_student where stuId in (" + delIds + ")";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	//编辑指定id的学生
	public int studentModify(Connection conn,Student t) throws Exception{
		String sql = "update t_student set stuName=?,stuNo=?,sex=?,birthday=?,gradeId=?,email=?,stuDesc=? where stuId=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, t.getStuName());
		pstmt.setString(2, t.getStuNo());
		pstmt.setString(3, t.getSex());
		pstmt.setString(4, DateToStringUtil.formatDate(t.getBirthday(), "yyyy年MM月dd日"));
		pstmt.setInt(5, t.getGradeId());
		pstmt.setString(6, t.getEmail());
		pstmt.setString(7, t.getStuDesc());
		pstmt.setInt(8, t.getStuId());
		return pstmt.executeUpdate();
	}
	
	public boolean getStudentByGradeId(Connection conn,String gradeId) throws Exception{
		String sql = "select * from t_student where gradeId=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, gradeId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
}
