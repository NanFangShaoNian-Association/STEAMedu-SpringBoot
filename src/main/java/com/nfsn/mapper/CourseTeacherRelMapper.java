package com.nfsn.mapper;

import com.nfsn.model.entity.CourseTeacherRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nfsn.model.vo.TeacherInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course_teacher_rel】的数据库操作Mapper
* @createDate 2023-02-09 16:30:52
* @Entity com.nfsn.model.entity.CourseTeacherRel
*/
public interface CourseTeacherRelMapper extends BaseMapper<CourseTeacherRel> {

    @Select("SELECT ctr.teacher_role, u.user_name, u.user_avatar " +
            "FROM course_teacher_rel ctr " +
            "LEFT JOIN user u " +
            "ON ctr.teacher_user_id = u.user_id " +
            "WHERE ctr.course_id = #{courseId}")
    List<TeacherInfoVO> selectTeacherInfoByCourseId(@Param("courseId") Integer courseId);

}




