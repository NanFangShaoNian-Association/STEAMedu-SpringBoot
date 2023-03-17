package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfsn.common.Message;
import com.nfsn.constants.RedisConstants;
import com.nfsn.controller.common.MessageController;
import com.nfsn.model.entity.Chat;
import com.nfsn.model.vo.SessionInfoVo;
import com.nfsn.service.ChatService;
import com.nfsn.mapper.ChatMapper;
import com.nfsn.utils.AccountHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author Tuanzi
* @description 针对表【chat】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat>
    implements ChatService{

    @Resource
    private StringRedisTemplate stringRedisTemplate ;


    @Override
    public List<Message> listAllMessage(Integer friendId) throws JsonProcessingException {
        Integer userId = AccountHolder.getUser().getUserId();

        List<Chat> chats = this.list(new LambdaQueryWrapper<Chat>()
                // TODO: 2023/3/13 添加时间排序 
                .eq(Chat::getSenderUserId, userId)
                .eq(Chat::getReceiverUserId, friendId)
                .orderByAsc(Chat::getSendTime)
                .or()
                .eq(Chat::getSenderUserId, friendId)
                .eq(Chat::getReceiverUserId, userId)
                .orderByAsc(Chat::getSendTime));

        if (chats != null){
            this.update(new LambdaUpdateWrapper<Chat>()
                    .set(Chat::getReceiveStatus, 1)
                    .eq(Chat::getSenderUserId, friendId)
                    .eq(Chat::getReceiverUserId, userId));
            updateSessionUnReadCount(userId,friendId);
        }

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

    /**
     * 修改redis的会话未读条数
     * @param friendId
     */
    public void updateSessionUnReadCount(Integer userId,Integer friendId) {
        String friendIdString = "" + friendId;
        String key = RedisConstants.Session_List_KEY + userId;
        ObjectMapper objectMapper = new ObjectMapper();

        Object sessionString = stringRedisTemplate.opsForHash().get(key, friendIdString);
        //序列话JSON实体
        SessionInfoVo sessionInfoVo = null;
        try {
            sessionInfoVo = objectMapper.readValue((String) sessionString, SessionInfoVo.class);
            sessionInfoVo.setUnReadCount(0);
            String sessionValue1 = objectMapper.writeValueAsString(sessionInfoVo);
            stringRedisTemplate.opsForHash().put(key,friendIdString,sessionValue1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}




