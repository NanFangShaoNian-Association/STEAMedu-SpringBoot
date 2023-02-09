package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sign
 */
@TableName(value ="sign")
@Data
public class Sign implements Serializable {
    /**
     * 签到信息ID-主键
     */
    @TableId(value = "sign_id")
    private Integer signId;

    /**
     * 签到发起人ID-用户外键
     */
    @TableField(value = "sign_sender")
    private Integer signSender;

    /**
     * 课时ID-课程外键
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 截止时间
     */
    @TableField(value = "sign_finall_time")
    private Date signFinallTime;

    /**
     * 签到信息-定位签到存储精准定位±范围，二维码签到存储对应校验信息，手势签到和验证码签到存储对应字符即可
     */
    @TableField(value = "sign_infomation")
    private String signInfomation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}