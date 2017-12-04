package com.hhu.repository;

import com.hhu.dao.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试Repository
 */
public class EmployeeRepositoryTest {

    //定义上下文
    private ApplicationContext ctx = null;

    private EmployeeRepository employeeRepository = null;

    //分页接口
    private EmployeePagingAndSortingRepository employeePagingAndSortingRepository = null;

    //jpa接口
    private EmployeeJpaRepository employeeJpaRepository = null;

    //jpaSpecificationExecutor接口
    private EmployeeJpaSpecificationExecutor employeeJpaSpecificationExecutor = null;

    @Before
    public void doBefore() {
        System.out.println("set up...");
        ctx = new ClassPathXmlApplicationContext("beans.xml");
        employeeRepository = (EmployeeRepository) ctx.getBean(EmployeeRepository.class);
        employeePagingAndSortingRepository = (EmployeePagingAndSortingRepository)ctx.getBean("employeePagingAndSortingRepository");
        employeeJpaRepository = (EmployeeJpaRepository)ctx.getBean("employeeJpaRepository");
        employeeJpaSpecificationExecutor = (EmployeeJpaSpecificationExecutor)ctx.getBean("employeeJpaSpecificationExecutor");
    }

    @Test
    public void findByNameStartWithAndAgeLessThanTest() {
        List<Employee> list = employeeRepository.findByNameStartingWithAndAgeLessThan("test",24);
        System.out.println(list);
    }

    @Test
    public void findByNameEndingWithAndAgeGreaterLanTest() {
        List<Employee> list = employeeRepository.findByNameEndingWithAndAgeGreaterThan("5",23);
        System.out.println(list);
    }

    @Test
    public void employeeRepositoryTest() {
        String name = "jack";
        Employee employee = employeeRepository.findByName(name);
        System.out.println(employee);
    }

    @Test
    public void findByNameContainingTest() {
        List<Employee> list = employeeRepository.findByNameContaining("5");
        System.out.println(list);
    }

    @Test
    public void findByNameLike() {
        List<Employee> list = employeeRepository.findByNameLike("5");
        System.out.println(list);
    }

    @Test
    public void findByNameInOrAgeLessThan() {
        List<String> names = new ArrayList<String>();
        names.add("jack");
        names.add("air");
        names.add("test");
        names.add("test1");
        List<Employee> list = employeeRepository.findByNameInOrAgeLessThan(names,23);
        System.out.println(list);
    }

    @Test
    public void getMaxIdTest() {
        Employee e = employeeRepository.getMaxId();
        System.out.println(e);
    }

    @Test
    public void getTestRecodsTest() {
        List<Employee> e = employeeRepository.getTestRecords("test5",27);
        System.out.println("select e:" + e);
    }

    @Test
    public void getTestRecords2Test() {
        List<Employee> list = employeeRepository.getTestRecords2("jack",25);
        System.out.println(list);
    }

    @Test
    public void queryLike1Test() {
        List<Employee> list = employeeRepository.queryLike1("test");
        System.out.println(list);
    }

    @Test
    public void queryLike2Test() {
        List<Employee> list = employeeRepository.queryLike2("a");
        System.out.println(list);
    }

    @Test
    public void getCountTest() {
        System.out.println(employeeRepository.getCount());
    }

    @Test
    public void update() {
        employeeRepository.update(18,11);
    }

    /*
    测试分页
     */
    @Test
    public void pageTest() {
        //Page对象是Spring内置的对象，从第0页开始，每页显示5条记录
        Pageable page = new PageRequest(1, 5);//创建一个Page对象，第1页（即第二页）,每页显示5条记录
        Page<Employee> employeePage = employeePagingAndSortingRepository.findAll(page);
        System.out.println("总页数：" + employeePage.getTotalPages());
        System.out.println("总记录数：" + employeePage.getTotalElements());
        System.out.println("当前页码（从第0页开始）：" + employeePage.getNumber());
        System.out.println("当前页记录的集合：" + employeePage.getContent());
        System.out.println("当前页面包含的记录数：" + employeePage.getNumberOfElements());
    }

    @Test
    public void PageSortTest() {
        //创建排序对象（排序方式，按照那个字段排序），注意这里是先排序再分页
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order);
        Pageable page = new PageRequest(0, 8, sort);
        Page<Employee>  sortPage = employeePagingAndSortingRepository.findAll(page);
        System.out.println("当前页的记录数：" + sortPage.getNumberOfElements());
        System.out.println("当前页码：" + sortPage.getNumber());
        System.out.println("当前页记录的集合：" + sortPage.getContent());
        System.out.println("总记录数：" + sortPage.getTotalElements());
        System.out.println("总页数：" + sortPage.getTotalPages());
    }

    /**
     * 测试JpaRepository接口
     */
    @Test
    public void JpaTest() {
        Employee e = employeeJpaRepository.findOne(99);
        System.out.println(e);

        boolean r1 = employeeJpaRepository.exists(78);
        boolean r2 = employeeJpaRepository.exists(120);
        System.out.println("r1:" + r1);
        System.out.println("r2:" + r2);
    }

    /**
     * 测试JpaSpecificationExecutor接口
     *
     * 实现功能：分页（第一页，每页5条记录）+排序（按id降序）+查询条件（比如年龄大于50）
     */
    @Test
    public void jpaSpecificationTest() {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order);

        Pageable pageable = new PageRequest(0, 5, sort);
        //创建Specification对象
        Specification<Employee> specification = new Specification<Employee>() {
            /***
             *
             * @param root 就是待查询的数据类型（这里就是Employee）
             * @param criteriaQuery 添加查询条件
             * @param criteriaBuilder 构建predicate返回
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //root就是查询的对象Employee,从里面可以获取Employee中的age属性
                Path path = root.get("age");

                //criteriaBuilder就是构建的查询条件
                Predicate predicate = criteriaBuilder.gt(path, 50);
                return predicate;
            }
        };

        //这里可以将查询的条件添加进finadAll()方法中
        Page<Employee> page = employeeJpaSpecificationExecutor.findAll(specification, pageable);

        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前页记录的集合：" + page.getContent());
        System.out.println("当前页的记录数：" + page.getNumberOfElements());
        System.out.println("当前页码：" + page.getNumber());
    }



    @After
    public void doAfter() {
        if(ctx!=null) {
            ctx = null;
        }
    }

}
