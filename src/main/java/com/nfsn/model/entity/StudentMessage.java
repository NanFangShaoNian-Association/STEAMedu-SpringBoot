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
 * @TableName student_message
 */
@TableName(value ="student_message")
@Data
public class StudentMessage implements Serializable {
    /**
     * 学生信息ID-主键
     */
    @TableId(value = "student_message_id")
    private Integer studentMessageId;

    /**
     * 用户ID-用户外键
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 学生名
     */
    @TableField(value = "student_message_name")
    private String studentMessageName;

    /**
     * 性别
     */
    @TableField(value = "student_sex")
    private String studentSex;

    /**
     * 学校
     */
    @TableField(value = "school")
    private String school;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 联系方式
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 学生照片-真人图片
     */
    @TableField(value = "student_photo")
    private String studentPhoto;

    /**
     * 年级
     */
    @TableField(value = "grade")
    private String grade;

    /**
     * 学生信息状态码
     */
    @TableField(value = "student_message_status")
    private Integer studentMessageStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}