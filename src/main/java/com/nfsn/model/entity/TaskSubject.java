package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName task_subject
 */
@TableName(value ="task_subject")
@Data
public class TaskSubject implements Serializable {
    /**
     * 作业题目ID-主键;作业外键
     */
    @TableId(value = "task_subject_id")
    private Integer taskSubjectId;

    /**
     * 题目ID-主键;题目外键
     */
    @TableField(value = "subject_id")
    private Integer subjectId;

    /**
     * 题目分数
     */
    @TableField(value = "subject_grade")
    private Integer subjectGrade;

    /**
     * 作业题目序号
     */
    @TableField(value = "task_subject_number")
    private Integer taskSubjectNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}