package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName cart
 */
@TableName(value ="cart")
@Data
public class Cart implements Serializable {
    /**
     * 购物车id-主键
     */
    @TableId(value = "cart_id")
    private Integer cartId;

    /**
     * 用户id-用户外键
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 课程id-课程外键
     */
    @TableField(value = "course_id")
    private Integer courseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}