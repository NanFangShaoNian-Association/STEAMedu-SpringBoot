package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Course;
import com.nfsn.service.CourseService;
import com.nfsn.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【course】的数据库操作Service实现
* @createDate 2023-02-07 14:24:32
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

}




