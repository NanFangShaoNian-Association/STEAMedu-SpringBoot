package com.nfsn.model.entity;

import java.io.Serializable;

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
    private Integer homeworkId;

    /**
     * 关联的题目ID
     */
    private Integer questionId;

    private static final long serialVersionUID = 1L;
}