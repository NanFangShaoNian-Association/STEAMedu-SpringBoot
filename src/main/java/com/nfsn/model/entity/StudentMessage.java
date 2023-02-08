package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName student_message
 */
@TableName(value ="student_message")
@Data
public class StudentMessage implements Serializable {
    /**
     * 学生信息ID-主键
     */
    @TableId(value = "student_message_id")
    private Integer student_message_id;

    /**
     * 用户ID-用户外键
     */
    @TableField(value = "user_id")
    private Integer user_id;

    /**
     * 学生名
     */
    @TableField(value = "student_message_name")
    private String student_message_name;

    /**
     * 性别
     */
    @TableField(value = "student_sex")
    private String student_sex;

    /**
     * 学校
     */
    @TableField(value = "school")
    private String school;

    /**
     * 联系方式
     */
    @TableField(value = "phone_number")
    private String phone_number;

    /**
     * 学生照片路径-真人图片
     */
    @TableField(value = "student_photo")
    private String student_photo;

    /**
     * 年级
     */
    @TableField(value = "grade")
    private Integer grade;

    /**
     * 学生信息状态码
     */
    @TableField(value = "student_message_status")
    private Integer student_message_status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}