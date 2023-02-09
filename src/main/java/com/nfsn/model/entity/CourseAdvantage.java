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
     * 课程优势ID-主键;课程外键
     */
    @TableId(value = "course_advantage_id")
    private Integer courseAdvantageId;

    /**
     * 课程ID-主键;课程外键
     */
    @TableField(value = "course_id")
    private Integer courseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}