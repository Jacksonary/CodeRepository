package com.hhu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hhu.base.BaseDAO;
import com.hhu.entity.RoleFunction;

@Repository
public class RoleFunctionDao extends BaseDAO {
	//这个都是就是结果集中的对应表中的记录
	private class RoleFunctionMapper implements RowMapper<RoleFunction> {

		@Override
		public RoleFunction mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			RoleFunction roleFunction = new RoleFunction();
			roleFunction.setId(rs.getLong("id"));
			roleFunction.setRoleId(rs.getLong("role_id"));
			roleFunction.setFunctionId(rs.getLong("function_id"));
			roleFunction.setStatus(rs.getInt("status"));
			return roleFunction;
		}
		
	}

	public RoleFunction getRoleFunctionById(Long id) {
		String sql = "select * from auth_role_function where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RoleFunctionMapper());
	}
	
	//批量保存
	public void saveRoleFunctions(Collection<RoleFunction> roleFunctions) {
		String sql = "insert into auth_role_function(role_id,function_id,status) values(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		roleFunctions.forEach((rf)->{
			Object[] obj = new Object[3];
			obj[0] = rf.getRoleId();
			obj[1] = rf.getFunctionId();
			obj[2] = rf.getStatus();
			batchArgs.add(obj);
		});
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	public List<RoleFunction> findRoleFunctionsByRoleId(Long id) {
		String sql = "select * from auth_role_function where role=?";
		return jdbcTemplate.query(sql, new Object[]{id}, new RoleFunctionMapper());
	}
	
	public void deleteByRoleId(Long id) {
		String sql = "delete * from auth_role_function where role=?";
		jdbcTemplate.update(sql, id);
	}
	
}
