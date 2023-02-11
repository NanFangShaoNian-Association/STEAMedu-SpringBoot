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
 * @TableName course_rating
 */
@TableName(value ="course_rating")
@Data
public class CourseRating implements Serializable {
    /**
     * 评价id-主键
     */
    @TableId(value = "course_rating_id")
    private Integer courseRatingId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 课程id
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 打分-0代表好评，1代表一般，2代表差评
     */
    @TableField(value = "rating")
    private Integer rating;

    /**
     * 评价内容
     */
    @TableField(value = "comment")
    private String comment;

    /**
     * 评论时间
     */
    @TableField(value = "comment_time")
    private Date commentTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}