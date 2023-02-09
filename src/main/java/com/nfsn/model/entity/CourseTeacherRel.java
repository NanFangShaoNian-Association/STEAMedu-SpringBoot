package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName course_teacher_rel
 */
@TableName(value ="course_teacher_rel")
@Data
public class CourseTeacherRel implements Serializable {
    /**
     * 课程ID-主键
     */
    @TableId(value = "course_id")
    private Integer courseId;

    /**
     * 老师ID-主键
     */
    @TableField(value = "teacher_user_id")
    private Integer teacherUserId;

    /**
     * 老师在这门课中的角色-0表示授课老师，1表示助教
     */
    @TableField(value = "teacher_role")
    private Integer teacherRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}