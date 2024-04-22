package com.local.demo.aop;

import com.local.demo.enums.FakerTypeEnum;

public @interface Faker {

     FakerTypeEnum fakerType();

     String customVal();
}
