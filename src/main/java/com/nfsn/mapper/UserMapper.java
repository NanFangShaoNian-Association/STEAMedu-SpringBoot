package com.nfsn.mapper;

import com.nfsn.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nfsn.model.vo.CourseInstitutionInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author Tuanzi
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-02-09 16:30:53
* @Entity com.nfsn.model.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT user_avatar, user_name, user_introduction" +
            " FROM user" +
            " WHERE user_id = #{userId}")
    CourseInstitutionInfoVO getInstitutionByUserId(@Param("userId") Integer userId);

}




