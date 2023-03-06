package com.nfsn.controller.user;

import cn.hutool.core.convert.Convert;
import com.nfsn.common.Message;
import com.nfsn.model.dto.ApplyFriendRequest;
import com.nfsn.model.vo.FriendsVO;
import com.nfsn.service.ChatService;
import com.nfsn.service.FriendsService;
import com.nfsn.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: UserChatController
 * @Author: 团子tz
 * @CreateTime: 2023/02/13 19:52
 * @Description: 用户聊天操作类
 */
@RestController
@RequestMapping("/users/chats")
@Api("用户聊天操作类")
@ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class)
})
public class UserChatController {
    @Resource
    private UserService userService;

    @Resource
    private FriendsService friendsService;

    @Resource
    private ChatService chatService;

    @GetMapping("/list")
    @ApiOperation("获取好友列表")
    public List<FriendsVO> list(){
        return friendsService.listFriend();
    }

    @GetMapping("/list/{friendId}/allMessage")
    @ApiOperation("获取所有信息")
    public List<Message> listAllMessage(@PathVariable("friendId") String friendId){
        Integer targetFriendId = Convert.toInt(friendId, null);
        return chatService.listAllMessage(targetFriendId);
    }

    @DeleteMapping("/delete/{friendId}")
    @ApiOperation("删除好友")
    public void deleteFriend(@PathVariable("friendId") String friendId){
        Integer delFriendId = Convert.toInt(friendId, null);
        friendsService.deleteFriend(delFriendId);
    }

    @GetMapping("/search/{target}")
    @ApiOperation("搜索用户（手机号或者STEAM学号）")
    public FriendsVO searchUser(@PathVariable("target") String target){
        Long ifFriend = friendsService.ifFriend(target);
        return userService.searchUser(target,ifFriend);
    }

    @PostMapping("/applyFriend")
    @ApiOperation("申请好友")
    public void applyFriend(@RequestBody ApplyFriendRequest applyFriendRequest){
        friendsService.applyFriend(applyFriendRequest);
    }

    @PostMapping("/consentFriend/{requestUserId}")
    @ApiOperation("同意好友")
    public void consentFriend(@PathVariable("requestUserId") Integer requestUserId){
        friendsService.consentFriend(requestUserId);
    }

    @PostMapping("/contactFriend/{requestUserId}")
    @ApiOperation("拒绝好友")
    public void contactFriend(@PathVariable("requestUserId") Integer requestUserId){
        friendsService.contactFriend(requestUserId);
    }


}
