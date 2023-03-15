package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("被使用的优惠券金额")
public class UsedCouponAmountVO {
    @ApiModelProperty("优惠券金额")
    private Integer value;
}