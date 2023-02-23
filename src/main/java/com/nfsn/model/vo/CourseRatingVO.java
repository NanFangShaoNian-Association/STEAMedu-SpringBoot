package com.nfsn.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: CourseRatingVO
 * @Description: 课程评价VO
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-11 23:38
 * @Version: 1.0
 **/
@Data
@ApiModel(value = "课程评价VO")
public class CourseRatingVO {
    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String userAvatar;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 打分-0代表好评，1代表一般，2代表差评
     */
    @ApiModelProperty(value = "打分-0代表好评，1代表一般，2代表差评")
    private Integer rating;

    /**
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容")
    private String comment;

    /**
     * 评论时间
     */
    // TODO:这里需要将时间格式定义为yyyy-mm-dd
    @JsonFormat(pattern="yyyy-MM-dd ",timezone="GMT+8")
    @ApiModelProperty(value = "评论时间")
    private Date commentTime;
}