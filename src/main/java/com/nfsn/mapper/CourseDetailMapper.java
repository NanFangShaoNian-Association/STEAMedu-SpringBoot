package com.nfsn.mapper;

import com.nfsn.model.entity.CourseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nfsn.model.vo.CourseDetailVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course_detail】的数据库操作Mapper
* @createDate 2023-02-09 16:30:52
* @Entity com.nfsn.model.entity.CourseDetail
*/
public interface CourseDetailMapper extends BaseMapper<CourseDetail> {

    @Select("SELECT picture, display_order FROM course_detail WHERE course_id = #{courseId}")
    List<CourseDetailVO> selectCourseDetailByCourseId(Integer courseId);
}




