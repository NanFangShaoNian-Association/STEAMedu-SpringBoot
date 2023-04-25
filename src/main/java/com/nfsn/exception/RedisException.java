package com.nfsn.exception;

import com.nfsn.constants.ResultCode;
import lombok.Data;

/**
 * @author snail
 * @create 2023-04-21  20:46
 */
@Data
public class RedisException extends RuntimeException{
    private ResultCode resultCode;

    public RedisException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
