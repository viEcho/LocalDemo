package com.local.demo.global;

import com.local.demo.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * 全局异常处理程序
 *
 * @author echo
 * @date 2024/01/12
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 失败
     *
     * @param e e
     * @return {@link ResponseVO}
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseVO fail(Exception e){
        e.printStackTrace();
        return ResponseVO.fail();
    }


    /**
     * 失败
     *
     * @param e e
     * @return {@link ResponseVO}
     */
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public ResponseVO fail(GlobalException e){
        log.error(getStackMessage(e));//打印异常堆栈信息
        return ResponseVO.fail().msg(e.getMessage()).code(e.getCode());
    }

    /**
     * 获取堆栈消息
     *
     * @param e e
     * @return {@link String}
     */
    public static String getStackMessage(Exception e){
        // 流
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            // 将出错的信息输出到 PrintWriter！
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (sw!=null){
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw!=null){
                pw.close();
            }
        }
        return sw.toString();
    }

    /**
     * 失败
     *
     * @param e e
     * @return {@link ResponseVO}
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public ResponseVO fail(BadSqlGrammarException e){
        e.printStackTrace();
        return ResponseVO.fail(ResponseCodeEnum.BAD_SQL_GRAMMAR);
    }
}
