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
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户ID-主键
     */
    @TableId(value = "user_id")
    private Integer userId;

    /**
     * 用户名-默认值(应该是随机生成)
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户角色-0:平台管理员;1:机构管理员;2:老师;3:学生;(默认为0)
     */
    @TableField(value = "user_role")
    private Integer userRole;

    /**
     * 头像-默认（固定默认头像）
     */
    @TableField(value = "user_avatar")
    private String userAvatar;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号-唯一
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 用户简介
     */
    @TableField(value = "user_introduction")
    private String userIntroduction;

    /**
     * 省份
     */
    @TableField(value = "user_location_province")
    private String userLocationProvince;

    /**
     * 城市
     */
    @TableField(value = "user_location_city")
    private String userLocationCity;

    /**
     * 区域
     */
    @TableField(value = "user_location_region")
    private String userLocationRegion;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private Double latitude;

    /**
     * wx_openID
     */
    @TableField(value = "wx_openID")
    private String wxOpenid;

    /**
     * qq_openID
     */
    @TableField(value = "qq_openID")
    private String qqOpenid;

    /**
     * 最后一次上线时间
     */
    @TableField(value = "login_last_time")
    private Date loginLastTime;

    /**
     * 最后一次下线时间
     */
    @TableField(value = "off_line_last_time")
    private Date offLineLastTime;

    /**
     * 最后一次登录ip
     */
    @TableField(value = "login_last_time_ip")
    private String loginLastTimeIp;

    /**
     * 微信unionid
     */
    @TableField(value = "wx_unionid")
    private String wxUnionid;

    /**
     * QQunionid
     */
    @TableField(value = "QQ_unionid")
    private String qqUnionid;

    /**
     * 账号注册时间-非空
     */
    @TableField(value = "user_regist_time")
    private Date userRegistTime;

    /**
     * 用户状态码-0:未注销;1:已注销;2:暂时被冻结;(默认为0)
     */
    @TableField(value = "user_status")
    private Integer userStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}