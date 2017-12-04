package com.hhu.repository;

import com.hhu.dao.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 继承分页查询的接口PagingAndSortingRepository
 */
public interface EmployeePagingAndSortingRepository extends PagingAndSortingRepository<Employee, Integer>{

}
