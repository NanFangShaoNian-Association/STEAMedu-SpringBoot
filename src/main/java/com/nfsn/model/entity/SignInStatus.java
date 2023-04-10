package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 签到状态表
 * @author 温格
 * @TableName sign_in_status
 */
@TableName(value ="sign_in_status")
@Data
public class SignInStatus implements Serializable {
    /**
     * 签到状态的唯一标识符
     */
    @TableId(value = "sign_in_status_id", type = IdType.AUTO)
    private Integer signInStatusId;

    /**
     * 签到的唯一标识符
     */
    @TableField(value = "sign_in_id")
    private Integer signInId;

    /**
     * 学生的唯一标识符
     */
    @TableField(value = "student_message_id")
    private Integer studentMessageId;

    /**
     * 学生签到的时间
     */
    @TableField(value = "sign_in_time")
    private Date signInTime;

    /**
     * 签到状态，0表示未签到，1表示已签到
     */
    @TableField(value = "sign_in_status")
    private Integer signInStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}