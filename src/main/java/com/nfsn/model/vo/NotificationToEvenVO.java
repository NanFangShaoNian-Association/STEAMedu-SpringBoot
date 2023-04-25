package com.nfsn.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snail
 * @create 2023-04-13  14:36
 */
@Data
@ApiModel("事件通知响应实体")
public class NotificationToEvenVO implements Serializable {
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
     * 课程名称
     */
    @ApiModelProperty("课程名称")
    private String courseName;

    /**
     * 通知失效时间
     */
    @ApiModelProperty("通知失效时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date expiryTime;

    /**
     * 事件类型
     */
    @ApiModelProperty("事件类型")
    private String evenType = "通知";
}
