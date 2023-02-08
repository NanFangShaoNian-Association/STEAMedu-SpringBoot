package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Friends;
import com.nfsn.service.FriendsService;
import com.nfsn.mapper.FriendsMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【friends】的数据库操作Service实现
* @createDate 2023-02-07 14:24:32
*/
@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends>
    implements FriendsService{

}




