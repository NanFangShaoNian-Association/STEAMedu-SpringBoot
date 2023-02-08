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
 * @TableName privacy
 */
@TableName(value ="privacy")
@Data
public class Privacy implements Serializable {
    /**
     * 用户身份信息id
     */
    @TableId(value = "privacy_id", type = IdType.AUTO)
    private Integer privacy_id;

    /**
     * 用户ID-用户外键
     */
    @TableField(value = "user_id")
    private Integer user_id;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String real_name;

    /**
     * 身份证号码-18位数
     */
    @TableField(value = "id_number")
    private String id_number;

    /**
     * 性别-0:男生;1:女生;(默认为0)
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 生日-默认根据当前时间戳更新
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 省份
     */
    @TableField(value = "personal_province")
    private String personal_province;

    /**
     * 个人简介
     */
    @TableField(value = "personal_profile")
    private String personal_profile;

    /**
     * 城市
     */
    @TableField(value = "personal_city")
    private String personal_city;

    /**
     * 区域
     */
    @TableField(value = "personal_region")
    private String personal_region;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}