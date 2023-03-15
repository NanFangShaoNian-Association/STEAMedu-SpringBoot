package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snail
 * @create 2023-03-06  14:51
 */
@Data
@ApiModel("消息列表响应实体")
public class ChatInfoVo implements Serializable {


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 聊天记录ID-主键
     */
    @ApiModelProperty("聊天记录ID")
    private Integer chatId;

    /**
     * 发送者ID-外键
     */
    @ApiModelProperty("发送者ID")
    private Integer senderUserId;

    /**
     * 接受者ID-外键
     */
    @ApiModelProperty("接受者ID")
    private Integer receiverUserId;

    /**
     * 消息内容
     */
    @ApiModelProperty("消息内容")
    private String message;

    /**
     * 发送时间
     */
    @ApiModelProperty("发送时间")
    private Date sendTime;

    /**
     * 接受状态-0为未接收，1为已接收
     */
    @ApiModelProperty("接受状态-0为未接收，1为已接收")
    private Integer receiveStatus;

    /**
     *最后一条消息内容
     */
    @ApiModelProperty("最后一条消息内容")
    private String lastContent;

}
