package com.hhu.dao;

import javax.persistence.*;

/**
 * 雇员，这个实体类用于测试jpa对于table的创建
 * jpa中所有的基本数据类型建议使用封装类型
 */
@Entity//这个注解表明他是一个实体类，根据xml中的配置会自动创建这个实体类的映射表
//@Table(name = "")//给table命名，默认就是实体名的小写
public class Employee {

    @Id//这个注解表明这个属性值是id
    @GeneratedValue//这个注解表明他是策略是id自增
    private Integer id;

    @Column(length = 20)//该注解是指定创建表字段时候的长度（默认为255）
    private String name;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
