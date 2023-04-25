package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snail
 * @create 2023-04-13  14:54
 */
@Data
@ApiModel("作业事件响应实体")
public class HomeworkToEvenVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 作业ID
     */
    @ApiModelProperty("作业ID")
    private Integer homeworkId;

    /**
     * 作业标题
     */
    @ApiModelProperty("作业标题")
    private String homeworkTitle;

    /**
     * 课程名称
     */
    @ApiModelProperty("课程名称")
    private String courseName;

    /**
     * 作业截止时间
     */
    @ApiModelProperty("作业截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date deadline;

    /**
     * 事件类型
     */
    @ApiModelProperty("事件类型")
    private String evenType = "作业";

}
