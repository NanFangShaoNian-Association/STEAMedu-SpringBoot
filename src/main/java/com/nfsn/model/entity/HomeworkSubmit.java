package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 作业提交表
 * @TableName homework_submit
 */
@TableName(value ="homework_submit")
@Data
public class HomeworkSubmit implements Serializable {
    /**
     * 自增主键
     */
    @TableId(value = "homework_submit_id", type = IdType.AUTO)
    private Integer homeworkSubmitId;

    /**
     * 作业 ID，外键关联 homework 表
     */
    @TableField(value = "homework_id")
    private Integer homeworkId;

    /**
     * 学生 ID，外键关联 student 表
     */
    @TableField(value = "student_id")
    private Integer studentId;

    /**
     * 作业答案，json存储
     */
    @TableField(value = "homework_submit_answer")
    private Object homeworkSubmitAnswer;

    /**
     * 学生得分，默认为 NULL，表示未评分
     */
    @TableField(value = "homework_submit_score")
    private BigDecimal homeworkSubmitScore;

    /**
     * 作业状态：0 - 未完成，1 - 已完成，2 - 保存
     */
    @TableField(value = "homework_submit_status")
    private Integer homeworkSubmitStatus;

    /**
     * 学生提交时间，默认为 NULL，表示未提交
     */
    @TableField(value = "submit_time")
    private Date submitTime;

    /**
     * 作业评价时间，默认为 NULL，表示未评价
     */
    @TableField(value = "review_time")
    private Date reviewTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}