package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 题目选项表
 * @author 温格
 * @TableName question_option
 */
@TableName(value ="question_option")
@Data
public class QuestionOption implements Serializable {
    /**
     * 题目选项ID
     */
    @TableId(value = "question_option_id", type = IdType.AUTO)
    private Integer questionOptionId;

    /**
     * 关联题目表的题目ID
     */
    @TableField(value = "question_id")
    private Integer questionId;

    /**
     * 选项序号，可以是A、B、C、D、E等
     */
    @TableField(value = "option_index")
    private String optionIndex;

    /**
     * 选项内容
     */
    @TableField(value = "option_text")
    private String optionText;

    /**
     * 标识选项是否是正确答案，0 -错误答案 1-正确答案
     */
    @TableField(value = "is_correct")
    private Integer isCorrect;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}