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
 * @TableName examination
 */
@TableName(value ="examination")
@Data
public class Examination implements Serializable {
    /**
     * 考试ID-主键
     */
    @TableId(value = "examination_id")
    private Integer examinationId;

    /**
     * 课程ID-课程外键
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 考试名
     */
    @TableField(value = "examination_name")
    private String examinationName;

    /**
     * 命题人ID-用户外键
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;

    /**
     * 开始时间
     */
    @TableField(value = "examination_start_time")
    private Date examinationStartTime;

    /**
     * 截止时间
     */
    @TableField(value = "examin_finall_time")
    private Date examinFinallTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}