package com.local.demo.enums;

import lombok.Getter;

/**
 * 数据库类型枚举
 *
 * @author echo
 * @date 2024/01/12
 */
@Getter
public enum DbTypeEnums {
    MASTER(1,"主库"),
    SLAVE(2,"从库"),
    CACHE(3,"缓存"),
    CACHE_AND_MASTER(4,"缓存和主库"),
    CACHE_AND_SLAVE(5,"缓存和从库");

    private final int code;
    private final String desc;

    DbTypeEnums(int code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
