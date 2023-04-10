package com.nfsn.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 作业表
 * @author 温格
 * @TableName homework
 */
@Data
@TableName(value ="homework")
public class Homework implements Serializable {
    /**
     * 作业ID
     */
    private Integer homeworkId;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 作业标题
     */
    private String homeworkTitle;

    /**
     * 作业截止时间
     */
    private Date deadline;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 作业状态：0 - 未发布，1 - 已发布，2 - 已截止
     */
    private Integer status;

    /**
     * 创建者
     */
    private Integer creatorId;

    private static final long serialVersionUID = 1L;


}