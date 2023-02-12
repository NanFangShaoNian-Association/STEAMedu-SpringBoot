package com.nfsn.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.nfsn.model.vo.*;
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
    @ApiModelProperty("价格")
    private Integer coursePrice;

    /**
     * 课程名
     */
    @ApiModelProperty("课程名")
    private String courseName;

    /**
     * 课程开始时间
     */
    @ApiModelProperty("课程开始时间")
    private String courseStartTime;

    /**
     * 报名人数
     */
    @ApiModelProperty("报名人数")
    private Integer courseEnrolment;

    /**
     * 课时数
     */
    @ApiModelProperty("课时数")
    private Integer courseSectionNumber;

    /**
     * 课程优势
     */
    @ApiModelProperty("课程优势")
    private List<AdvantageVO> courseAdvantage;

    /**
     * 上课地点
     */
    @ApiModelProperty("上课地点")
    private String coursePosition;

    /**
     * 教师
     */
    @ApiModelProperty("教师")
    private List<TeacherInfoVO> teacher;

    /**
     * 课程详细介绍
     */
    @ApiModelProperty("课程详细介绍")
    private List<CourseDetailVO> courseDetail;

    /**
     * 发布课程的机构
     */
    @ApiModelProperty("发布课程的机构")
    private CourseInstitutionInfoVO institution;

    /**
     * 课程评价
     */
    @ApiModelProperty("课程评价")
    private List<CourseRatingVO> courseRating;

}
