package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.TaskSubject;
import com.nfsn.service.TaskSubjectService;
import com.nfsn.mapper.TaskSubjectMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【task_subject】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class TaskSubjectServiceImpl extends ServiceImpl<TaskSubjectMapper, TaskSubject>
    implements TaskSubjectService{

}




