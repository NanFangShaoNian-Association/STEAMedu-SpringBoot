package com.nfsn.service;

import com.nfsn.model.entity.CourseTeacherRel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.TeacherInfoVO;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course_teacher_rel】的数据库操作Service
* @createDate 2023-02-09 16:30:53
*/
public interface CourseTeacherRelService extends IService<CourseTeacherRel> {
    List<TeacherInfoVO> selectTeacherInfoByCourseId(Integer courseId);

    TeacherInfoVO getTeacherByCourseId(Integer courseId);
}
