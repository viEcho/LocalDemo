package com.local.demo.test.shejimoshi.strategy.business;

import com.local.demo.entity.UserInfo;
import com.local.demo.enums.DbTypeEnums;
import com.local.demo.service.SlaveUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 按缓存和从库策略查询用户
 *
 * @author echo
 * @date 2024/01/12
 */
@Service
@Slf4j
public class QueryUserByCacheAndSlaveStrategy extends BaseUserQueryByDbTypeStrategy{

    @Autowired
    private SlaveUserService slaveUserService;

    @Override
    public Integer routeKey() {
        return DbTypeEnums.CACHE_AND_SLAVE.getCode();
    }

    @Override
    public UserInfo handle(Long id) {
        log.info("查询缓存和从库。。。");
        // 先查询缓存
        // 如果缓存查询为空,再查询从库。。。代码略
        return slaveUserService.queryUserInfoById(id);
    }
}
