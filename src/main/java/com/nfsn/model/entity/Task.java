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
 * @TableName task
 */
@TableName(value ="task")
@Data
public class Task implements Serializable {
    /**
     * 作业ID-主键
     */
    @TableId(value = "task_id")
    private Integer taskId;

    /**
     * 课程ID-课程外键
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 作业名
     */
    @TableField(value = "task_name")
    private String taskName;

    /**
     * 命题人ID-用户外键
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 截止时间
     */
    @TableField(value = "finall_time")
    private Date finallTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}