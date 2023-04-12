package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.convert.PeriodFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snail
 * @create 2023-04-11  15:53
 */
@Data
@ApiModel("事件消息通知响应实体")
public class NotificationVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 通知消息id
     */
    @ApiModelProperty("通知消息id")
    private Integer notificationId;

    /**
     * 通知名称
     */
    @ApiModelProperty("通知名称")
    private String notificationName;

    /**
     * 通知内容
     */
    @ApiModelProperty("通知内容")
    private String notificationContent;

    /**
     * 课程id
     */
    @ApiModelProperty("课程id")
    private Integer courseId;

    /**
     * 课程名称
     */
    @ApiModelProperty("课程名称")
    private String courseName;

    /**
     * 发布者id
     */
    @ApiModelProperty("发布者id")
    private Integer userId;

    /**
     * 发布者姓名
     */
    @ApiModelProperty("发布者姓名")
    private String userName;

    /**
     * 通知发布时间
     */
    @ApiModelProperty("通知发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date publishTime;

    /**
     * 通知失效时间
     */
    @ApiModelProperty("通知失效时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date expiryTime;

    /**
     * 事件类型
     */
    private String evenType = "通知";
}
