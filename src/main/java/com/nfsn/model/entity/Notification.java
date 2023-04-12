package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 通知表
 * @author 温格
 * @TableName notification
 */
@TableName(value ="notification")
@Data
public class Notification implements Serializable {
    /**
     * 通知消息id
     */
    @TableId(value = "notification_id", type = IdType.AUTO)
    private Integer notificationId;

    /**
     * 通知名称
     */
    @TableField(value = "notification_name")
    private String notificationName;

    /**
     * 通知内容
     */
    @TableField(value = "notification_content")
    private String notificationContent;

    /**
     * 课程id
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 发布者id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 通知发布时间
     */
    @TableField(value = "publish_time")
    private Date publishTime;

    /**
     * 通知失效时间
     */
    @TableField(value = "expiry_time")
    private Date expiryTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}