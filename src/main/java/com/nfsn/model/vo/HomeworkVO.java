package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-12  12:15
 */
@Data
@ApiModel("作业内容响应实体")
public class HomeworkVO implements Serializable {
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
     * 题目列表
     */
    @ApiModelProperty("题目和选项列表列表")
    private List<HomeworkQuestionVo> homeworkQuestionVoList;

}
