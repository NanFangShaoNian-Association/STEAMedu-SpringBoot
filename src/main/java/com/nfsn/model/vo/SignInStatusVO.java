package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snail
 * @create 2023-04-11  9:36
 */
@Data
public class SignInStatusVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 学生签到的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date signInTime;

    /**
     * 签到状态，0表示未签到，1表示已签到
     */
    private Integer signInStatus;

    /**
     * 事件类型
     */
    private String evenType = "签到";
}
