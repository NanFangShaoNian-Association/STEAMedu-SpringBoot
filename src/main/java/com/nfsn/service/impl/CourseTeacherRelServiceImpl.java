package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.CourseTeacherRel;
import com.nfsn.model.vo.TeacherInfoVO;
import com.nfsn.service.CourseTeacherRelService;
import com.nfsn.mapper.CourseTeacherRelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course_teacher_rel】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class CourseTeacherRelServiceImpl extends ServiceImpl<CourseTeacherRelMapper, CourseTeacherRel>
    implements CourseTeacherRelService{

    @Resource
    private CourseTeacherRelMapper courseTeacherRelMapper;

    /**
     * 通过课程ID查询老师信息
     *
     * @param courseId 课程ID
     * @return 老师信息列表
     */
    public List<TeacherInfoVO> selectTeacherInfoByCourseId(Integer courseId) {
        return courseTeacherRelMapper.selectTeacherInfoByCourseId(courseId);
    }

}




