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
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer user_id;

    /**
     * 用户名-默认值（随机生成）
     */
    @TableField(value = "user_name")
    private String user_name;

    /**
     * 用户角色-0:平台管理员;1:机构管理员;2:老师;3:学生;(默认为0)
     */
    @TableField(value = "user_role")
    private Integer user_role;

    /**
     * 头像路径-默认（固定默认头像）
     */
    @TableField(value = "user_avatar")
    private String user_avatar;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "phone_number")
    private String phone_number;

    /**
     * wx_openID
     */
    @TableField(value = "wx_openID")
    private String wx_openID;

    /**
     * 区域
     */
    @TableField(value = "user_location_region")
    private String user_location_region;

    /**
     * 城市
     */
    @TableField(value = "user_location_city")
    private String user_location_city;

    /**
     * 省份
     */
    @TableField(value = "user_location_province")
    private String user_location_province;

    /**
     * qq_openID
     */
    @TableField(value = "qq_openID")
    private String qq_openID;

    /**
     * 最后一次上线时间
     */
    @TableField(value = "login_last_time")
    private Date login_last_time;

    /**
     * 最后一次下线时间
     */
    @TableField(value = "off_line_last_time")
    private Date off_line_last_time;

    /**
     * 最后一次登录ip
     */
    @TableField(value = "login_last_time_ip")
    private String login_last_time_ip;

    /**
     * 账号注册时间
     */
    @TableField(value = "user_regist_time")
    private Date user_regist_time;

    /**
     * 用户状态码-0:未注销;1:已注销;2:暂时被冻结;(默认为0)
     */
    @TableField(value = "user_status")
    private Integer user_status;

    /**
     * 微信unionid
     */
    @TableField(value = "wx_unionid")
    private String wx_unionid;

    /**
     * QQunionid
     */
    @TableField(value = "QQ_unionid")
    private String QQ_unionid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}