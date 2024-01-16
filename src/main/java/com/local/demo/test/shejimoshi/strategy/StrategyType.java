package com.local.demo.test.shejimoshi.strategy;


import java.lang.annotation.*;

/**
 * 策略类型
 *
 * @author echo
 * @date 2024/01/12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrategyType {

    Class<? extends BaseCommonStrategy<?,?,?>> value();

}
