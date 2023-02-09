package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.CourseRating;
import com.nfsn.service.CourseRatingService;
import com.nfsn.mapper.CourseRatingMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【course_rating】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class CourseRatingServiceImpl extends ServiceImpl<CourseRatingMapper, CourseRating>
    implements CourseRatingService{

}




