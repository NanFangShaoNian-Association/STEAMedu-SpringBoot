package com.nfsn.exception;

import com.nfsn.constants.ResultCode;
import lombok.Data;

/**
 * @author snail
 * @create 2023-03-07  0:30
 */
@Data
public class UserException extends RuntimeException {
    private ResultCode resultCode;


    public UserException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
