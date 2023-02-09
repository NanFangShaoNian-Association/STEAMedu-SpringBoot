package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Subject;
import com.nfsn.service.SubjectService;
import com.nfsn.mapper.SubjectMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【subject】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
    implements SubjectService{

}




