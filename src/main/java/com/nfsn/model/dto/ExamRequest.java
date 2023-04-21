package com.nfsn.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-14  15:39
 */
@Data
@ApiModel("考试请求传输实体")
public class ExamRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 考试名称
     */
    @ApiModelProperty("考试名称")
    private String examName;

    /**
     * 考试开始时间
     */
    @ApiModelProperty("考试开始时间，可为空")
    private Date examStartTime;

    /**
     * 考试结束时间
     */
    @ApiModelProperty("考试结束时间，可为空")
    private Date examEndTime;

    /**
     * 考试时长（单位为分钟）
     */
    @ApiModelProperty("考试时长（单位为分钟），必须要有")
    private Integer examDuration;

    /**
     * 课程ID
     */
    @ApiModelProperty("课程ID")
    private Integer courseId;

    /**
     * 题目集合
     */
    @ApiModelProperty("题目集合")
    private List<Integer> questionIds;
}
