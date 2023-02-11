package com.nfsn.service;

import com.nfsn.model.entity.CourseDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.CourseDetailVO;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course_detail】的数据库操作Service
* @createDate 2023-02-09 16:30:52
*/
public interface CourseDetailService extends IService<CourseDetail> {
    List<CourseDetailVO> getCourseDetailByCourseId(Integer courseId);
}
