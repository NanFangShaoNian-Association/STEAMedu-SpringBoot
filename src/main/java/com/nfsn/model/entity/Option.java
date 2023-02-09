package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName option
 */
@TableName(value ="option")
@Data
public class Option implements Serializable {
    /**
     * 选项ID-主键
     */
    @TableId(value = "option_id")
    private Integer optionId;

    /**
     * 题目ID-题目外键
     */
    @TableField(value = "subject_id")
    private Integer subjectId;

    /**
     * 选项值
     */
    @TableField(value = "option_value")
    private String optionValue;

    /**
     * 选项序号
     */
    @TableField(value = "option")
    private String option;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}