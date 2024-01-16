package com.local.demo.enums;

import lombok.Getter;


/**
 * 响应代码枚举
 *
 * @author echo
 * @date 2024/01/12
 */
@Getter
public enum ResponseCodeEnum {

    SUCCESS(true, 1000,"Success"),
    UNKNOWN_REASON(false, 1001, "Unknown error"),
    BAD_SQL_GRAMMAR(false, 1002, "Sql syntax error"),
    ANNOTATION_CAN_NOT_FIND(false,1003,"Can not find the annotation"),
    STARTEGY_CAN_NOT_FIND(false,1004,"Can not find the handle strategy");

    // 是否响应成功
    private final Boolean success;
    // 响应的状态码
    private final Integer code;
    // 响应的消息
    private final String msg;

    ResponseCodeEnum(Boolean success, Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }
}
