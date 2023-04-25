package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName slideshow
 */
@TableName(value ="slideshow")
@Data
public class Slideshow implements Serializable {
    /**
     * 轮播图id-主键
     */
    @TableId(value = "slideshow_id", type = IdType.AUTO)
    private Integer slideshowId;

    /**
     * 图片-存图片路径
     */
    @TableField(value = "slideshow_picture")
    private String slideshowPicture;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}