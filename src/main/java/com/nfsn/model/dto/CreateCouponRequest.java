package com.nfsn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CreateCouponRequest
 * @Author: FebSnail
 * @CreateTime: 2023/03/03 15:10
 * @Description: 创建优惠券请求传输实体
 */
@Data
@ApiModel("创建优惠券请求传输实体")
public class CreateCouponRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("指定优惠课程Id")
    private Integer designatedCourseId;

    @ApiModelProperty("优惠金额")
    private Integer value;

    @ApiModelProperty("开始日期")
    private Date startDate;

    @ApiModelProperty("失效日期")
    private Date endDate;

    @ApiModelProperty(value = "最低订单金额（满xx）")
    private Integer minOrderAmount;





}
