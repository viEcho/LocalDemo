package com.local.demo.test.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * @description: TODO
 * @author: echo
 * @date: 2023/2/13
 */
public class BeanServiceTest implements InitializingBean, DisposableBean, ApplicationContextAware,
        ApplicationEventPublisherAware, BeanClassLoaderAware, BeanFactoryAware,
        BeanNameAware, EnvironmentAware, ImportAware, ResourceLoaderAware {

    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【步骤2】执行Bean的set方法,设置name属性值：" + name);
        this.name = name;
    }
    public void setSex(String sex) {
        System.out.println("【步骤2】执行Bean的set方法,设置sex属性值：" + sex);
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "IocBeanLifeService{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public BeanServiceTest(){
        System.out.println("【步骤1】执行Bean的无参构造函数");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【步骤12】执行InitializingBean的afterPropertiesSet方法");

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("【步骤16】执行DisposableBean接口的destroy方法");
    }


    //通过<bean>的destroy-method属性指定的销毁方法
    public void destroyMethod() throws Exception {
        System.out.println("【步骤17】执行配置的destroy-method");
    }

    //通过<bean>的init-method属性指定的初始化方法
    public void initMethod() throws Exception {
        System.out.println("【步骤13】执行配置的init-method");
    }

    @PostConstruct
    public void initPostConstruct(){
        System.out.println("【步骤11】执行PostConstruct注解标注的方法");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("【步骤15】执行preDestroy注解标注的方法");
    }


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("【步骤4】执行BeanClassLoaderAware中setBeanClassLoader,ClassLoader的name = " + classLoader.getClass().getName());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【步骤5】执行BeanFactoryAware中setBeanFactory,beanFactory中是否包含IocBeanLifeService：" + beanFactory.containsBean("iocBeanLifeService"));
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("【步骤3】执行BeanNameAware中setBeanName方法，beanName值："
                + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【步骤9】执行ApplicationContextAware的setApplicationContext方法，Bean Definition Names="
                + Arrays.toString(applicationContext.getBeanDefinitionNames()));

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("【步骤8】执行ApplicationEventPublisherAware中setApplicationEventPublisher方法");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("【步骤6】执行EnvironmentAware的setEnvironment方法");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        Resource resource = resourceLoader.getResource("classpath:applicationContext.xml");
        System.out.println("【步骤7】执行ResourceLoaderAware的setResourceLoader方法，Resource File Name="
                + resource.getFilename());

    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        System.out.println("执行setImportMetadata");
    }
}
