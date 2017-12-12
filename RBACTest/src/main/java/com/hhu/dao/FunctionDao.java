package com.hhu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hhu.base.BaseDAO;
import com.hhu.entity.Function;
import com.mysql.jdbc.Statement;

@Repository
public class FunctionDao extends BaseDAO {
	
	//这个都是就是结果集中的对应表中的记录
	private class FunctionMapper implements RowMapper<Function> {

		@Override
		public Function mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Function function = new Function();
			function.setId(rs.getLong("id"));
			function.setName(rs.getString("name"));
			function.setParentId(rs.getLong("parent_id"));
			function.setSerialNum(rs.getInt("serial_num"));
			function.setAccordiont(rs.getInt("accordiont"));
			return function;
		}
		
	}

	public Function getFunction(String name, String pwd) {
		String sql = "select * from auth_function where name=? and pwd=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{name, pwd}, new FunctionMapper());
	}
	
	public void save(Function function) {
		String sql = "insert into auth_function(name,parent_id,url,serial_num,accordiont) values(?,?,?,?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, function.getName());
				ps.setLong(2, function.getParentId());
				ps.setString(3, function.getUrl());
				ps.setInt(4, function.getSerialNum());
				ps.setInt(5, function.getAccordiont());
				return ps;
			}
		}, holder);
		
		function.setId(holder.getKey().longValue());
		
//		jdbcTemplate.update(sql, function.getName(), function.getParentId(), function.getUrl(), function.getSerialNum(), function.getAccordiont());
		
	}
	
	public void deleteById(Long id) {
		String sql = "delete from auth_function where id=?";
		jdbcTemplate.update(sql, id);
	}
	
	//根据id更新url
	public void update(Long id, String url) {
		String sql = "update auth_function set url=? where id=?";
		jdbcTemplate.update(sql, url, id);
	}
	
	//分页查询功能
	public List<Function> findFunctions(int page, int size, Long parentId) {
		String sql = "select * from auth_function where parent_id=? limit?,?";
		return jdbcTemplate.query(sql, new Object[]{parentId,(page-1)*size,size},new FunctionMapper());
	}
	
	public Function findById(Long id) {
		String sql = "select * from auth_function where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new FunctionMapper());
	}
	
	public List<Function> getAllFunctions() {
		String sql = "select * from auth_function";
		return jdbcTemplate.query(sql, new FunctionMapper());
	}

}
