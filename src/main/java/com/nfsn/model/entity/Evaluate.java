package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName evaluate
 */
@TableName(value ="evaluate")
@Data
public class Evaluate implements Serializable {
    /**
     * 课程评价id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 用户ID-用户外键
     */
    @TableField(value = "student_id")
    private Integer student_id;

    /**
     * 课程ID-用户外键
     */
    @TableField(value = "course_id")
    private Integer course_id;

    /**
     * 评价
     */
    @TableField(value = "evaluate")
    private String evaluate;

    /**
     * 评价时间
     */
    @TableField(value = "evaluate_time")
    private Date evaluate_time;

    /**
     * 评价级别-0:好评;1:一般;2:差评;(默认为0)
     */
    @TableField(value = "evaluate_level")
    private Integer evaluate_level;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}