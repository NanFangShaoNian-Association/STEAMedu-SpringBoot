package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName submit_examination
 */
@TableName(value ="submit_examination")
@Data
public class SubmitExamination implements Serializable {
    /**
     * 考试ID-主键;考试外键
     */
    @TableId(value = "submit_examination_id")
    private Integer submitExaminationId;

    /**
     * 学生信息ID-用户外键
     */
    @TableField(value = "student_id")
    private Integer studentId;

    /**
     * 学生答案-需要 分割符
     */
    @TableField(value = "examination_student_answer")
    private String examinationStudentAnswer;

    /**
     * 试卷提交状态
     */
    @TableField(value = "examination_submit_status")
    private Integer examinationSubmitStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}