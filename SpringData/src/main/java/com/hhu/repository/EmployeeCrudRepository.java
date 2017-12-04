package com.hhu.repository;

import com.hhu.dao.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * 继承CrudRepository接口,该接口又继承了顶级接口Repository
 */
public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer>{
}
