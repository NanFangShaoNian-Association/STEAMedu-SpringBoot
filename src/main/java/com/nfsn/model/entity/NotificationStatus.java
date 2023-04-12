package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 通知状态表
 * @TableName notification_status
 */
@TableName(value ="notification_status")
@Data
public class NotificationStatus implements Serializable {
    /**
     * 主键，通知接收状态ID
     */
    @TableId(value = "notification_status_id", type = IdType.AUTO)
    private Integer notificationStatusId;

    /**
     * 外键，关联通知表的通知ID
     */
    @TableField(value = "notification_id")
    private Integer notificationId;

    /**
     * 外键，关联学生用户表的用户ID
     */
    @TableField(value = "student_message_id")
    private Integer studentMessageId;

    /**
     * 通知接收状态（0-未阅读 1-已阅读）
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}