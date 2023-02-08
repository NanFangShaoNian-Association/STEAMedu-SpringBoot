package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.StudentMessage;
import com.nfsn.service.StudentMessageService;
import com.nfsn.mapper.StudentMessageMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【student_message】的数据库操作Service实现
* @createDate 2023-02-07 14:24:32
*/
@Service
public class StudentMessageServiceImpl extends ServiceImpl<StudentMessageMapper, StudentMessage>
    implements StudentMessageService{

}




