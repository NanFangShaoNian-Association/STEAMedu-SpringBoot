package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName advantage
 */
@TableName(value ="advantage")
@Data
public class Advantage implements Serializable {
    /**
     * 课程优势ID-主键
     */
    @TableId(value = "course_advantage_id")
    private Integer courseAdvantageId;

    /**
     * 
     */
    @TableField(value = "course_advantage")
    private String courseAdvantage;

    /**
     * 
     */
    @TableField(value = "course_advantage_introduction")
    private String courseAdvantageIntroduction;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}