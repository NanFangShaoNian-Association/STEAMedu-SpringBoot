package com.nfsn.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ApplyFriendRequest
 * @Author: 团子tz
 * @CreateTime: 2023/02/13 20:38
 * @Description: 申请好友传输实体
 */
@Data
@ApiModel("申请好友传输实体")
public class ApplyFriendRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 被请求的用户ID-主键;用户外键
     */
    @ApiModelProperty("被请求的用户ID")
    private Integer requestedUserId;

    /**
     * 申请理由
     */
    @ApiModelProperty("申请理由")
    private String addfriendsReason;

    /**
     * 名称备注
     */
    @ApiModelProperty("名称备注")
    private String remarkName;
}