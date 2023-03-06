package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AddFriendsVO
 * @Author: 团子tz
 * @CreateTime: 2023/02/13 19:59
 * @Description: 好友信息响应实体
 */
@Data
@ApiModel("好友信息响应实体")
public class FriendsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 朋友用户id-主键；用户外键
     */
    @ApiModelProperty("朋友用户id")
    private Integer userId;

    /**
     * 用户名-默认值(应该是随机生成)
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

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

    @ApiModelProperty("好友判断 0-不存在  1-存在且未加好友  2-已加好友 3-本人不可查")
    private Integer friendCode;
}
