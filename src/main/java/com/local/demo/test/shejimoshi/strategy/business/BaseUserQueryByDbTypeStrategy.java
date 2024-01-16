package com.local.demo.test.shejimoshi.strategy.business;

import com.local.demo.entity.UserInfo;
import com.local.demo.test.shejimoshi.strategy.StrategyType;

/**
 * 基于数据库类型策略基本用户查询
 *
 * @author echo
 * @date 2024/01/12
 */
@StrategyType(BaseUserQueryByDbTypeStrategy.class)
public abstract class BaseUserQueryByDbTypeStrategy implements BaseUserQueryStrategy<Long,Integer, UserInfo>{

}
