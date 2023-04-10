package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 课程ID-主键
     */
    @TableId(value = "course_id")
    private Integer courseId;

    /**
     * 课程封面-存图片路径
     */
    @TableField(value = "course_cover")
    private String courseCover;

    /**
     * 开课机构ID-外键
     */
    @TableField(value = "course_distributor")
    private Integer courseDistributor;

    /**
     * 课程名
     */
    @TableField(value = "course_name")
    private String courseName;

    /**
     * 价格
     */
    @TableField(value = "course_price")
    private Integer coursePrice;

    /**
     * 课程类型-0:精选推荐;1:普通
     */
    @TableField(value = "course_type")
    private Integer courseType;

    /**
     * 上课地点
     */
    @TableField(value = "course_position")
    private String coursePosition;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private Double latitude;

    /**
     * 课程开始时间
     */
    @TableField(value = "course_start_time")
    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    private String courseStartTime;

    /**
     * 每节课时长
     */
    @TableField(value = "course_duration")
    private Integer courseDuration;

    /**
     * 报名人数
     */
    @TableField(value = "course_enrolment")
    private Integer courseEnrolment;

    /**
     * 课时数
     */
    @TableField(value = "course_section_number")
    private Integer courseSectionNumber;

    /**
     * 课程限报人数-一门课能报名多少人(比如线下课，可能教室不够)
     */
    @TableField(value = "course_limited_purchase_volume")
    private Integer courseLimitedPurchaseVolume;

    /**
     * 课程信息录入时间
     */
    @TableField(value = "entering_time")
    private Date enteringTime;

    /**
     * 课程文字简介-只有精选课程才会显示文字简介，其他课程都没有这个
     */
    @TableField(value = "course_text_introduction")
    private String courseTextIntroduction;

    /**
     * 课程状态码-0:未删除;1:已删除;(默认为0)
     */
    @TableField(value = "course_delete_status")
    private Integer courseDeleteStatus;

    /**
     * 结课状态-0:未结课;1:已结课;(默认为0)
     */
    @TableField(value = "course_final_status")
    private Integer courseFinalStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}