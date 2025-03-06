package com.local.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO 添加注释
 *
 * @author: echo
 * @date: 2025/3/1
 */
@Service
public class Aservice {
    public void testA(){
        System.out.println("A服务的testA方法");
    }
}
