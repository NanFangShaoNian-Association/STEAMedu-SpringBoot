package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 签到表
 * @author 温格
 * @TableName sign_in
 */
@TableName(value ="sign_in")
@Data
public class SignIn implements Serializable {
    /**
     * 签到的唯一标识符
     */
    @TableId(value = "sign_in_id", type = IdType.AUTO)
    private Integer signInId;

    /**
     * 签到所属的课程的唯一标识符
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 发起签到的用户的唯一标识符
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 签到的截止时间
     */
    @TableField(value = "deadline")
    private Date deadline;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}