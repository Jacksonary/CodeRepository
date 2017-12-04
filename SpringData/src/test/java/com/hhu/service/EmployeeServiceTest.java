package com.hhu.service;

import com.hhu.dao.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceTest {

    private ApplicationContext ctx = null;

    private EmployeeService employeeService = null;

    @Before
    public void doBefore() {
        System.out.println("set up...");
        ctx = new ClassPathXmlApplicationContext("beans.xml");
        employeeService = (EmployeeService) ctx.getBean("employeeService");
    }

    @After
    public void doAfter() {
        if(ctx!=null) {
            ctx = null;
        }
    }

    @Test
    public void updateTest() {
        System.out.println("ctx: " + ctx);
        System.out.println("employeeService: " + employeeService);
        employeeService.update(18, 11);
    }

    //测试crud
    @Test
    public void crudSaveTest() {
        List list = new ArrayList();
//        list.add(new Employee("curdSave1",33));
//        list.add(new Employee("crudSave2",34));
//        list.add(new Employee("crudSave3",35));
//        list.add(new Employee("crudSave4",36));
//        list.add(new Employee("crudSave5",37));
//        list.add(new Employee("crudSave6",38));
        for(int i=1;i<100;i++) {
            list.add(new Employee("crudSave"+i, 100-i));
        }
        employeeService.save(list);
    }

//    public void
}
