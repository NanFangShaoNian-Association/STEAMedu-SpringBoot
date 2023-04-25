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
     * 课时ID-主键
     */
    @TableId(value = "course_hour_id", type = IdType.AUTO)
    private Integer courseHourId;

    /**
     * 课程ID-课程外键
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 上课时间-开始时间
     */
    @TableField(value = "course_start_time")
    private Date courseStartTime;

    /**
     * 课时名-其实也是课时的内容
     */
    @TableField(value = "course_name")
    private String courseName;

    /**
     * 上课地点-考虑机构可能存在多个校区，学生要求调换校区上课的情况，默认是课程的上课地点
     */
    @TableField(value = "course_room")
    private String courseRoom;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}