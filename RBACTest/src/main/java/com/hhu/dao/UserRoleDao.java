package com.hhu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hhu.base.BaseDAO;
import com.hhu.entity.UserRole;

@Repository
public class UserRoleDao extends BaseDAO {
	
	//这个都是就是结果集中的对应表中的记录
	private class UserRoleMapper implements RowMapper<UserRole> {

		@Override
		public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			UserRole userRole = new UserRole();
			userRole.setId(rs.getLong("id"));
			userRole.setRole(rs.getLong("role"));
			userRole.setUserId(rs.getLong("user_id"));
			return userRole;
		}
		
	}
	
	public UserRole findById(Long id) {
		String sql = "select * from auth_user_role where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRoleMapper());
	}
	
	public List<UserRole> findByUserId(Long id) {
		String sql = "select * from auth_user_role where user_id=?";
		return jdbcTemplate.query(sql, new Object[]{id}, new UserRoleMapper());
	}
	
	public void save(UserRole userRole) {
		String sql = "insert into auth_user_role(role,user_id) values(?,?)";
		jdbcTemplate.update(sql, userRole.getRole(), userRole.getUserId());
	}
	
	public void deleteByUserId(Long userId) {
		String sql = "delete from auth_user_role where user_id=?";
		jdbcTemplate.update(sql, userId);
	}
	
	public List<UserRole> findUserRoles(int page, int size) {
		String sql = "select * from auth_user_role order by user_id limit?,?";
		return jdbcTemplate.query(sql, new Object[]{(page-1)*size, size}, new UserRoleMapper());
	}
	
	//批量保存
	public void saveUserRoles(Collection<UserRole> userRoles) {
		String sql = "insert into auth_user_role(role,user_id) values(?,?)";
		List<Object[]> bachArgs = new ArrayList<Object[]>();
		userRoles.forEach((ur)->{
			Object[] objs = new Object[2];
			objs[0] = ur.getUserId();
			objs[1] = ur.getRole();
			bachArgs.add(objs);
		});
		jdbcTemplate.batchUpdate(sql, bachArgs);
	}
	
}
