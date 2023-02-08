package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName friends
 */
@TableName(value ="friends")
@Data
public class Friends implements Serializable {
    /**
     * 好友表ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 朋友ID-用户外键
     */
    @TableField(value = "friend_user_id")
    private Integer friend_user_id;

    /**
     * 自己的ID-用户外键
     */
    @TableField(value = "self_user_id")
    private Integer self_user_id;

    /**
     * 备注昵称
     */
    @TableField(value = "remark_name")
    private String remark_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}