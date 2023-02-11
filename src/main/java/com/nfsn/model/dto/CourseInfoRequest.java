package com.nfsn.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.nfsn.model.entity.Advantage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: CourseInfoRequest
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-10 22:10
 * @Description: 课程详细信息传输实体
 */
@Data
@ApiModel("课程详细信息传输实体")
public class CourseInfoRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 课程封面-存图片路径
     */
    @ApiModelProperty("课程封面-存图片路径")
    private String courseCover;

    /**
     * 价格
     */
    @TableField(value = "course_price")
    private Integer coursePrice;

    /**
     * 课程名
     */
    @TableField(value = "course_name")
    private String courseName;

    /**
     * 课程开始时间
     */
    @TableField(value = "course_start_time")
    private String courseStartTime;

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
     * 课程优势
     */
    @ApiModelProperty("课程优势")
    private List<AdvantageRequest> courseAdvantage;

    /**
     * 上课地点
     */
    @TableField(value = "course_position")
    private String coursePosition;

    /**
     * 教师
     */
    @ApiModelProperty("教师")
    private List<TeacherRequest> teacher;

    /**
     * 课程详细介绍
     */
    @ApiModelProperty("课程详细介绍")
    private List<CourseDetailRequest> courseDetail;

    /**
     * 发布课程的机构
     */
    @ApiModelProperty("发布课程的机构")
    private InstitutionRequest institution;

    /**
     * 课程评价
     */
    @ApiModelProperty("课程评价")
    private List<CourseRatingRequest> courseRating;

}
