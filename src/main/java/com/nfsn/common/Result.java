package com.nfsn.common;

import com.nfsn.constants.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Result<T> implements Serializable {
//    private boolean flag;
//    private Object data;
//    private String msg;

    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode){
        this.setCode(resultCode.getCode());
        this.setMessage(resultCode.getMessage());
    }

    //返回成功
    public static <T> Result<T> success(){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    //返回成功
    public static <T> Result<T> success(T data){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    //返回失败
    public static <T> Result<T> failure(ResultCode resultCode){
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    //返回失败
    public static <T> Result<T> failure(ResultCode resultCode,T data){
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
}