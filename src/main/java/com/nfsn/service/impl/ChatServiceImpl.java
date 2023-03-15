package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.common.Message;
import com.nfsn.model.entity.Chat;
import com.nfsn.service.ChatService;
import com.nfsn.mapper.ChatMapper;
import com.nfsn.utils.AccountHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Tuanzi
* @description 针对表【chat】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat>
    implements ChatService{

    @Override
    public List<Message> listAllMessage(Integer friendId) {
        Integer userId = AccountHolder.getUser().getUserId();

        List<Chat> chats = this.list(new LambdaQueryWrapper<Chat>()
                // TODO: 2023/3/13 添加时间排序 
                .eq(Chat::getSenderUserId, userId)
                .eq(Chat::getReceiverUserId, friendId)
                .or()
                .eq(Chat::getSenderUserId, friendId)
                .eq(Chat::getReceiverUserId, userId));

        //封装
        List<Message> messages = new ArrayList<>(chats.size());
        chats.stream().forEach(chat -> {
            Message message = new Message();
            message.setId(Convert.toStr(chat.getChatId()));
            message.setFromId(Convert.toStr(chat.getSenderUserId()));
            message.setToId(Convert.toStr(chat.getReceiverUserId()));
            message.setContent(chat.getMessage());
            message.setTime(chat.getSendTime());
            messages.add(message);
        });

        return messages;
    }

}




