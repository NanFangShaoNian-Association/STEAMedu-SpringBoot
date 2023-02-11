package com.nfsn.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AdvantageRequest
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-10 22:10
 * @Description: 课程优势传输实体
 */
@Data
@ApiModel("课程优势传输实体")
public class AdvantageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 课程优势
     */
    @ApiModelProperty("课程优势")
    private String courseAdvantage;

    /**
     * 课程优势介绍
     */
    @ApiModelProperty("课程优势介绍")
    private String courseAdvantageIntroduction;
}
