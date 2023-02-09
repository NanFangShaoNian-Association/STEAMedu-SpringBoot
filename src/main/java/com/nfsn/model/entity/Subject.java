package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName subject
 */
@TableName(value ="subject")
@Data
public class Subject implements Serializable {
    /**
     * 题目ID-主键
     */
    @TableId(value = "subject_id")
    private Integer subjectId;

    /**
     * 题目
     */
    @TableField(value = "subject_name")
    private String subjectName;

    /**
     * 答案
     */
    @TableField(value = "answer")
    private String answer;

    /**
     * 所属题集-0:未分类;1:Java;2:Python;3:C语言;...(默认为0)
     */
    @TableField(value = "subject_type")
    private Integer subjectType;

    /**
     * 题目解析
     */
    @TableField(value = "subject_analysis")
    private String subjectAnalysis;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}