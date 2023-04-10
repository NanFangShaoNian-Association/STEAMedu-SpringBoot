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
 * @author 温格
 * @TableName exam
 */
@TableName(value ="exam")
@Data
public class Exam implements Serializable {
    /**
     * 考试id
     */
    @TableId(value = "exam_id", type = IdType.AUTO)
    private Integer examId;

    /**
     * 考试名称
     */
    @TableField(value = "exam_name")
    private String examName;

    /**
     * 考试开始时间
     */
    @TableField(value = "exam_start_time")
    private Date examStartTime;

    /**
     * 考试结束时间
     */
    @TableField(value = "exam_end_time")
    private Date examEndTime;

    /**
     * 考试时长（单位为分钟）
     */
    @TableField(value = "exam_duration")
    private Integer examDuration;

    /**
     * 考试创建时间
     */
    @TableField(value = "exam_create_time")
    private Date examCreateTime;

    /**
     * 教师ID
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;

    /**
     * 课程ID
     */
    @TableField(value = "course_id")
    private Integer courseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}