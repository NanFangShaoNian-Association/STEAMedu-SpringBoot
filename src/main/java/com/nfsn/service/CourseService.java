package com.nfsn.service;

import com.nfsn.model.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.ChooseCourseInfoVO;
import com.nfsn.model.vo.CourseVO;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course】的数据库操作Service
* @createDate 2023-02-09 16:30:52
*/
public interface CourseService extends IService<Course> {
    CourseVO getCourseInfoById(Integer courseId);

    List<ChooseCourseInfoVO> listChooseCourseInfo();

    void deleteChooseCourseInfo(List<String> ids);
}
