package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Course;
import com.nfsn.model.vo.CourseVO;
import com.nfsn.service.CourseService;
import com.nfsn.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Tuanzi
* @description 针对表【course】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Resource
    private CourseMapper courseMapper;

    @Override
    public CourseVO getCourseInfoById(Integer courseId) {
        return courseMapper.getCourseInfoById(courseId);
    }

}




