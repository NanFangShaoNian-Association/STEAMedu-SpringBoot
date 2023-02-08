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
     * 好友添加ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 申请人ID-用户外键
     */
    @TableField(value = "request_user_id")
    private Integer request_user_id;

    /**
     * 对方ID-用户外键
     */
    @TableField(value = "requested_user_id")
    private Integer requested_user_id;

    /**
     * 申请理由
     */
    @TableField(value = "addfriends_reason")
    private String addfriends_reason;

    /**
     * 名称备注
     */
    @TableField(value = "name_remark")
    private String name_remark;

    /**
     * 好友添加状态码-0:非好友状态;1:好友状态;(默认为0)
     */
    @TableField(value = "addfriends_status")
    private Integer addfriends_status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}