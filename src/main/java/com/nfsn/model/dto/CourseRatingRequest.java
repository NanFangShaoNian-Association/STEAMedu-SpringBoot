package com.nfsn.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: AdvantageRequest
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-10 22:10
 * @Description: 课程评价传输实体
 */
@Data
@ApiModel("课程评价传输实体")
public class CourseRatingRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 头像-默认（固定默认头像）
     */
    @ApiModelProperty("头像-默认（固定默认头像）")
    private String userAvatar;

    /**
     * 用户名-默认值(应该是随机生成)
     */
    @ApiModelProperty("用户名-默认值(应该是随机生成)")
    private String userName;

    /**
     * 打分-0代表好评，1代表一般，2代表差评
     */
    @ApiModelProperty("打分-0代表好评，1代表一般，2代表差评")
    private Integer rating;

    /**
     * 评价内容
     */
    @ApiModelProperty("评价内容")
    private String comment;

    /**
     * 评论时间
     */
    @ApiModelProperty("评论时间")
    private Date commentTime;
}
