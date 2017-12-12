package com.hhu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hhu.base.BaseDAO;
import com.hhu.entity.User;

@Repository
public class UserDao extends BaseDAO {
	
	//这个都是就是结果集中的对应表中的记录
	private class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setName(rs.getString("name"));
			user.setPwd(rs.getString("pwd"));
			return user;
		}
		
	}

	public User getUser(String name, String pwd) {
		String sql = "select * from auth_user where name=? and pwd=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{name, pwd}, new UserMapper());
	}
	
	public void save(User user) {
		String sql = "insert into auth_user(name,pwd) values(?,?)";
		jdbcTemplate.update(sql, user.getName(), user.getPwd());
	}
	
	public void deleteById(Long id) {
		String sql = "delete from auth_user where id=?";
		jdbcTemplate.update(sql, id);
	}
	
	public void update(User user) {
		String sql = "update auth_user set name=?,pwd=? where id=?";
		jdbcTemplate.update(sql, user.getName(), user.getPwd(), user.getId());
	}
	
	public User findById(Long id) {
		String sql = "select * from auth_user where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
	}
	
	public Collection<User> find(Collection<Long> ids) {
		StringBuilder sql = new StringBuilder("select * from auth_user where id in(");
		Object[] args = new Object[ids.size()];
		AtomicInteger index= new AtomicInteger(0);
		ids.forEach(id -> {
			sql.append(id).append("?, ");
			args[index.getAndIncrement()] = id;
		});
		/*
		 * 拼接后的sql为“select * from auth_user where id in(id1, id2, id3,)”
		 * 这里需要将最后一个逗号去除，即倒数第二位的字符
		 */
		sql.deleteCharAt(sql.length()-2);
		sql.append(")");
		return jdbcTemplate.query(sql.toString(), ids.toArray(new Object[0]), new UserMapper());
	}
	
	//分页查询角色信息,注意sql语句的写法
	public List<User> findUsers(int page, int size) {
		String sql = "select * from auth_user limit ?,?";
		return jdbcTemplate.query(sql, new Object[]{(page-1)*size, size}, new UserMapper());
	}

}
