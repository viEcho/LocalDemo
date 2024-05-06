package com.local.demo.aop;

import com.local.demo.enums.FakerTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Faker {

     FakerTypeEnum fakerType() default FakerTypeEnum.IGNORE;

     String customVal() default "";
}
