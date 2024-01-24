package com.local.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * mvc流测试控制器
 *
 * @author echo
 * @date 2024/01/24
 */
@RestController
@RequestMapping("/mvc")
public class MvcFlowTestController {


    /**
     * 指数
     *
     * @return {@link String}
     */
    @RequestMapping("/index")
    public String index() {
        //返回视图名称
        return "index";
    }

    /**
     * 目标
     *
     * @return {@link String}
     */
    @RequestMapping("/target")
    public String toTarget() {
        return "target";
    }

}
