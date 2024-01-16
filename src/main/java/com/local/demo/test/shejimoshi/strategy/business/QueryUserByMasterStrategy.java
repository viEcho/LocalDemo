package com.local.demo.test.shejimoshi.strategy.business;

import com.local.demo.entity.UserInfo;
import com.local.demo.enums.DbTypeEnums;
import com.local.demo.service.MasterUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 按主库略查询用户
 *
 * @author echo
 * @date 2024/01/12
 */
@Service
@Slf4j
public class QueryUserByMasterStrategy extends BaseUserQueryByDbTypeStrategy{

    /**
     * 主库用户服务
     */
    @Autowired
    private MasterUserService masterUserService;

    /**
     * 路由关键字
     *
     * @return {@link Integer}
     */
    @Override
    public Integer routeKey() {
        return DbTypeEnums.MASTER.getCode();
    }

    /**
     * 处理
     *
     * @param id 主键
     * @return {@link UserInfo}
     */
    @Override
    public UserInfo handle(Long id) {
        log.info("查询主库。。。");
        return masterUserService.queryUserInfoById(id);
    }
}
