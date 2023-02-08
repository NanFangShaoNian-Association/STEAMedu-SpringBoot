package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName about
 */
@TableName(value ="about")
@Data
public class About implements Serializable {
    /**
     * 关于ID-主键
     */
    @TableId(value = "about_id", type = IdType.AUTO)
    private Integer about_id;

    /**
     * LOGO路径
     */
    @TableField(value = "LOGO_path")
    private String LOGO_path;

    /**
     * 软件名
     */
    @TableField(value = "software_name")
    private String software_name;

    /**
     * 版本号
     */
    @TableField(value = "version")
    private Integer version;

    /**
     * 开发团队
     */
    @TableField(value = "development_team")
    private String development_team;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}