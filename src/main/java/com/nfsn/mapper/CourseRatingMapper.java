package com.nfsn.mapper;

import com.nfsn.model.entity.CourseRating;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nfsn.model.vo.CourseRatingVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course_rating】的数据库操作Mapper
* @createDate 2023-02-09 16:30:52
* @Entity com.nfsn.model.entity.CourseRating
*/
public interface CourseRatingMapper extends BaseMapper<CourseRating> {

    /**
     * 通过课程id查出这门课所有评价的用户名、用户头像、打分、评价内容、评价时间
     * @param courseId 课程id
     * @return List<CourseRatingVO> 课程评价列表
     */
    @Select("select u.user_name as userName, u.user_avatar as userAvatar, cr.rating, cr.comment, cr.comment_time as commentTime from course_rating cr left join user u on cr.user_id = u.user_id where course_id = #{courseId}")
    List<CourseRatingVO> selectByCourseId(@Param("courseId") Integer courseId);

    /**
     * 通过课程id查出这门课三条评价的用户名、用户头像、打分、评价内容、评价时间
     * @param courseId 课程id
     * @return List<CourseRatingVO> 课程评价列表
     */
    @Select("select u.user_name as userName, u.user_avatar as userAvatar, cr.rating, cr.comment, cr.comment_time as commentTime from course_rating cr left join user u on cr.user_id = u.user_id where course_id = #{courseId} limit 3")
    List<CourseRatingVO> selectByCourseIdLimit3(@Param("courseId") Integer courseId);
}