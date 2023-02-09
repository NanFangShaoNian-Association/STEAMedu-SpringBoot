package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Sign;
import com.nfsn.service.SignService;
import com.nfsn.mapper.SignMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【sign】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class SignServiceImpl extends ServiceImpl<SignMapper, Sign>
    implements SignService{

}




