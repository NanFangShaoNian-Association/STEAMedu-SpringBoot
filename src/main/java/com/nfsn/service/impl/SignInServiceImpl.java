package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.SignIn;
import com.nfsn.service.SignInService;
import com.nfsn.mapper.SignInMapper;
import org.springframework.stereotype.Service;

/**
* @author 温格
* @description 针对表【sign_in(签到表)】的数据库操作Service实现
* @createDate 2023-04-10 09:36:23
*/
@Service
public class SignInServiceImpl extends ServiceImpl<SignInMapper, SignIn>
    implements SignInService{

}




