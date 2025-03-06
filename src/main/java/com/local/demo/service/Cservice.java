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
public class Cservice {

    @Autowired
    Aservice aservice;

    public void testC(){
        aservice.testA();
        System.out.println("C服务的testC方法");
    }
}
