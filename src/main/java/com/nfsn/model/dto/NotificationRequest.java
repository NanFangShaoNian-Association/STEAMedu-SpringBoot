package com.nfsn.model.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snail
 * @create 2023-04-10  14:58
 */
@Data
@ApiModel("通知实体")
public class NotificationRequest implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 通知失效时间
     */
    @ApiModelProperty("通知失效时间")
    private Date expiryTime;

}
