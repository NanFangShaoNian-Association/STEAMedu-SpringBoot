package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: CourseAdvantageVO
 * @Description: 课程优势VO
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-11 16:19
 * @Version: 1.0
 **/
@Data
@ApiModel(value="课程优势")
public class CourseAdvantageVO {

    @ApiModelProperty(value="课程优势")
    private String courseAdvantage;

    @ApiModelProperty(value="课程优势介绍")
    private String courseAdvantageIntroduction;
}
