package com.nfsn.exception;

import com.nfsn.constants.ResultCode;
import lombok.Data;

/**
 * @ClassName: UserLoginException
 * @Author: 团子tz
 * @CreateTime: 2022/11/14 22:26
 * @Description: 用户登录异常类
 */
@Data
public class UserLoginException extends RuntimeException{
    private ResultCode resultCode;


    public UserLoginException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
