package com.nfsn.mapper;

import com.nfsn.model.entity.Advantage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nfsn.model.vo.CourseAdvantageVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【advantage】的数据库操作Mapper
* @createDate 2023-02-09 16:30:52
* @Entity com.nfsn.model.entity.Advantage
*/
public interface AdvantageMapper extends BaseMapper<Advantage> {

    @Select("SELECT a.course_advantage, a.course_advantage_introduction " +
            "FROM advantage a " +
            "LEFT JOIN course_advantage ca ON ca.course_advantage_id = a.course_advantage_id " +
            "WHERE ca.course_id = #{courseId}")
    List<CourseAdvantageVO> selectByCourseId(@Param("courseId") Integer courseId);

}




