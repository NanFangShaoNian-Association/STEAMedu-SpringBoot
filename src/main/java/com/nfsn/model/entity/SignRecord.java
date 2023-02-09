package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sign_record
 */
@TableName(value ="sign_record")
@Data
public class SignRecord implements Serializable {
    /**
     * 签到记录ID-主键
     */
    @TableId(value = "sign_record_id")
    private Integer signRecordId;

    /**
     * 签到信息ID-签到信息外键
     */
    @TableField(value = "sign_id")
    private Integer signId;

    /**
     * 学生信息ID-用户外键
     */
    @TableField(value = "student_id")
    private Integer studentId;

    /**
     * 签到状态-0:未签到;1：已签到（默认未签到）
     */
    @TableField(value = "sign_status")
    private Integer signStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}