package com.nfsn.mapper;

import com.nfsn.model.entity.HomeworkSubmit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
* @author 温格
* @description 针对表【homework_submit(作业提交表)】的数据库操作Mapper
* @createDate 2023-04-10 09:33:52
* @Entity com.nfsn.model.entity.HomeworkSubmit
*/
public interface HomeworkSubmitMapper extends BaseMapper<HomeworkSubmit> {

    @ResultMap("mybatis-plus_HomeworkSubmit")
    @Select("select * FROM homework_submit where homework_submit_id = #{homeworkSubmitId}")
    HomeworkSubmit selectByHomeworkId(Integer homeworkSubmitId);

}




