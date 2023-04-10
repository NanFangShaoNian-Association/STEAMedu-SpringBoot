package com.nfsn.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nfsn.model.dto.CourseInfoRequest;
import com.nfsn.model.entity.Collect;
import com.nfsn.model.vo.*;
import com.nfsn.service.*;
import com.nfsn.utils.AccountHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: DiscoverController
 * @Description: 发现模块controller
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-10 22:10
 * @Version: 1.0
 **/

@Api(value = "发现模块接口",tags = "发现模块接口")
@RestController
@RequestMapping("/api")
@ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class)
})
public class DiscoverController {
    @Resource
    private CourseService courseService;

    @Resource
    private CourseTeacherRelService courseTeacherRelService;

    @Resource
    private AdvantageService advantageService;

    @Resource
    private UserService userService;

    @Resource
    private CourseRatingService courseRatingService;

    @Resource
    private CourseDetailService courseDetailService;

    @Resource
    private CollectService collectService;

    @ApiOperation("根据课程ID查询课程详细信息")
    @GetMapping("/getCourseInfoById")
    public CourseInfoRequest getWebsiteTypeList(Integer courseId) {
        // 实例化课程详情请求对象
        CourseInfoRequest courseInfoRequest = new CourseInfoRequest();

        // 通过课程ID查询课程详情
        CourseVO courseByCourseId = courseService.getCourseInfoById(courseId);
        // 通过课程分销商ID查询机构信息
        CourseInstitutionInfoVO courseInstitutionInfoVO = userService.getInstitutionByUserId(courseByCourseId.getCourseDistributor());
        // 查询课程优势
        List<AdvantageVO> courseAdvantageVOList = advantageService.selectByCourseId(courseId);
        // 查询课程评价
        List<CourseRatingVO> courseRatingVOList = courseRatingService.getThreeCourseRatingsByCourseId(courseId);
        // 查询课程详情
        List<CourseDetailVO> courseDetailVOList = courseDetailService.getCourseDetailByCourseId(courseId);
        // 查询课程对应的老师
        List<TeacherInfoVO> teacher = courseTeacherRelService.selectTeacherInfoByCourseId(courseId);

        // 将课程详情信息复制到课程详情请求对象中
        BeanUtils.copyProperties(courseByCourseId,courseInfoRequest);
        // 设置课程所属机构信息
        courseInfoRequest.setInstitution(courseInstitutionInfoVO);
        // 设置课程优势
        courseInfoRequest.setCourseAdvantage(courseAdvantageVOList);
        // 设置课程评价
        courseInfoRequest.setCourseRating(courseRatingVOList);
        // 设置课程详情
        courseInfoRequest.setCourseDetail(courseDetailVOList);
        // 设置课程对应的老师信息
        courseInfoRequest.setTeacher(teacher);

        //判断是否收藏


        Collect one = collectService.getOne(new LambdaQueryWrapper<Collect>()
                .eq(Collect::getCourseId, courseId).eq(Collect::getUserId,AccountHolder.getUser().getUserId()));
        if (one != null){
            courseInfoRequest.setIsCollect(1);
        }else {
            courseInfoRequest.setIsCollect(0);
        }

        return courseInfoRequest;
    }

    @ApiOperation("获取发现页推荐课程列表")
    @GetMapping("/getRecommendedCourseList")
    public List<RecommendedCourseVO> getRecommendedCourseList() {
        return courseService.getRecommendedCourseList();
    }

    @ApiOperation("获取发现页精选课程列表")
    @GetMapping("/getChoicenessCourseList")
    public List<ChoicenessCourseVO> getChoicenessCourseList() {
        return courseService.getChoicenessCourseList();
    }

}
