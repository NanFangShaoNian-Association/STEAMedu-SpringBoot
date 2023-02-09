package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_coupon
 */
@TableName(value ="user_coupon")
@Data
public class UserCoupon implements Serializable {
    /**
     * 优惠券ID-外键
     */
    @TableId(value = "coupon_id")
    private Integer couponId;

    /**
     * 用户ID-外键
     */
    @TableField(value = "user_id")
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}