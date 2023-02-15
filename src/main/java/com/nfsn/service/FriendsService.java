package com.nfsn.service;

import com.nfsn.model.dto.ApplyFriendRequest;
import com.nfsn.model.entity.Friends;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.FriendsVO;
import com.nfsn.model.vo.NotificationInfoVO;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【friends】的数据库操作Service
* @createDate 2023-02-09 16:30:53
*/
public interface FriendsService extends IService<Friends> {

    List<FriendsVO> listFriend();

    void deleteFriend(Integer delFriendId);

    void applyFriend(ApplyFriendRequest applyFriendRequest);

    List<NotificationInfoVO> getNotifications();

}
