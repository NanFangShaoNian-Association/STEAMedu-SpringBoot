package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Agreement;
import com.nfsn.service.AgreementService;
import com.nfsn.mapper.AgreementMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【agreement】的数据库操作Service实现
* @createDate 2023-02-07 14:24:32
*/
@Service
public class AgreementServiceImpl extends ServiceImpl<AgreementMapper, Agreement>
    implements AgreementService{

}




