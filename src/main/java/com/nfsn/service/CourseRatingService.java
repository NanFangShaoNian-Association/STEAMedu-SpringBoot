package com.nfsn.service;

import com.nfsn.model.entity.CourseRating;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.CourseRatingVO;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course_rating】的数据库操作Service
* @createDate 2023-02-09 16:30:52
*/
public interface CourseRatingService extends IService<CourseRating> {
    /**
     * 通过课程id查询评价信息
     * @param courseId 课程id
     * @return 课程评价信息列表
     */
    List<CourseRatingVO> getThreeCourseRatingsByCourseId(Integer courseId);
}
