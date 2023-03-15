package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snail
 * @create 2023-03-13  19:29
 */
@Data
@ApiModel("优惠券传输实体")
public class CouponInfoVo implements Serializable {

    /**
     * 优惠券ID-主键
     */
    @ApiModelProperty("优惠券ID")
    private Integer couponId;

    /**
     * 优惠券名
     */
    @ApiModelProperty("优惠券名")
    private String couponName;

    /**
     * 优惠券兑换码
     */
    @ApiModelProperty("优惠券兑换码")
    private String couponCode;

    /**
     * 优惠金额
     */
    @ApiModelProperty("优惠金额")
    private Integer value;

    /**
     * 开始日期
     */
    @ApiModelProperty("开始日期")
    private Date startDate;

    /**
     * 失效日期
     */
    @ApiModelProperty("失效日期")
    private Date endDate;

    /**
     * 最低订单金额
     */
    @ApiModelProperty("最低订单金额")
    private Integer minOrderAmount;

    /**
     * 指定优惠课程ID
     */
    @ApiModelProperty("优惠课程ID")
    private Integer designatedCourseId;

    /**
     * 发卷人-外键
     */
    @ApiModelProperty("发卷人")
    private Integer issuerUserId;

    /**
     * 优惠券状态-0为不存在，1为存在，2为已使用
     */
    @ApiModelProperty("优惠券状态")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
