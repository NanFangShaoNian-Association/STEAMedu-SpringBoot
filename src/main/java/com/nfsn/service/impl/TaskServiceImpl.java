package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Task;
import com.nfsn.service.TaskService;
import com.nfsn.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【task】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{

}




