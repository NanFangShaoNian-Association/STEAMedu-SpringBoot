package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author snail
 * @create 2023-04-02  17:02
 */
@Data
@ApiModel(value = "我的课程列表VO")
public class CourseMyListVO {

    /**
     * 课程ID
     */
    @ApiModelProperty("课程ID")
    private Integer courseId;

    /**
     * 课程名
     */
    @ApiModelProperty("课程名")
    private String courseName;

    /**
     * 上课地点
     */
    @ApiModelProperty("上课地点")
    private String coursePosition;

    /**
     * 课程开始时间
     */
    @ApiModelProperty("课程开始时间")
    private String courseStartTime;

    /**
     * 老师信息
     */
    @ApiModelProperty(value = "老师信息")
    private List<TeacherInfoVO> teachers;

    /**
     * 距离
     */
    @ApiModelProperty("距离")
    private String distance = "2km";

}
