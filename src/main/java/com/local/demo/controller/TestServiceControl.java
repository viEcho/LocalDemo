package com.local.demo.controller;

import com.local.demo.global.ResponseVO;
import com.local.demo.service.Bservice;
import com.local.demo.service.Cservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.text.ParseException;

/**
 * 测试 无增强的对象 是不是就是bean本身
 *
 * @author: echo
 * @date: 2025/3/1
 */
@RestController
@RequestMapping("/testService")
public class TestServiceControl {

    @Autowired
    Bservice bservice;

    @Autowired
    Cservice cservice;

    @GetMapping("/test")
    ResponseVO queryUserInfo()  {
        bservice.testB();
        cservice.testC();
        return ResponseVO.success();
    }
}
