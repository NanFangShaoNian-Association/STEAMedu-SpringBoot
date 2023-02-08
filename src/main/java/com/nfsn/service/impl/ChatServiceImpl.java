package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Chat;
import com.nfsn.service.ChatService;
import com.nfsn.mapper.ChatMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【chat】的数据库操作Service实现
* @createDate 2023-02-07 14:24:32
*/
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat>
    implements ChatService{

}




