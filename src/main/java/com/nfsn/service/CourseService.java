package com.nfsn.service;

import com.nfsn.model.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.*;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course】的数据库操作Service
* @createDate 2023-02-09 16:30:52
*/
public interface CourseService extends IService<Course> {
    CourseVO getCourseInfoById(Integer courseId);

//    void deleteChooseCourseInfo(List<String> ids);

    List<RecommendedCourseVO> getRecommendedCourseList();

    List<ChoicenessCourseVO> getChoicenessCourseList();

    PendingPaymentCourseVO getCourseDetailById(Integer courseId);

    /**
     * 获得我的所有课程的信息
     * @return
     */
    List<CourseMyListVO> listMyCourses();

    /**
     * 根据课程id获取所有学生id
     * @param courseId
     * @return
     */
    List<Integer> getStudentMessageIds(Integer courseId);
}