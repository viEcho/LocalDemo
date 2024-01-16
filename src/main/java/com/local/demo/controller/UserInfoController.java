package com.local.demo.controller;

import com.local.demo.entity.UserInfo;
import com.local.demo.global.ResponseVO;
import com.local.demo.test.shejimoshi.strategy.CommonStrategyFactory;
import com.local.demo.test.shejimoshi.strategy.business.BaseUserQueryByDbTypeStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;

/**
 * 用户信息控制器
 *
 * @author echo
 * @date 2024/01/14
 */
@RestController
@RequestMapping("/userInfo")
@Slf4j
public class UserInfoController {

    /**
     * 策略工厂
     */
    @Autowired
    private CommonStrategyFactory commonStrategyFactory;

    /**
     * 查询用户信息
     *
     * @param id     主键
     * @param dbType db类型
     * @return {@link ResponseVO}
     */
    @GetMapping("/query")
    ResponseVO queryUserInfo(@PathParam("id") Long id, @PathParam("dbType") Integer dbType) {
        ResponseVO vo = new ResponseVO();
        UserInfo userInfo = commonStrategyFactory.handle(BaseUserQueryByDbTypeStrategy.class, dbType, id);
        return vo.data(userInfo);
    }
}
