package com.nfsn.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snail
 * @create 2023-04-14  16:46
 */
@Data
@ApiModel("考试事件响应实体")
public class ExamToEvenVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 考试ID
     */
    @ApiModelProperty("考试ID")
    private Integer examId;

    /**
     * 考试名称
     */
    @ApiModelProperty("考试名称")
    private String examName;

    /**
     * 课程名称
     */
    @ApiModelProperty("课程名称")
    private String courseName;

    /**
     * 考试开始时间
     */
    @ApiModelProperty("考试开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date examStartTime;

    /**
     * 考试结束时间
     */
    @ApiModelProperty("考试结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date examEndTime;

    /**
     * 事件类型
     */
    @ApiModelProperty("事件类型")
    private String evenType = "考试";
}
