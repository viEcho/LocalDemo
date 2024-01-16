package com.local.demo.test.shejimoshi.strategy.business;

import com.local.demo.entity.UserInfo;
import com.local.demo.enums.DbTypeEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 按缓存策略查询用户
 *
 * @author echo
 * @date 2024/01/12
 */
@Service
@Slf4j
public class QueryUserByCacheStrategy extends BaseUserQueryByDbTypeStrategy{


    /**
     * 路由关键字
     *
     * @return {@link Integer}
     */
    @Override
    public Integer routeKey() {
        return DbTypeEnums.CACHE.getCode();
    }

    /**
     * 处理
     *
     * @param id 主键
     * @return {@link UserInfo}
     */
    @Override
    public UserInfo handle(Long id) {
        log.info("查询缓存。。。");
        // 查询缓存
        return new UserInfo();
    }
}
