package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: AdvantageVO
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-10 22:10
 * @Description: 推荐课程传输实体
 */
@Data
@ApiModel("推荐课程传输实体")
public class RecommendedCourseVO implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID-主键
     */
    @ApiModelProperty("课程ID-主键")
    private Integer courseId;

    /**
     * 课程名
     */
    @ApiModelProperty("课程名")
    private String courseName;

    /**
     * 课程开始时间
     */
    @ApiModelProperty("课程开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm" ,timezone = "GMT+8")
    private String courseStartTime;

    /**
     * 上课地点
     */
    @ApiModelProperty("上课地点")
    private String coursePosition;

    /**
     * 教师列表
     */
    @ApiModelProperty("教师信息列表")
    private List<TeacherInfoVO> teacherInfoVOList;

    /**
     * 距离
     */
    @ApiModelProperty("距离")
    private String distance;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private Integer coursePrice;
}
