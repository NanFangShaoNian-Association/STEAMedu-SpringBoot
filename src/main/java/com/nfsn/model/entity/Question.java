package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 题目表
 * @author 温格
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {
    /**
     * 题目ID，主键自增
     */
    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;

    /**
     * 题目
     */
    @TableField(value = "question_text")
    private String questionText;

    /**
     * 题目类型（1:单选，2:多选）
     */
    @TableField(value = "question_type")
    private Integer questionType;

    /**
     * 题目类别（java、python、数学、语文等）
     */
    @TableField(value = "question_category")
    private String questionCategory;

    /**
     * 图片题目时的图片地址，文字题目为空
     */
    @TableField(value = "question_image_url")
    private String questionImageUrl;

    /**
     * 题目解析
     */
    @TableField(value = "question_analysis")
    private String questionAnalysis;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}