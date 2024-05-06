package com.local.demo.enums;

import lombok.Getter;

@Getter
public enum FakerTypeEnum {

    IGNORE(1,"","忽略此字段"),
    NAME(2,"faker.name()","随机姓名"),
    ADDRESS(3,"faker.address()","随机地址"),
    NOW_TIME(4,"datetime.now().strftime('%Y-%m-%d %H:%M:%S')","当前时间"),
    PWD(4,"faker.password()","随机密码"),

    ;

    private final int code;

    private final String fakerVal;

    private final String fakerValDesc;

    FakerTypeEnum(int code,String fakerVal,String fakerValDesc){
        this.code = code;
        this.fakerVal = fakerVal;
        this.fakerValDesc = fakerValDesc;
    }

}
