package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName examination_subject
 */
@TableName(value ="examination_subject")
@Data
public class ExaminationSubject implements Serializable {
    /**
     * 考试题目ID-主键
     */
    @TableId(value = "examination_subject_id")
    private Integer examination_subject_id;

    /**
     * 题目ID-题目外键
     */
    @TableField(value = "subject_id")
    private Integer subject_id;

    /**
     * 考试分数
     */
    @TableField(value = "examination_grade")
    private Integer examination_grade;

    /**
     * 考试题目序号
     */
    @TableField(value = "task_subject_id")
    private Integer task_subject_id;

    /**
     * 考试ID-考试外键
     */
    @TableField(value = "task_id")
    private Integer task_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}