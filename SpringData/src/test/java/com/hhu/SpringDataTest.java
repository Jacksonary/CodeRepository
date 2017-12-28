package com.hhu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试jpa,根据实体类直接生成table
 */
public class SpringDataTest {

    //Spring的上下文
    ApplicationContext ctx = null;

    //初始化Spring的上下文
    @Before
    public void doBefore(){
        System.out.println("set up...");
        ctx = new ClassPathXmlApplicationContext("beans.xml");
    }

    @Test
    public void SpringDataTest() {

    }

    @After
    public void doAfter() {
        if(ctx!=null) {
            ctx = null;
        }
        System.out.println("destoried!");
    }

}
