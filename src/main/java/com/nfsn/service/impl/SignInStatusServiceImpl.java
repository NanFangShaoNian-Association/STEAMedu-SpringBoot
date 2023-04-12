package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.SignInStatus;
import com.nfsn.service.SignInStatusService;
import com.nfsn.mapper.SignInStatusMapper;
import org.springframework.stereotype.Service;

/**
* @author 温格
* @description 针对表【sign_in_status(签到状态表)】的数据库操作Service实现
* @createDate 2023-04-10 09:48:50
*/
@Service
public class SignInStatusServiceImpl extends ServiceImpl<SignInStatusMapper, SignInStatus>
    implements SignInStatusService{

}




