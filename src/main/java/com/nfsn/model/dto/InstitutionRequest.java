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
 * @Description: 机构传输实体
 */
@Data
@ApiModel("机构传输实体")
public class InstitutionRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 头像-默认（固定默认头像）
     */
    @ApiModelProperty("头像-默认（固定默认头像）")
    private String userAvatar;

    /**
     * 用户名-默认值(应该是随机生成)
     */
    @ApiModelProperty("用户名-默认值(应该是随机生成)")
    private String userName;

    /**
     * 用户简介
     */
    @ApiModelProperty("用户简介")
    private String userIntroduction;
}
