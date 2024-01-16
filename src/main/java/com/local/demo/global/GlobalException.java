package com.local.demo.global;

import com.local.demo.enums.ResponseCodeEnum;
import lombok.Data;


/**
 * 全局异常
 *
 * @author echo
 * @date 2024/01/12
 */
@Data
public class GlobalException extends RuntimeException{

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    public GlobalException(){
        super();
    }

    /**
     * @description: 接收自定传递的状态码和异常消息
     * @author: echo
     * @date: 2021/5/22
     * @param: code
     * @param: message
     * @return:
     */
    public GlobalException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    /**
     * @description: 接收自定传递的状态码和异常消息
     * @author: echo
     * @date: 2021/5/25
     * @param: msg
     * @return:
     */
    public GlobalException(String msg){
        super(msg);
        this.code = ResponseCodeEnum.UNKNOWN_REASON.getCode();
        this.msg = msg;
    }
    /**
     * @description: 接收自定传递的状态码和异常消息
     * @author: echo
     * @date: 2021/5/25
     * @param: code
     * @return:
     */
    public GlobalException(Integer code){
        super(ResponseCodeEnum.UNKNOWN_REASON.getMsg());
        this.code = code;
        this.msg = ResponseCodeEnum.UNKNOWN_REASON.getMsg();

    }

    /**
     * @description: 接收枚举类型参数
     * @author: echo
     * @date: 2021/5/22
     * @param: responseCodeEnum
     * @return:
     */
    public GlobalException(ResponseCodeEnum responseCodeEnum){
        super(responseCodeEnum.getMsg());
        this.code = responseCodeEnum.getCode();
        this.msg = responseCodeEnum.getMsg();
    }

    public GlobalException(ResponseCodeEnum responseCodeEnum,String definedMsg){
        super(responseCodeEnum.getMsg());
        this.code = responseCodeEnum.getCode();
        this.msg = definedMsg;
    }

    @Override
    public String toString() {
        return "GlobalException{code=" + code +" message=" + this.getMessage() +"}";
    }
}
