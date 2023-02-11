package com.nfsn.service;

import com.nfsn.mapper.AdvantageMapper;
import com.nfsn.model.entity.Advantage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.CourseAdvantageVO;

import java.util.List;


/**
* @author Tuanzi
* @description 针对表【advantage】的数据库操作Service
* @createDate 2023-02-09 16:30:52
*/
public interface AdvantageService extends IService<Advantage> {
    List<CourseAdvantageVO> selectByCourseId(Integer courseId);
}
