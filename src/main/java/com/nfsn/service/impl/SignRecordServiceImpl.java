package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.SignRecord;
import com.nfsn.service.SignRecordService;
import com.nfsn.mapper.SignRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【sign_record】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class SignRecordServiceImpl extends ServiceImpl<SignRecordMapper, SignRecord>
    implements SignRecordService{

}




