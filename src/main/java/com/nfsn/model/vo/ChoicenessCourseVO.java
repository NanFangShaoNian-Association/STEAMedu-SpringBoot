package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: AdvantageVO
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-10 22:10
 * @Description: 精选课程传输实体
 */
@Data
@ApiModel("精选课程传输实体")
public class ChoicenessCourseVO implements Serializable{
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
     * 课程文字简介-只有精选课程才会显示文字简介，其他课程都没有这个
     */
    @ApiModelProperty("课程文字简介-只有精选课程才会显示文字简介，其他课程都没有这个")
    private String courseTextIntroduction;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private Integer coursePrice;
}

