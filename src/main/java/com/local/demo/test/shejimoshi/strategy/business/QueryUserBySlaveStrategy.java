package com.local.demo.test.shejimoshi.strategy.business;

import com.local.demo.entity.UserInfo;
import com.local.demo.enums.DbTypeEnums;
import com.local.demo.service.SlaveUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 按从库策略查询用户
 *
 * @author echo
 * @date 2024/01/12
 */
@Service
@Slf4j
public class QueryUserBySlaveStrategy extends BaseUserQueryByDbTypeStrategy{

    /**
     * 从库用户服务
     */
    @Autowired
    private SlaveUserService slaveUserService;

    /**
     * 路由关键字
     *
     * @return {@link Integer}
     */
    @Override
    public Integer routeKey() {
        return DbTypeEnums.SLAVE.getCode();
    }

    @Override
    public UserInfo handle(Long id) {
        log.info("查询从库。。。");
        return slaveUserService.queryUserInfoById(id);
    }
}
