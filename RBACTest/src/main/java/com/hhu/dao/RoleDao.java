package com.hhu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hhu.base.BaseDAO;
import com.hhu.entity.Role;
import com.mysql.jdbc.Statement;

@Repository
public class RoleDao extends BaseDAO {
	
	//这个都是就是结果集中的对应表中的记录
		private class RoleMapper implements RowMapper<Role> {

			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Role role = new Role();
				role.setId(rs.getLong("id"));
				role.setName(rs.getString("name"));
				return role;
			}
			
		}

		public void save(Role role) {
			String sql = "insert into auth_role(name) values(?)";
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub
					PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, role.getName());
					return ps;
				}
			}, holder);
			role.setId(holder.getKey().longValue());
		}
		
		public void deleteById(Long id) {
			String sql = "delete from auth_role where id=?";
			jdbcTemplate.update(sql, id);
		}
		
		public void update(Role role) {
			String sql = "update auth_role set name=? where id=?";
			jdbcTemplate.update(sql, role.getName(), role.getId());
		}
		
		public Role findById(Long id) {
			String sql = "select * from auth_role where id=?";
			return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RoleMapper());
		}
		
		public List<Role> find(Collection<Long> ids) {
			if(null==ids||ids.isEmpty()) {
				return new ArrayList<>();
			}
			
			StringBuilder sql = new StringBuilder("select * from auth_role where id in(");
			Object[] args = new Object[ids.size()];
			AtomicInteger index= new AtomicInteger(0);
			ids.forEach(id -> {
				sql.append("?, ");
				args[index.getAndIncrement()] = id;
			});
			/*
			 * 拼接后的sql为“select * from auth_role where id in(id1, id2, id3,)”
			 * 这里需要将最后一个逗号去除，即倒数第二位的字符
			 */
			sql.deleteCharAt(sql.length()-2);
			sql.append(")");
			return jdbcTemplate.query(sql.toString(), ids.toArray(new Object[0]), new RoleMapper());
		}
		
		//分页查询角色信息,注意sql语句的写法
		public List<Role> findRoles(int page, int size) {
			String sql = "select * from auth_role limit ?,?";
			return jdbcTemplate.query(sql, new Object[]{(page-1)*size, size}, new RoleMapper());
		}

}
