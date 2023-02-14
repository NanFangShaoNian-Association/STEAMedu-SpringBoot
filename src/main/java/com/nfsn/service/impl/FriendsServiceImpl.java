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
import com.nfsn.service.AddFriendsService;
import com.nfsn.service.FriendsService;
import com.nfsn.mapper.FriendsMapper;
import com.nfsn.service.UserService;
import com.nfsn.utils.AccountHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

        //获取friends中的好友
        List<Friends> friends = this.list(new LambdaQueryWrapper<Friends>().eq(Friends::getSelfUserId, userId));
        //获取其中的id
        List<Integer> friendIds = friends.stream().map(Friends::getFriendUserId).collect(Collectors.toList());
        if (friendIds.size() == 0){
            return null;
        }
        //获取对应的用户信息
        List<User> users = userService.listByIds(friendIds);
        //实体转换
        List<FriendsVO> friendsVOS = BeanUtil.copyToList(users, FriendsVO.class);
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
        addFriends.setAddfriendsStatus(0);//未处理

        addFriendsService.save(addFriends);
    }

    //检查申请是否重复，若重复返回true，否则返回false
    public boolean checkApplyIsRepeated(Integer userId,Integer friendId) {
        long count = addFriendsService.count(new LambdaQueryWrapper<AddFriends>()
                .eq(AddFriends::getRequestedUserId, friendId).eq(AddFriends::getRequestUserId, userId)
                .or()
                .eq(AddFriends::getRequestedUserId, userId).eq(AddFriends::getRequestUserId, friendId));
        return count == 1;
    }
}




