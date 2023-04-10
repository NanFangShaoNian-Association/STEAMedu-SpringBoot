package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: CourseVO
 * @Description: 课程信息VO
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-11 15:58
 * @Version: 1.0
 **/
@Data
@ApiModel(value = "课程信息VO")
public class CourseVO {
    /**
     * 课程封面
     */
    @ApiModelProperty(value = "课程封面")
    private String courseCover;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private Integer coursePrice;

    /**
     * 课程名
     */
    @ApiModelProperty(value = "课程名")
    private String courseName;

    /**
     * 课程开始时间
     */
    @ApiModelProperty(value = "课程开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date courseStartTime;

    /**
     * 报名人数
     */
    @ApiModelProperty(value = "报名人数")
    private Integer courseEnrolment;

    /**
     * 课时
     */
    @ApiModelProperty(value = "课时")
    private Integer courseSectionNumber;

    /**
     * 上课地点
     */
    @ApiModelProperty(value = "上课地点")
    private String coursePosition;

    /**
     * 开课机构ID-外键
     */
    @ApiModelProperty(value = "开课机构ID-外键")
    private Integer courseDistributor;
}
