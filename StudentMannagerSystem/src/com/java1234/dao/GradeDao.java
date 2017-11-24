package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.Grade;
import com.java1234.model.PageBean;
import com.java1234.util.StringUtil;

/**
 * 班级操作的工具类
 * @author Weiguo Liu
 *
 */
public class GradeDao {

	//查询所有班级,这里将搜索和工具栏中的search项整合到一起了,这里的追加语句一定要注意空格！！！
	public ResultSet gradeList(Connection conn,PageBean pageBean,Grade grade) throws Exception{
		StringBuffer sb = new StringBuffer("select * from t_grade");
		if(grade!=null && StringUtil.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%" + grade.getGradeName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit " + pageBean.getStartNum() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	//统计数据表中的总记录数
	public int gradeCount(Connection conn,Grade grade) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_grade");
		if(StringUtil.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
		}
		//将and替换成where
		PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){//如果有记录存在的话
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	//添加新的班级进数据表
	public int gradeAdd(Connection conn,Grade grade) throws Exception{
		String sql = "insert into t_grade values(null,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		return pstmt.executeUpdate();
	}
	
	//删除指定id的班级信息,这里是批量删除
	public int gradeDelete(Connection conn,String delIds) throws Exception{
		String sql = "delete from t_grade where id in("+delIds+")";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	//编辑指定id的班级信息
	public int gradeModeify(Connection conn,Grade grade) throws Exception{
		String sql = "update t_grade set gradeName=?,gradeDesc=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		pstmt.setInt(3, grade.getId());
		return pstmt.executeUpdate();
	}
	
}
