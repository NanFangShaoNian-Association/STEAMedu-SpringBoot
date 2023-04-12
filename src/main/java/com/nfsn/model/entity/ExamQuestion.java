package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @author 温格
 * @TableName exam_question
 */
@TableName(value ="exam_question")
@Data
public class ExamQuestion implements Serializable {
    /**
     * 关联的试卷ID
     */
    @TableField(value = "exam_id")
    private Integer examId;

    /**
     * 关联的题目ID
     */
    @TableField(value = "question_id")
    private Integer questionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}