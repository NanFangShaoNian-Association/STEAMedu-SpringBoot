package com.nfsn.exception;

import com.nfsn.constants.ResultCode;
import lombok.Data;

/**
 * @author snail
 * @create 2023-03-22  10:22
 */
@Data
public class BaseInfoException extends RuntimeException{
    private ResultCode resultCode;


    public BaseInfoException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

}
