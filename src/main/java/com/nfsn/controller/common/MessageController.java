package com.nfsn.controller.common;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfsn.common.Result;
import com.nfsn.constants.RedisConstants;
import com.nfsn.constants.ResultCode;
import com.nfsn.model.entity.Chat;
import com.nfsn.model.vo.SessionInfoVo;
import com.nfsn.service.ChatService;
import com.nfsn.service.FriendsService;
import com.nfsn.service.UserService;
import com.nfsn.utils.AccountHolder;
import com.nfsn.utils.CacheClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author snail
 * @create 2023-03-06  14:43
 */
@Api(value = "聊天相关接口",tags = "聊天相关接口")
@RequestMapping("/message")
@RestController
@ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class)
})
public class MessageController {
    /**
     * websoker是多例，类是单例 所以要多次获取bean
     */
    @Resource
    private UserService userService  = SpringUtil.getBean(UserService.class);

    @Resource
    private ChatService chatService = SpringUtil.getBean(ChatService.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);


    @ApiOperation("根据用户id进行查询已经建立的全部会话")
    @GetMapping("/SessionList")
    public List<SessionInfoVo> SessionList() throws IOException {
        Integer userId = AccountHolder.getUser().getUserId();

        String key = RedisConstants.Session_List_KEY + userId;
        List<Object> SessionRedisList = stringRedisTemplate.opsForHash().values(key);
        ObjectMapper objectMapper = new ObjectMapper();
        List<SessionInfoVo> sessionInfoVoList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        // 将 List<Object> 转换为 List<String>
        for (Object obj : SessionRedisList){
            stringList.add((String) obj);
        }
        //将list<String> 反序列化为list<SessionInfoVo>
        for (String s : stringList){
            SessionInfoVo sessionInfoVo = objectMapper.readValue(s,SessionInfoVo.class);
            sessionInfoVoList.add(sessionInfoVo);
        }
        return sessionInfoVoList;
    }


    @ApiOperation("根据对方id进行创建会话")
    @PostMapping("/createSession")
    public Result createSession(@RequestParam Integer userId, @RequestParam Integer friendId) throws JsonProcessingException {
        //建立key和hashKey
        String fridId = "" + friendId;
        String friToId = "" + userId;
        String key = RedisConstants.Session_List_KEY + userId;
        String friendKey = RedisConstants.Session_List_KEY + friendId;
        Object o = stringRedisTemplate.opsForHash().get(key, fridId);
        Object o1 = stringRedisTemplate.opsForHash().get(friendKey, friToId);
        ObjectMapper objectMapper = new ObjectMapper();
        if (o == null){
            SessionInfoVo sessionInfoVo = addSession(userId, friendId);
            sessionInfoVo.setUnReadCount(0);
            //序列话JSON实体
            String sessionValue = objectMapper.writeValueAsString(sessionInfoVo);
            stringRedisTemplate.opsForHash().put(key,fridId,sessionValue);
            //判断对方有没有会话，没有则创建
            if (o1 == null){
                SessionInfoVo sessionInfoVo2 = addSession(friendId, userId);
                sessionInfoVo2.setUnReadCount(0);
                //序列话JSON实体 objectMapper类方法
                String sessionValue2 = objectMapper.writeValueAsString(sessionInfoVo2);
                stringRedisTemplate.opsForHash().put(friendKey,friToId,sessionValue2);
            }
            return  Result.success();
        }
        if (o1 == null){
            SessionInfoVo sessionInfoVo2 = addSession(friendId, userId);
            String sessionValue2 = objectMapper.writeValueAsString(sessionInfoVo2);
            stringRedisTemplate.opsForHash().put(friendKey,friToId,sessionValue2);
            return Result.success("自己会话存在，已为对方开启会话");
        }
        return Result.failure(ResultCode.SESSION_HAS_EXISTED);
    }

    @ApiOperation("更新redis中的数据消息")
    @PutMapping("/updateSession")
    public void updateSession( @RequestParam Integer userId ,@RequestParam Integer friendId,String lastMessage) throws JsonProcessingException {
        //建立key和hashKey
        String key =  RedisConstants.Session_List_KEY + userId;
        String friendKey =  RedisConstants.Session_List_KEY + friendId;
        String friendIdString = "" + friendId;
        String userIdString = "" + userId;
        ObjectMapper objectMapper = new ObjectMapper();

        //更新到我的会话
        Object sessionString = stringRedisTemplate.opsForHash().get(key, friendIdString);
        //序列话JSON实体
        SessionInfoVo sessionInfoVo = objectMapper.readValue((String) sessionString, SessionInfoVo.class);
        sessionInfoVo.setLastMessage(lastMessage);
        sessionInfoVo.setLastTime(new Date());
        sessionInfoVo.setUnReadCount(sessionInfoVo.getUnReadCount()+1);
        String sessionValue1 = objectMapper.writeValueAsString(sessionInfoVo);
        stringRedisTemplate.opsForHash().put(key,friendIdString,sessionValue1);

        //更新到朋友的会话
        Object friendSessionString = stringRedisTemplate.opsForHash().get(friendKey, userIdString);
        //序列话JSON实体
        SessionInfoVo friendSessionInfoVo = objectMapper.readValue((String) friendSessionString, SessionInfoVo.class);
        friendSessionInfoVo.setLastMessage(lastMessage);
        friendSessionInfoVo.setLastTime(new Date());
        friendSessionInfoVo.setUnReadCount(friendSessionInfoVo.getUnReadCount()+1);
        String sessionValue2 = objectMapper.writeValueAsString(friendSessionInfoVo);
        stringRedisTemplate.opsForHash().put(friendKey,userIdString,sessionValue2);
    }




    // 删除会话
    @ApiOperation("删除一个会话")
    @DeleteMapping("/delSession")
    public Result delSession(@RequestParam Integer friendId){
        Integer userId = AccountHolder.getUser().getUserId();
        String key =  RedisConstants.Session_List_KEY + userId;
        Long delete = stringRedisTemplate.opsForHash().delete(key, friendId);
        if (delete ==0){
            return  Result.failure(ResultCode.SESSION_NOT_DELETE);
        }
        return Result.success();
    }
    /**
     * 根据两者id返回会话实体
     * @param userId
     * @param friendId
     * @return
     */
    public SessionInfoVo addSession(Integer userId,Integer friendId){

        Long count = chatService.count(new LambdaQueryWrapper<Chat>()
                .eq(Chat::getSenderUserId, userId)
                .eq(Chat::getReceiverUserId, friendId)
                .eq(Chat::getReceiveStatus, 0));
        Integer integerCount = Convert.toInt(count);

        //存储对应数据
        SessionInfoVo sessionInfoVo = new SessionInfoVo();
        sessionInfoVo.setUserId(userId);
        sessionInfoVo.setFriedId(friendId);
        sessionInfoVo.setSessionName(userService.getById(friendId).getUserName());
        sessionInfoVo.setFriedAvatar(userService.getById(friendId).getUserAvatar());
        sessionInfoVo.setLastTime(new Date());

        sessionInfoVo.setUnReadCount(integerCount);
        return sessionInfoVo;
    }

}
