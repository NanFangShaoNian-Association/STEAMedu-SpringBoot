package com.nfsn.controller.common;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nfsn.common.Message;
import com.nfsn.convertor.MessageDecoder;
import com.nfsn.convertor.MessageEncoder;
import com.nfsn.model.entity.Chat;
import com.nfsn.model.entity.Friends;
import com.nfsn.model.entity.User;
import com.nfsn.service.ChatService;
import com.nfsn.service.FriendsService;
import com.nfsn.service.UserService;
import com.nfsn.utils.AccountHolder;
import com.nfsn.utils.CacheClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ChatController
 * @Author: 团子tz
 * @CreateTime: 2023/02/14 20:59
 * @Description: 通信Controller
 */
@ServerEndpoint(value = "/ws/{userId}", decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
@Component
@Slf4j
public class ChatController {

    //用来记录fromUserId和该session进行绑定
    private static final Map<Integer, Session> map = new ConcurrentHashMap<>();

    //用来存放每个客户端对应的MyWebSocket对象
    private static final Map<Integer, ChatController> webSocketSet = new ConcurrentHashMap<>();

    private List<Integer> friendIds;

    private static UserService userService;

    private static FriendsService friendsService;

    private static ChatService chatService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFriendService(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) {
        log.info("user:{}已连接",userId);
        //在此校验userId是否存在
        if (!checkUserIsExist(userId)){
            return;
        }
        map.put(userId, session);

        //加入set中
        webSocketSet.put(userId, this);
    }

    //检查该用户是否存在，存在返回true，否则为false
    private boolean checkUserIsExist(Integer userId) {
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserId, userId)
                .eq(User::getUserStatus, 0));//查找未注销的用户
        if (user == null){
            return false;
        }
        AccountHolder.saveUser(user);
        return true;
    }

    /**
     * 关闭连接调用的方法
     */
    @OnClose
    public void onClose(@PathParam("userId") Integer userId) {
        //从map中删除
        webSocketSet.remove(userId);
        map.remove(userId);
        AccountHolder.removeUser();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 当前session连接
     */
    @OnMessage
    public void onMessage(Message message, Session session) {
        //检测好友关系
        if (!checkFriendship(message)){
            //todo:抛出非好友异常
        }
        onService(message, session);
    }

    //处理信息
    private void onService(Message message, Session session){
        //保存信息
        saveMessage(message);
        //如果用户在线，则发送消息
        if (webSocketSet.get(Convert.toInt(message.getToId())) != null){
            //发送信息
            sendMessage(message);
        }
    }

    private void sendMessage(Message message) {
        try {
            Session session = map.get(Convert.toInt(message.getToId()));
            session.getBasicRemote().sendObject(message);
        } catch (IOException e) {
            log.error("send发送异常");
            e.printStackTrace();
        } catch (EncodeException e) {
            log.error("send转化异常");
            e.printStackTrace();
        }
    }

    private void saveMessage(Message message) {
        Chat chat = new Chat();
        chat.setSenderUserId(Convert.toInt(message.getFromId()));
        chat.setReceiverUserId(Convert.toInt(message.getToId()));
        chat.setMessage(message.getContent());
        chat.setSendTime(message.getTime() == null ? new Date() : message.getTime());
        chat.setReceiveStatus(0);//未接收

        chatService.save(chat);
    }

    //检测好友关系，好友返回true，否则返回false
    private boolean checkFriendship(Message message) {
        long count = friendsService.count(new LambdaQueryWrapper<Friends>()
                .eq(Friends::getSelfUserId, message.getFromId())
                .eq(Friends::getFriendUserId, message.getToId()));
        return count == 1;
    }

    /**
     * 发生错误时调用
     *
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

}
