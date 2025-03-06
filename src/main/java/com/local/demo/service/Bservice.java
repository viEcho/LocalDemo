package com.local.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO 添加注释
 *
 * @author: echo
 * @date: 2025/3/1
 */
@Service
public class Bservice {

    @Autowired
    Aservice aservice;

    public void testB(){
        aservice.testA();
        System.out.println("B服务的testB方法");
    }
}
