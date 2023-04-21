package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.common.Result;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.BaseInfoException;
import com.nfsn.mapper.CollectMapper;
import com.nfsn.model.entity.Collect;
import com.nfsn.model.entity.Course;
import com.nfsn.model.vo.CollectToCourseInfoVo;
import com.nfsn.model.vo.TeacherInfoVO;
import com.nfsn.service.CollectService;
import com.nfsn.service.CourseService;
import com.nfsn.service.CourseTeacherRelService;
import com.nfsn.utils.AccountHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author snail
 * @description 针对表【Collect】的数据库操作Service实现
 * @create 2023-03-22  9:13
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Resource
    private CourseService courseService;

    @Resource
    private CourseTeacherRelService courseTeacherRelService;


    @Override
    public List<CollectToCourseInfoVo> getCollectList() {
        Integer userId = AccountHolder.getUser().getUserId();

        List<Collect> collectList = this.list(new LambdaQueryWrapper<Collect>()
                .eq(Collect::getUserId, userId));

        if (collectList.size() ==0){
            List<CollectToCourseInfoVo> CollectToCourseInfoVo = new ArrayList<>();
            return CollectToCourseInfoVo;
//            throw new BaseInfoException(ResultCode.PARAM_IS_BLANK);
        }

        List<Integer> ids = collectList.stream().map(Collect::getCourseId)
                .collect(Collectors.toList());
        List<Course> courseList = courseService.list(new LambdaQueryWrapper<Course>()
                .in(Course::getCourseId, ids));

        return courseList.stream().map(courseOne ->{
            Course course1 = courseService.getById(courseOne.getCourseId());
            List<TeacherInfoVO> teacherInfoVOS = courseTeacherRelService.selectTeacherInfoByCourseId(course1.getCourseId());
            CollectToCourseInfoVo courseInfoVo = BeanUtil.copyProperties(course1,CollectToCourseInfoVo.class);
            courseInfoVo.setTeachers(teacherInfoVOS);
            return courseInfoVo;
        }).collect(Collectors.toList());

    }

    @Override
    public void addCourseToCollect(Integer courseId) {
        Integer userId = AccountHolder.getUser().getUserId();

        Collect one = this.getOne(new LambdaQueryWrapper<Collect>()
                .eq(Collect::getCourseId, courseId)
                .eq(Collect::getUserId, userId));
        if (one == null){
            //封装课程收藏信息
            Collect collect = new Collect();
            collect.setUserId(userId);
            collect.setCourseId(courseId);
            this.save(collect);
        }else {
            throw new BaseInfoException(ResultCode.PARAM_HAS_EXISTED);
        }
    }

    @Override
    public void delCourseToCollect(Integer courseId) {
        Integer userId = AccountHolder.getUser().getUserId();

        boolean remove = this.remove(new LambdaQueryWrapper<Collect>()
                .eq(Collect::getUserId, userId)
                .eq(Collect::getCourseId, courseId));

        //判断
        if (!remove){
            throw new BaseInfoException(ResultCode.PARAM_IS_INVALID);
        }
    }
}
