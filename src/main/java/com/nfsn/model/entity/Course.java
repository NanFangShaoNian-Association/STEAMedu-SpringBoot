package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 课程ID
     */
    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer course_id;

    /**
     * 课程封面-存图片路径
     */
    @TableField(value = "course_cover")
    private String course_cover;

    /**
     * 开课者ID-用户外键
     */
    @TableField(value = "course_distributor")
    private Integer course_distributor;

    /**
     * 课程名
     */
    @TableField(value = "course_name")
    private String course_name;

    /**
     * 价格
     */
    @TableField(value = "course_price")
    private Integer course_price;

    /**
     * 课程类型-0:精选推荐;1:普通
     */
    @TableField(value = "course_type")
    private Integer course_type;

    /**
     * 上课地点
     */
    @TableField(value = "course_position")
    private String course_position;

    /**
     * 课程开始时间
     */
    @TableField(value = "course_start_time")
    private String course_start_time;

    /**
     * 每节课时长
     */
    @TableField(value = "course_duration")
    private Integer course_duration;

    /**
     * 报名人数
     */
    @TableField(value = "course_enrolment")
    private Integer course_enrolment;

    /**
     * 课时数
     */
    @TableField(value = "course_section_number")
    private Integer course_section_number;

    /**
     * 课程限购数-一门课能报名多少人(比如线下课，可能教室不够)
     */
    @TableField(value = "course_limited_purchase_volume")
    private Integer course_limited_purchase_volume;

    /**
     * 录入时间
     */
    @TableField(value = "entering_time")
    private Date entering_time;

    /**
     * 课程文字简介-只有精选课程才会显示文字简介，其他课程都没有这个
     */
    @TableField(value = "course_text_introduction")
    private String course_text_introduction;

    /**
     * 课程状态码-0:未删除;1:已删除;(默认为0)
     */
    @TableField(value = "course_delete_status")
    private Integer course_delete_status;

    /**
     * 结课状态-0:未结课;1:已结课;(默认为0)
     */
    @TableField(value = "course_final_status")
    private Integer course_final_status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}