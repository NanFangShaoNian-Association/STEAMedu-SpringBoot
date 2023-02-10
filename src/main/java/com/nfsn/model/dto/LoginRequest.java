package com.nfsn.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: LoginRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/31 08:58
 * @Description: 登录传输实体
 */
@Data
@ApiModel("用户登录传输实体")
public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 凭证-手机号登录凭证为手机号码，微信和QQ则为对应的凭据
     */
    @ApiModelProperty("凭证-手机号登录凭证为手机号码，微信和QQ则为对应的凭据")
    private String certificate;

    /**
     * 验证码-仅在手机号登录时可选
     */
    @ApiModelProperty("验证码-仅在手机号登录时可选")
    private String verifyCode;

    /**
     * 角色（用户手机号0，用户微信1，用户QQ登录2），默认为0
     */
    @ApiModelProperty("角色（用户手机号0，用户微信1，用户QQ登录2），默认为0")
    private Integer roleId = 0;
}
