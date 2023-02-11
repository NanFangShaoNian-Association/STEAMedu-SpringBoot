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
    public CourseVO getCourseByCourseId(Integer courseId) {
        // 根据课程 ID 查询课程信息
        Course course = courseMapper.selectById(courseId);

        // 封装返回的 CourseVO 对象
        CourseVO courseVO = new CourseVO();
        courseVO.setCourseCover(course.getCourseCover()); // 设置课程封面
        courseVO.setCoursePrice(course.getCoursePrice()); // 设置价格
        courseVO.setCourseName(course.getCourseName()); // 设置课程名
        courseVO.setCourseStartTime(course.getCourseStartTime()); // 设置课程开始时间
        courseVO.setCourseEnrolment(course.getCourseEnrolment()); // 设置报名人数
        courseVO.setCourseSectionNumber(course.getCourseSectionNumber()); // 设置课时数
        courseVO.setCoursePosition(course.getCoursePosition()); // 设置上课地点

        return courseVO;
    }

}




