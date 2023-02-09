package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.CourseStudent;
import com.nfsn.service.CourseStudentService;
import com.nfsn.mapper.CourseStudentMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【course_student】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class CourseStudentServiceImpl extends ServiceImpl<CourseStudentMapper, CourseStudent>
    implements CourseStudentService{

}




