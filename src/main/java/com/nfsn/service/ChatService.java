package com.nfsn.service;

import com.nfsn.common.Message;
import com.nfsn.model.entity.Chat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【chat】的数据库操作Service
* @createDate 2023-02-09 16:30:52
*/
public interface ChatService extends IService<Chat> {

    List<Message> listAllMessage(Integer friendId);

}
