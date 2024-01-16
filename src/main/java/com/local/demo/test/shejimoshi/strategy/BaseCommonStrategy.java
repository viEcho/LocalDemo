package com.local.demo.test.shejimoshi.strategy;

/**
 * 公共策略接口
 *
 * @author echo
 * @date 2024/01/12
 */
public interface BaseCommonStrategy <P,K,R>{

    /**
     * 路由关键字
     *
     * @return {@link K}
     */
    K routeKey();

    /**
     * 处理
     *
     * @param p p
     * @return {@link R}
     */
    R handle(P p);

}
