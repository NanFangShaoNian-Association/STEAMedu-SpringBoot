package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.CourseDetail;
import com.nfsn.model.vo.CourseDetailVO;
import com.nfsn.service.CourseDetailService;
import com.nfsn.mapper.CourseDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course_detail】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class CourseDetailServiceImpl extends ServiceImpl<CourseDetailMapper, CourseDetail>
    implements CourseDetailService{
    @Resource
    private CourseDetailMapper courseDetailMapper;

    @Override
    public List<CourseDetailVO> getCourseDetailByCourseId(Integer courseId) {
        return courseDetailMapper.selectCourseDetailByCourseId(courseId);
    }
}




