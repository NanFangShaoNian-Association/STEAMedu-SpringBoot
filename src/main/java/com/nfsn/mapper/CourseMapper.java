package com.nfsn.mapper;

import com.nfsn.model.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nfsn.model.vo.CourseVO;
import com.nfsn.model.vo.PendingPaymentCourseVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course】的数据库操作Mapper
* @createDate 2023-02-09 16:30:52
* @Entity com.nfsn.model.entity.Course
*/
public interface CourseMapper extends BaseMapper<Course> {

    @Select("SELECT course_cover, course_price, course_name, course_start_time, course_enrolment, course_section_number, course_position, course_distributor FROM course WHERE course_id = #{courseId}")
    CourseVO getCourseInfoById(@Param("courseId") Integer courseId);

    /**
     * 根据课程ID查询课程详情，包括课程名、上课开始时间、课时、上课地点和价格
     *
     * @param courseId 课程ID
     * @return 包含课程详情的CourseVO列表，每个CourseVO对应一个课程
     */
    @Select("SELECT " +
            "c.course_id, " +
            "c.course_name, " +
            "c.course_start_time, " +
            "c.course_section_number, " +
            "c.course_position, " +
            "c.course_price " +
            "FROM " +
            "course c " +
            "WHERE " +
            "c.course_id = #{courseId}")
    @Results(id = "courseVO", value = {
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "course_name", property = "courseName"),
            @Result(column = "course_start_time", property = "courseStartTime"),
            @Result(column = "course_section_number", property = "courseSectionNumber"),
            @Result(column = "course_position", property = "coursePosition"),
            @Result(column = "course_price", property = "coursePrice")
    })
    PendingPaymentCourseVO selectCourseDetailByCourseId(Integer courseId);

}