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
 * @TableName chat
 */
@TableName(value ="chat")
@Data
public class Chat implements Serializable {
    /**
     * 聊天记录ID-主键
     */
    @TableId(value = "chat_id", type = IdType.AUTO)
    private Integer chat_id;

    /**
     * 发送者ID-用户外键
     */
    @TableField(value = "sender_user_id")
    private Integer sender_user_id;

    /**
     * 接受者ID-用户外键
     */
    @TableField(value = "receiver_user_id")
    private Integer receiver_user_id;

    /**
     * 消息内容
     */
    @TableField(value = "message")
    private String message;

    /**
     * 发送时间
     */
    @TableField(value = "send_time")
    private Date send_time;

    /**
     * 接受状态
     */
    @TableField(value = "receive_status")
    private Integer receive_status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}