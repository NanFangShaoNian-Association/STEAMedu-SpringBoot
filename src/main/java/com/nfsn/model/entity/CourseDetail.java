package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName course_detail
 */
@TableName(value ="course_detail")
@Data
public class CourseDetail implements Serializable {
    /**
     * 课程详细介绍id
     */
    @TableId(value = "course_detail_id")
    private Integer course_detail_id;

    /**
     * 
     */
    @TableField(value = "course_id")
    private Integer course_id;

    /**
     * 
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 存图片路径
     */
    @TableField(value = "display_order")
    private Integer display_order;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}