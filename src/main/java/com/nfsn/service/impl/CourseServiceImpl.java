package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.mapper.CourseTeacherRelMapper;
import com.nfsn.mapper.UserMapper;
import com.nfsn.model.entity.*;
import com.nfsn.model.vo.*;
import com.nfsn.service.*;
import com.nfsn.mapper.CourseMapper;
import com.nfsn.utils.AccountHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tuanzi
 * @description 针对表【course】的数据库操作Service实现
 * @createDate 2023-02-09 16:30:52
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private CourseTeacherRelService courseTeacherRelService;

    @Resource
    private StudentMessageService studentMessageService;

    @Resource
    private CourseStudentService courseStudentService;

    @Override
    public CourseVO getCourseInfoById(Integer courseId) {
        return courseMapper.getCourseInfoById(courseId);
    }

    @Override
    public List<RecommendedCourseVO> getRecommendedCourseList() {
        // 获取推荐课程列表
        List<Course> recommendedCourseList = courseMapper.selectList(
                new QueryWrapper<Course>()
                        .eq("course_type", 1) // 1表示推荐
                        .eq("course_delete_status", 0) // 0表示未删除
                        .orderByDesc("entering_time") // 按照信息录入时间倒序排序
                        .last("limit 10") // 取前10条数据
        );

        // 组装推荐课程列表
        List<RecommendedCourseVO> recommendedCourseVOList = new ArrayList<>();
        for (Course course : recommendedCourseList) {
            RecommendedCourseVO recommendedCourseVO = new RecommendedCourseVO();
            recommendedCourseVO.setCourseId(course.getCourseId());
            recommendedCourseVO.setCourseName(course.getCourseName());
            recommendedCourseVO.setCoursePrice(course.getCoursePrice());
            recommendedCourseVO.setDistance("10km"); // TODO:暂时这么写，方便前端接接口，后面再修改
            recommendedCourseVO.setCoursePosition(course.getCoursePosition());
            recommendedCourseVO.setCourseStartTime(course.getCourseStartTime());

            // 设置老师列表
            List<TeacherInfoVO> teacherInfoVOList = courseTeacherRelService.selectTeacherInfoByCourseId(course.getCourseId());

            recommendedCourseVO.setTeacherInfoVOList(teacherInfoVOList);
            recommendedCourseVOList.add(recommendedCourseVO);
        }
        return recommendedCourseVOList;
    }

    @Override
    public List<ChoicenessCourseVO> getChoicenessCourseList() {
        // 获取推荐课程列表
        List<Course> choicenessCourseList = courseMapper.selectList(
                new QueryWrapper<Course>()
                        .eq("course_type", 0) // 0表示精选推荐
                        .eq("course_delete_status", 0) // 0表示未删除
                        .orderByDesc("entering_time") // 按照信息录入时间倒序排序
                        .last("limit 10") // 取前10条数据
        );

        // 组装推荐课程列表
        List<ChoicenessCourseVO> choicenessCourseVOList = new ArrayList<>();
        for (Course course : choicenessCourseList) {
            ChoicenessCourseVO choicenessCourseVO = new ChoicenessCourseVO();
            choicenessCourseVO.setCourseId(course.getCourseId());
            choicenessCourseVO.setCourseName(course.getCourseName());
            choicenessCourseVO.setCoursePrice(course.getCoursePrice());
            choicenessCourseVO.setCourseTextIntroduction(course.getCourseTextIntroduction());

            choicenessCourseVOList.add(choicenessCourseVO);
        }
        return choicenessCourseVOList;
    }

    /**
     * 根据课程id查找待支付课程详情
     *
     * @param courseId 课程ID
     * @return
     */
    @Override
    public PendingPaymentCourseVO getCourseDetailById(Integer courseId) {
        // 查询课程信息
        PendingPaymentCourseVO pendingPaymentCourseVO = this.baseMapper.selectCourseDetailByCourseId(courseId);
        // 查询并设置教师信息列表
        pendingPaymentCourseVO.setTeachers(courseTeacherRelService.selectTeacherInfoByCourseId(courseId));
        // 返回包含所有相关老师信息的待支付课程详情
        return pendingPaymentCourseVO;
    }

    @Override
    public List<CourseMyListVO> listMyCourses() {
        // 获取当前用户ID
        Integer userId = AccountHolder.getUser().getUserId();

        Integer studentMessageId = studentMessageService.getOne(new LambdaQueryWrapper<StudentMessage>()
                .eq(StudentMessage::getUserId, userId)).getStudentMessageId();

        List<CourseStudent> courseStudentList = courseStudentService.list(new LambdaQueryWrapper<CourseStudent>()
                .eq(CourseStudent::getStudentMessageId, studentMessageId));

        return courseStudentList.stream().map(courseOne ->{
            //根据课程id获取课程信息
            Course course = this.getById(courseOne.getCourseId());
            //根据课程id获取教师信息列表
            List<TeacherInfoVO> teacherInfoVOList = courseTeacherRelService.selectTeacherInfoByCourseId(course.getCourseId());
            //将课程信息转化为对象
            CourseMyListVO courseMyListVO = BeanUtil.copyProperties(course,CourseMyListVO.class);
            //设置对象的教师列表
            courseMyListVO.setTeachers(teacherInfoVOList);
            //返回课程对象
            return courseMyListVO;

        }).collect(Collectors.toList());

    }

}




