package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName course_advantage
 */
@TableName(value ="course_advantage")
@Data
public class CourseAdvantage implements Serializable {
    /**
     * 课程优势ID-主键
     */
    @TableId(value = "course_advantage_id", type = IdType.AUTO)
    private Integer course_advantage_id;

    /**
     * 优势
     */
    @TableField(value = "course_advantage")
    private String course_advantage;

    /**
     * 优势简介
     */
    @TableField(value = "course_advantage_introduction")
    private String course_advantage_introduction;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}