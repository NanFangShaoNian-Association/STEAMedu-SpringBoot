package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName agreement
 */
@TableName(value ="agreement")
@Data
public class Agreement implements Serializable {
    /**
     * 协议ID-主键
     */
    @TableId(value = "agreement_id", type = IdType.AUTO)
    private Integer agreement_id;

    /**
     * 协议名
     */
    @TableField(value = "agreement_name")
    private String agreement_name;

    /**
     * 协议内容
     */
    @TableField(value = "agreement_content")
    private String agreement_content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}