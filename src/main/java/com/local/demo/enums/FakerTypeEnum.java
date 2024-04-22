package com.local.demo.enums;

import lombok.Getter;

@Getter
public enum FakerTypeEnum {

    IGNORE("","","忽略此字段"),
    ;

    private final String fakerVal;

    private final String fakerType;

    private final String fakerTypeDesc;

    FakerTypeEnum(String fakerVal,String fakerType,String fakerDesc){
        this.fakerVal = fakerVal;
        this.fakerType = fakerType;
        this.fakerTypeDesc = fakerDesc;
    }

}
