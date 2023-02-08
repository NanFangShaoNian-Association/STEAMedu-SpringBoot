package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName course_teacher
 */
@TableName(value ="course_teacher")
@Data
public class CourseTeacher implements Serializable {
    /**
     * 这个关系表由两个外键构成组合主键来唯一标识一条记录
     */
    @TableId(value = "course_id")
    private Integer course_id;

    /**
     * 
     */
    @TableField(value = "teacher_user_id")
    private Integer teacher_user_id;

    /**
     * 0表示授课老师，1表示助教
     */
    @TableField(value = "teacher_role")
    private Integer teacher_role;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}