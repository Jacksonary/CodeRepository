package com.hhu.service;

import com.hhu.dao.Employee;
import com.hhu.repository.EmployeeCrudRepository;
import com.hhu.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    @Transactional
    public void update(Integer age, Integer id) {
        employeeRepository.update(age, id);
    }

    @Transactional
    public void save(List<Employee> list) {
        employeeCrudRepository.save(list);
    }

}
