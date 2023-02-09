package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName leave
 */
@TableName(value ="leave")
@Data
public class Leave implements Serializable {
    /**
     * 学生信息ID-主键;用户外键
     */
    @TableId(value = "student_id")
    private Integer studentId;

    /**
     * 课时ID-主键;课程外键
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 请假理由
     */
    @TableField(value = "leave_reason")
    private String leaveReason;

    /**
     * 批准状态-0:未批准;1:已批准;(默认为未批准)
     */
    @TableField(value = "leave_approval_status")
    private Integer leaveApprovalStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}