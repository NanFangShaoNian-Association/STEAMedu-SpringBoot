package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.intern.InternUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.dto.ApplyFriendRequest;
import com.nfsn.model.entity.AddFriends;
import com.nfsn.model.entity.Friends;
import com.nfsn.model.entity.User;
import com.nfsn.model.vo.FriendsVO;
import com.nfsn.model.vo.NotificationInfoVO;
import com.nfsn.model.vo.PersonalInfoVO;
import com.nfsn.service.AddFriendsService;
import com.nfsn.service.FriendsService;
import com.nfsn.mapper.FriendsMapper;
import com.nfsn.service.UserService;
import com.nfsn.utils.AccountHolder;
import com.sun.org.apache.bcel.internal.generic.LALOAD;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Tuanzi
* @description 针对表【friends】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends>
    implements FriendsService{
    @Resource
    private AddFriendsService addFriendsService;
    @Resource
    private UserService userService;

    @Override
    public List<FriendsVO> listFriend() {
        Integer userId = AccountHolder.getUser().getUserId();

        List<FriendsVO> friendsVOS = new ArrayList<>();

        //获取friends中的好友
        List<Friends> friends = this.list(new LambdaQueryWrapper<Friends>().eq(Friends::getSelfUserId, userId));

        //获取其中的id
        List<Integer> friendIds = friends.stream().map(Friends::getFriendUserId).collect(Collectors.toList());
        if (friendIds.size() == 0){
            return friendsVOS;
        }
        //获取对应的用户信息
        List<User> users = userService.listByIds(friendIds);
        //实体转换
         friendsVOS = BeanUtil.copyToList(users, FriendsVO.class);
        return friendsVOS;
    }

    @Override
    public void deleteFriend(Integer delFriendId) {
        Integer userId = AccountHolder.getUser().getUserId();

        this.remove(new LambdaUpdateWrapper<Friends>()
                .eq(Friends::getSelfUserId,userId)
                .eq(Friends::getFriendUserId,delFriendId));
    }

    @Override
    public void applyFriend(ApplyFriendRequest applyFriendRequest) {
        Integer userId = AccountHolder.getUser().getUserId();

        if (checkApplyIsRepeated(userId,applyFriendRequest.getRequestedUserId())){
            return;
        }

        AddFriends addFriends = BeanUtil.copyProperties(applyFriendRequest, AddFriends.class);
        addFriends.setRequestUserId(userId);
        addFriends.setAddfriendsStatus(0);//未处理

        addFriendsService.save(addFriends);
    }

    @Override
    public List<NotificationInfoVO> getNotifications() {
        Integer userId = AccountHolder.getUser().getUserId();

        //获取被请求用户为当前用户且该请求未被处理的信息（旧）
        //获取被请求用户所有请求信息（新）
        List<AddFriends> addFriends = addFriendsService.list(new LambdaQueryWrapper<AddFriends>()
                .eq(AddFriends::getRequestedUserId, userId));

        List<NotificationInfoVO> notificationInfoVOS = BeanUtil.copyToList(addFriends, NotificationInfoVO.class);

        List<NotificationInfoVO> notificationInfoVOList = notificationInfoVOS.stream().map(notificationInfoVO -> {
            Integer requestUserId = notificationInfoVO.getRequestUserId();
            PersonalInfoVO userInfo = userService.getRequestUserInfo(requestUserId);
            notificationInfoVO.setUserName(userInfo.getUserName());
            notificationInfoVO.setUserAvatar(userInfo.getUserAvatar());
            return notificationInfoVO;
        }).collect(Collectors.toList());
        return notificationInfoVOList;
    }

    @Override
    public void consentFriend(Integer requestUserId) {
        Integer userId = AccountHolder.getUser().getUserId();

        //修改申请好友状态为1（同意）
        boolean update = addFriendsService.update(new LambdaUpdateWrapper<AddFriends>()
                .eq(AddFriends::getAddfriendsStatus, 0)
                .eq(AddFriends::getRequestUserId, requestUserId)
                .eq(AddFriends::getRequestedUserId, userId)
                .set(AddFriends::getAddfriendsStatus, 1)
        );

        if (update){
            String friendUserName = userService.getById(requestUserId).getUserName();
            String userName = userService.getById(userId).getUserName();
            List<Friends> lists = new ArrayList<>();

            Friends friends = new Friends();
            friends.setFriendUserId(requestUserId);
            friends.setSelfUserId(userId);
            friends.setRemarkName(friendUserName);

            Friends friends1 = new Friends();
            friends1.setFriendUserId(userId);
            friends1.setSelfUserId(requestUserId);
            friends1.setRemarkName(userName);

            lists.add(friends);
            lists.add(friends1);

            this.saveBatch(lists);
        }
    }

    @Override
    public void contactFriend(Integer requestUserId) {
        Integer userId = AccountHolder.getUser().getUserId();

        //修改申请好友状态为2（拒绝）
        addFriendsService.update(new LambdaUpdateWrapper<AddFriends>()
                .eq(AddFriends::getAddfriendsStatus,0)
                .eq(AddFriends::getRequestUserId,requestUserId)
                .set(AddFriends::getAddfriendsStatus,2)
                .eq(AddFriends::getRequestedUserId,userId));
    }

    @Override
    public Long ifFriend(String target) {
        Integer userId = AccountHolder.getUser().getUserId();

        return count(new LambdaQueryWrapper<Friends>().eq(Friends::getSelfUserId,userId).eq(Friends::getFriendUserId,target));

    }

//    @Override
//    public List<Object> ifFriend(Integer friendUserId, Integer userId) {
//
//        return this.listObjs(new LambdaQueryWrapper<Friends>().eq(Friends::getFriendUserId, friendUserId)
//                .eq(Friends::getSelfUserId, userId));
//
//
//    }


    //检查申请是否重复，若重复返回true，否则返回false
    public boolean checkApplyIsRepeated(Integer userId,Integer friendId) {
        long count = addFriendsService.count(new LambdaQueryWrapper<AddFriends>()
                .eq(AddFriends::getRequestedUserId, friendId).eq(AddFriends::getRequestUserId, userId)
                .or()
                .eq(AddFriends::getRequestedUserId, userId).eq(AddFriends::getRequestUserId, friendId));
        return count == 1;
    }
}




