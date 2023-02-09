package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName add_friends
 */
@TableName(value ="add_friends")
@Data
public class AddFriends implements Serializable {
    /**
     * 请求增添的用户ID-主键;用户外键
     */
    @TableId(value = "request_user_id")
    private Integer requestUserId;

    /**
     * 被请求的用户ID-主键;用户外键
     */
    @TableField(value = "requested_user_id")
    private Integer requestedUserId;

    /**
     * 申请理由
     */
    @TableField(value = "addfriends_reason")
    private String addfriendsReason;

    /**
     * 名称备注
     */
    @TableField(value = "remark_name")
    private String remarkName;

    /**
     * 添加状态码-0:未处理;1:已增添为好友;(默认为0)
     */
    @TableField(value = "addfriends_status")
    private Integer addfriendsStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}