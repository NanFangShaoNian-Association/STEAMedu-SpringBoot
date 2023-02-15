package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: LoginVO
 * @Author: 团子tz
 * @CreateTime: 2022/10/31 14:32
 * @Description: 登录响应实体
 */
@Data
@ApiModel("用户登录响应实体")
public class LoginVO implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * token
     */
    @ApiModelProperty("用户登录令牌")
    private String token;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 用户角色-0:平台管理员;1:机构管理员;2:老师;3:学生;(默认为0)
     */
    @ApiModelProperty("用户角色-0:平台管理员;1:机构管理员;2:老师;3:学生;(默认为0)")
    private Integer roleId = 0;

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
     * 简介
     */
    @ApiModelProperty("简介")
    private String introduction;

    /**
     * 省份
     */
    @ApiModelProperty("省份")
    private String userLocationProvince;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String userLocationCity;

    /**
     * 区域
     */
    @ApiModelProperty("区域")
    private String userLocationRegion;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private Double latitude;

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
