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
     * 朋友ID-主键;用户外键
     */
    @TableId(value = "friend_user_id", type = IdType.AUTO)
    private Integer friendUserId;

    /**
     * 自己的ID-主键;用户外键
     */
    @TableField(value = "self_user_id")
    private Integer selfUserId;

    /**
     * 备注昵称-用户名
     */
    @TableField(value = "remark_name")
    private String remarkName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}