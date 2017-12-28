package com.hhu.repository;

import com.hhu.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeJpaSpecificationExecutor extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
}
