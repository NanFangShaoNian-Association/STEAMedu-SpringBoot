package com.nfsn.mapper;

import com.nfsn.model.entity.StudentMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

/**
* @author Tuanzi
* @description 针对表【student_message】的数据库操作Mapper
* @createDate 2023-02-09 16:30:53
* @Entity com.nfsn.model.entity.StudentMessage
*/
public interface StudentMessageMapper extends BaseMapper<StudentMessage> {
//    int updatePhotoUrlById(@Param("id") Integer id, @Param("photoUrl") String photoUrl);
}



