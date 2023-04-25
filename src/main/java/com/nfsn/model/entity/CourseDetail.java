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
     * 课程详细介绍ID-主键
     */
    @TableId(value = "course_detail_id", type = IdType.AUTO)
    private Integer courseDetailId;

    /**
     * 课程ID
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 图片-存图片路径
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 显示序号
     */
    @TableField(value = "display_order")
    private Integer displayOrder;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}