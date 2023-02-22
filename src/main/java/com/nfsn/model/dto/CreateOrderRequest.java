package com.nfsn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: CreateOrderRequest
 * @Author: 团子tz
 * @CreateTime: 2022/11/26 21:10
 * @Description: 创建订单请求传输实体
 */
@Data
@ApiModel("创建订单请求传输实体")
public class CreateOrderRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @ApiModelProperty("课程ID")
    String courseId;

    /**
     * 订单标题
     */
    @ApiModelProperty("订单标题")
    String title;

    /**
     * 商品数量
     */
    @ApiModelProperty("商品数量")
    Integer count;

    /**
     * 订单金额（分）
     */
    @ApiModelProperty("订单金额（分）")
    private Integer totalFee;

    /**
     * 订单备注
     */
    @ApiModelProperty("订单备注")
    private String orderRemark;
}
