package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.CourseRating;
import com.nfsn.model.vo.CourseRatingVO;
import com.nfsn.service.CourseRatingService;
import com.nfsn.mapper.CourseRatingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course_rating】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class CourseRatingServiceImpl extends ServiceImpl<CourseRatingMapper, CourseRating>
    implements CourseRatingService{

    @Resource
    private CourseRatingMapper courseRatingMapper;

    /**
     * 通过课程id查出这门课的所有评价的用户id、打分、评价内容、评价时间
     * @param courseId 课程id
     * @return List<CourseRatingVO> 课程评价列表
     */
    public List<CourseRatingVO> getThreeCourseRatingsByCourseId(Integer courseId){
        return courseRatingMapper.selectByCourseIdLimit3(courseId);
    }
}