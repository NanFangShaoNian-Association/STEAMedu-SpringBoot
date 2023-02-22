package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: PersonalInfoVO
 * @Author: 团子tz
 * @CreateTime: 2023/02/13 15:02
 * @Description: 用户基本信息响应实体
 */
@Data
@ApiModel("用户基本信息响应实体")
public class PersonalInfoVO  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id-默认值(应该是随机生成)
     */
    @ApiModelProperty("用户ID")
    private String userId;
    /**
     * 用户名-默认值(应该是随机生成)
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 头像-默认（固定默认头像）
     */
    @ApiModelProperty("头像")
    private String userAvatar;

    /**
     * 用户简介
     */
    @ApiModelProperty("用户简介")
    private String userIntroduction;

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
}
