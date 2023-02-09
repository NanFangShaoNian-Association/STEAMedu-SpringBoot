package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName submit_task
 */
@TableName(value ="submit_task")
@Data
public class SubmitTask implements Serializable {
    /**
     * 作业ID-主键;考试外键
     */
    @TableId(value = "submit_task_id")
    private Integer submitTaskId;

    /**
     * 学生信息ID-用户外键
     */
    @TableField(value = "student_id")
    private Integer studentId;

    /**
     * 学生作业答案
     */
    @TableField(value = "task_answer")
    private String taskAnswer;

    /**
     * 学生分数-需要 分割符
     */
    @TableField(value = "task_student_grade")
    private Integer taskStudentGrade;

    /**
     * 作业提交状态
     */
    @TableField(value = "submit_task_status")
    private Integer submitTaskStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}