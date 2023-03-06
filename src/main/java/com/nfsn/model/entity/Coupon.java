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
 * @TableName coupon
 */
@TableName(value ="coupon")
@Data
public class Coupon implements Serializable {
    /**
     * 优惠券ID-主键
     */
    @TableId(value = "coupon_id")
    private Integer couponId;

    /**
     * 优惠券兑换码
     */
    @TableField(value = "coupon_code")
    private String couponCode;

    /**
     * 优惠金额
     */
    @TableField(value = "value")
    private Integer value;

    /**
     * 开始日期
     */
    @TableField(value = "start_date")
    private Date startDate;

    /**
     * 失效日期
     */
    @TableField(value = "end_date")
    private Date endDate;

    /**
     * 最低订单金额
     */
    @TableField(value = "min_order_amount")
    private Integer minOrderAmount;

    /**
     * 指定优惠课程ID
     */
    @TableField(value = "designated_course_id")
    private Integer designatedCourseId;

    /**
     * 发卷人-外键
     */
    @TableField(value = "issuer_user_id")
    private Integer issuerUserId;

    /**
     * 优惠券状态-0为不存在，1为存在，2为已使用
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}