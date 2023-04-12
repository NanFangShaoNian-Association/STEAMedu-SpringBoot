package com.nfsn.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "homework_id", type = IdType.AUTO)
    private Integer homeworkId;

    /**
     * 课程ID
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 作业标题
     */
    @TableField(value = "homework_title")
    private String homeworkTitle;

    /**
     * 作业截止时间
     */
    @TableField(value = "deadline")
    private Date deadline;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 作业状态：0 - 未发布，1 - 已发布，2 - 已截止
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建者
     */
    @TableField(value = "creator_id")
    private Integer creatorId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}