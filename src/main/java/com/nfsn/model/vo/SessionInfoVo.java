package com.nfsn.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 聊天实体，为redis提供实体、为Vo传输实体
 * @author snail
 * @create 2023-03-14  9:23
 */
@Data
public class SessionInfoVo {

    private int id;

    /**
     * 自己的id
     */
    private int userId;

    /**
     * 朋友的id
     */
    private int friedId;

    /**
     * 会话名称
     */
    private String SessionName;

    /**
     * 朋友的头像
     */
    private String friedAvatar;

    /**
     * 最后一条消息内容
     */
    private String lastMessage;

    /**
     * 未读消息条数
     */
    private Integer unReadCount;

    /**
     * 最后一条消息的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastTime;

}
