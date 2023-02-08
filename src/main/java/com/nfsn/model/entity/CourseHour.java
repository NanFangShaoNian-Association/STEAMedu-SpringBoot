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
 * @TableName course_hour
 */
@TableName(value ="course_hour")
@Data
public class CourseHour implements Serializable {
    /**
     * 课时ID
     */
    @TableId(value = "course_hour_id", type = IdType.AUTO)
    private Integer course_hour_id;

    /**
     * 课程ID-课程外键
     */
    @TableField(value = "course_id")
    private Integer course_id;

    /**
     * 上课时间-开始时间
     */
    @TableField(value = "course_start_time")
    private Date course_start_time;

    /**
     * 课程名
     */
    @TableField(value = "course_name")
    private String course_name;

    /**
     * 教室号
     */
    @TableField(value = "course_room")
    private String course_room;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}