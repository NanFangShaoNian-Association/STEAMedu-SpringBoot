package com.nfsn.controller.common;

import com.nfsn.model.vo.SessionInfoVo;
import com.nfsn.service.UserService;
import com.nfsn.utils.AccountHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author snail
 * @create 2023-03-06  14:43
 */
@Api(value = "聊天相关接口",tags = "聊天相关接口")
@RequestMapping("/message")
@RestController
public class MessageController {

    @Resource
    private UserService userService;

    // TODO: 2023/3/13 聊天的接口待开发

    @ApiOperation("根据用户id进行查询已经建立的会话")
    @PutMapping("SessionList")
    public List<SessionInfoVo> SessionList(){
        Integer userId = AccountHolder.getUser().getUserId();
        //根据用户id查询会话列表

        return null;
    }
    @ApiOperation("测试接口")
    @GetMapping("test")
    public void msgTest(@RequestParam Integer friendId){
        String userAvatar = userService.getById(friendId).getUserAvatar();
        System.out.println(userAvatar);
    }


    public void createSession(@RequestParam Integer friendId,String friendName){

        Integer userId = AccountHolder.getUser().getUserId();
        //检查双方会话存不存在

        //判断自己会话存在否
        if(false){
            addSession(userId,friendId);
            //存储实体到redis
        }
        //判断朋友会话存在否
        if(false){
            addSession(friendId,userId);

            //存储实体到redis
        }
    }

    // 删除会话
    @ApiOperation("删除一个会话")
    @DeleteMapping("/delSession")
    public void delSession(@RequestParam Long sessionId){
//        sessionListMapper.deleteByPrimaryKey(sessionId);
        //根据id删除会话
    }



    /**
     * 根据两者id返回会话实体
     * @param userId
     * @param friendId
     * @return
     */
    public SessionInfoVo addSession(Integer userId,Integer friendId){
        //存储对应数据
        SessionInfoVo sessionInfoVo = new SessionInfoVo();
        sessionInfoVo.setUserId(userId);
        sessionInfoVo.setFriedId(friendId);
        sessionInfoVo.setSessionName(userService.getById(friendId).getUserName());
        sessionInfoVo.setFriedAvatar(userService.getById(friendId).getUserAvatar());
        sessionInfoVo.setLastTime(new Date());
        sessionInfoVo.setUnReadCount(1);
        return sessionInfoVo;
    }


}
