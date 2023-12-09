package com.local.demo.test.beanlife;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: TODO
 * @author: echo
 * @date: 2023/2/13
 */
public class TestBeanLife {

    @Test
    public void test(){
        System.out.println("Spring容器初始化===========================");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Spring容器初始化完毕========================");

        System.out.println("从容器中获取Bean");
        BeanServiceTest service = context.getBean("iocBeanLifeService", BeanServiceTest.class);

        System.out.println("service:"+service.toString());

        System.out.println("Spring容器准备关闭==========================");
        context.close();
        System.out.println("Spring容器完成关闭===========================");
    }
}
