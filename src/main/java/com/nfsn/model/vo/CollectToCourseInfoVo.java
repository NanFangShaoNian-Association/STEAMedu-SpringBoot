package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author snail
 * @create 2023-03-22  16:07
 */
@Data
@ApiModel("收藏信息响应实体")
public class CollectToCourseInfoVo {

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
     * 课程封面
     */
    @ApiModelProperty("课程封面")
    private String courseCover;

    /**
     * 课程开始时间
     */
    @ApiModelProperty("课程开始时间")
    private String courseStartTime;

    /**
     * 课时
     */
    @ApiModelProperty("课时")
    private Integer courseSectionNumber;

    /**
     * 上课地点
     */
    @ApiModelProperty("上课地点")
    private String coursePosition;

    /**
     * 距离
     */
    @ApiModelProperty("距离")
    private String distance = "2km";

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private Integer coursePrice;

    /**
     * 老师信息
     */
    @ApiModelProperty(value = "老师信息")
    private List<TeacherInfoVO> teachers;

}
