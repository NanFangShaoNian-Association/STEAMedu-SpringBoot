package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AdvantageVO
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-10 22:10
 * @Description: 课程详细介绍传输实体
 */
@Data
@ApiModel("课程详细介绍传输实体")
public class CourseDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 图片-存图片路径
     */
    @ApiModelProperty("图片-存图片路径")
    private String picture;

    /**
     * 显示序号
     */
    @ApiModelProperty("显示序号")
    private Integer displayOrder;
}
