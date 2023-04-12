package com.nfsn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-12  9:27
 */
@Data
@ApiModel("创建作业请求传输实体")
public class HomeworkRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @ApiModelProperty("课程ID")
    private Integer courseId;

    /**
     * 作业标题
     */
    @ApiModelProperty("作业标题")
    private String homeworkTitle;

    /**
     * 作业截止时间
     */
    @ApiModelProperty("作业截止时间")
    private Date deadline;

    /**
     * 题目集合
     */
    @ApiModelProperty("题目集合")
    private List<Integer> questionIds;

}
