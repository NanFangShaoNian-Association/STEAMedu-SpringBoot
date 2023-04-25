package com.nfsn.model.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 作业题目联系表
 * @author 温格
 * @TableName homework_question
 */
@Data
@TableName(value = "homework_question")
public class HomeworkQuestion implements Serializable {
    /**
     * 关联的作业ID
     */
    @TableId(value = "homework_id")
    private Integer homeworkId;

    /**
     * 关联的题目ID
     */
    @TableField(value = "question_id")
    private Integer questionId;

    private static final long serialVersionUID = 1L;
}