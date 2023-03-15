package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "待支付课程信息")
public class PendingPaymentCourseVO {

    @ApiModelProperty(value = "课程ID")
    private Integer courseId;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程开始时间")
    private String courseStartTime;

    @ApiModelProperty(value = "课时")
    private Integer courseSectionNumber;

    @ApiModelProperty(value = "上课地点")
    private String coursePosition;

    @ApiModelProperty(value = "价格")
    private Integer coursePrice;

    @ApiModelProperty(value = "老师信息")
    private List<TeacherInfoVO> teachers;
}