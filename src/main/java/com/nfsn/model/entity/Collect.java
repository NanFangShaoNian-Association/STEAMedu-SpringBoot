package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 收藏课程类实体
 * @author snail
 * @create 2023-03-22  9:03
 */
@TableName(value ="collect")
@Data
public class Collect implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 收藏实体类-主键
     */
    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;

    /**
     * 用户id-外键
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 课程id-外键
     */
    @TableField(value = "course_id")
    private Integer courseId;
}
