package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName course_student
 */
@TableName(value ="course_student")
@Data
public class CourseStudent implements Serializable {
    /**
     * 课程学生ID-主键
     */
    @TableId(value = "course_student_id", type = IdType.AUTO)
    private Integer courseStudentId;

    /**
     * 学生信息ID-学生信息外键
     */
    @TableField(value = "student_message_id")
    private Integer studentMessageId;

    /**
     * 课程ID-课程外键
     */
    @TableField(value = "course_id")
    private Integer courseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}