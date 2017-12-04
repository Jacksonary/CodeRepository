package com.hhu.repository;

import com.hhu.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 测试JpsRepository接口
 */
public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer>{

}
