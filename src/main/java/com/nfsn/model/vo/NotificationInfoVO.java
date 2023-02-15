package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: NotificationInfoVO
 * @Author: 团子tz
 * @CreateTime: 2023/02/15 14:00
 * @Description: 消息通知响应实体
 */
@Data
@ApiModel("消息通知响应实体")
public class NotificationInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 请求增添的用户ID-主键;用户外键
     */
    @ApiModelProperty("请求增添的用户ID")
    private Integer requestUserId;

    /**
     * 用户名-默认值(应该是随机生成)
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 头像-默认（固定默认头像）
     */
    @ApiModelProperty("头像")
    private String userAvatar;

    /**
     * 申请理由
     */
    @ApiModelProperty("申请理由")
    private String addfriendsReason;

    /**
     * 添加状态码-0:未处理;1:已增添为好友;(默认为0)
     */
    @ApiModelProperty("添加状态码-0:未处理;1:已增添为好友;(默认为0)")
    private Integer addfriendsStatus;
}
