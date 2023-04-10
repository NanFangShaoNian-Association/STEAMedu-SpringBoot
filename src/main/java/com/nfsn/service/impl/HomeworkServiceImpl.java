package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Homework;
import com.nfsn.service.HomeworkService;
import com.nfsn.mapper.HomeworkMapper;
import org.springframework.stereotype.Service;

/**
* @author 温格
* @description 针对表【homework(作业表)】的数据库操作Service实现
* @createDate 2023-04-10 09:18:59
*/
@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework>
    implements HomeworkService{

}




