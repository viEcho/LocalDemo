package com.local.demo.global;

import com.local.demo.enums.ResponseCodeEnum;
import lombok.Data;

/**
 * @description: 统一返回封装
 * @author: echo
 * @date: 2021/5/22
 */
@Data
public class ResponseVO {
    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private Object data;

    public ResponseVO() {
        this.success = ResponseCodeEnum.SUCCESS.getSuccess();
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.msg= ResponseCodeEnum.SUCCESS.getMsg();
    }


    /**
     * 成功
     *
     * @return {@link ResponseVO}
     */
    public static ResponseVO success(){
        return new ResponseVO()
        .success(ResponseCodeEnum.SUCCESS.getSuccess())
        .code(ResponseCodeEnum.SUCCESS.getCode())
        .msg(ResponseCodeEnum.SUCCESS.getMsg());
    }


    /**
     * 失败
     *
     * @return {@link ResponseVO}
     */
    public static ResponseVO fail(){
        return new ResponseVO()
        .success(ResponseCodeEnum.UNKNOWN_REASON.getSuccess())
        .code(ResponseCodeEnum.UNKNOWN_REASON.getCode())
        .msg(ResponseCodeEnum.UNKNOWN_REASON.getMsg());
    }


    /**
     * 失败
     *
     * @param responseCodeEnum 响应代码枚举
     * @return {@link ResponseVO}
     */
    public static ResponseVO fail(ResponseCodeEnum responseCodeEnum){
        ResponseVO r = new ResponseVO();
        r.setSuccess(responseCodeEnum.getSuccess());
        r.setCode(responseCodeEnum.getCode());
        r.setMsg(responseCodeEnum.getMsg());
        return r;
    }
    public ResponseVO success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public ResponseVO msg(String msg){
        this.setMsg(msg);
        return this;
    }
    public ResponseVO code(Integer code){
        this.setCode(code);
        return this;
    }
    public ResponseVO data(Object obj){
        this.setData(obj);
        return this;
    }

}
