package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AccountInfoVO
 * @Author: 团子tz
 * @CreateTime: 2023/02/15 14:16
 * @Description: 账号信息响应实体
 */
@Data
@ApiModel("账号信息响应实体")
public class AccountInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * wx_openID
     */
    @ApiModelProperty("wx_openID")
    private String wxOpenid;

    /**
     * qq_openID
     */
    @ApiModelProperty("qq_openID")
    private String qqOpenid;
}
