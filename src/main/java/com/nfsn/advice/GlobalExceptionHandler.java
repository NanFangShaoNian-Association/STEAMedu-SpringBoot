package com.nfsn.advice;

import com.nfsn.common.Result;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.UserException;
import com.nfsn.exception.UserLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author snail
 * @create 2023-02-23  21:56
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // TODO: 2023/2/23  补充异常（已完成大部分）

    //用户登录异常拦截
    @ExceptionHandler(UserLoginException.class)
    public Result userLoginExceptionHandler(HttpServletRequest req, UserLoginException e) {
        log.error("出现UserLoginException异常：", e);
        return Result.failure(e.getResultCode(), null);
    }

    //用户异常拦截
    @ExceptionHandler(UserException.class)
    public Result userExceptionHandler(HttpServletRequest req, UserException e) {
        log.error("出现UserException异常：", e);
        return Result.failure(e.getResultCode(), null);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Result internalExceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("出现NullPointerException异常：", e);
        return Result.failure(ResultCode.PARAM_IS_BLANK, e.getMessage());
    }

    //参数转换异常拦截，-1为系统异常
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result paramExceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
        log.error("出现HttpMessageNotReadableException异常：", e);
        return new Result(-1, e.getCause().toString(), null);
    }

    //其他异常拦截
    @ExceptionHandler(Exception.class)
    public Result internalExceptionHandler(HttpServletRequest request, Exception e) {
        log.error("出现Exception异常：", e);
        return new Result(ResultCode.INTERNAL_ERROR.getCode(), e.getCause().toString(), null);
    }
}
