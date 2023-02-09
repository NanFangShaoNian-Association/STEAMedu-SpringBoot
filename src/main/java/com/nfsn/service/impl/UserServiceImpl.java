package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.User;
import com.nfsn.service.UserService;
import com.nfsn.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




