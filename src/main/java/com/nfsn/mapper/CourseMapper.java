package com.nfsn.mapper;

import com.nfsn.model.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nfsn.model.vo.CourseVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author Tuanzi
* @description 针对表【course】的数据库操作Mapper
* @createDate 2023-02-09 16:30:52
* @Entity com.nfsn.model.entity.Course
*/
public interface CourseMapper extends BaseMapper<Course> {

    @Select("SELECT course_cover, course_price, course_name, course_start_time, course_enrolment, course_section_number, course_position, course_distributor FROM course WHERE course_id = #{courseId}")
    CourseVO getCourseInfoById(@Param("courseId") Integer courseId);

}




