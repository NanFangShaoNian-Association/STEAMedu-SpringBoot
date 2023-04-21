package com.nfsn.model.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

/**
 * 
 * @author 温格
 * @TableName exam_submission
 */
@TableName(value ="exam_submission")
@Data
public class ExamSubmission implements Serializable {
    /**
     * 考试提交id
     */
    @TableId(value = "submission_id", type = IdType.AUTO)
    private Integer submissionId;

    /**
     * 考试ID
     */
    @TableField(value = "exam_id")
    private Integer examId;

    /**
     * 学生ID
     */
    @TableField(value = "student_message_id")
    private Integer studentMessageId;

    /**
     * 学生的考试答案(json数据)
     */
    @TableField(value = "student_answer")
    private String studentAnswer;

    /**
     * 学生的得分
     */
    @TableField(value = "student_score")
    private Double studentScore;

    /**
     * 学生提交答案的时间
     */
    @TableField(value = "submission_time")
    private Date submissionTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}