package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-14  16:18
 */
@Data
@ApiModel("考试内容响应实体")
public class ExamVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @ApiModelProperty("课程ID")
    private Integer courseId;

    /**
     * 考试名称
     */
    @ApiModelProperty("考试名称")
    private String examName;

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
     * 考试时长（单位为分钟）
     */
    @ApiModelProperty("考试时长（单位为分钟）")
    private Integer examDuration;

    /**
     * 题目列表
     */
    @ApiModelProperty("题目和选项列表列表")
    private List<QuestionVo> QuestionVoList;
}
