package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.mapper.ChooseMapper;
import com.nfsn.model.entity.Cart;
import com.nfsn.model.entity.Choose;
import com.nfsn.model.entity.Course;
import com.nfsn.model.entity.CourseTeacherRel;
import com.nfsn.model.vo.ChooseCourseInfoVO;
import com.nfsn.model.vo.CourseVO;
import com.nfsn.model.vo.TeacherInfoVO;
import com.nfsn.service.*;
import com.nfsn.mapper.CourseMapper;
import com.nfsn.utils.AccountHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Tuanzi
* @description 针对表【course】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private CartService cartService;

    @Resource
    private UserService userService;

    @Resource
    private CourseTeacherRelService courseTeacherRelService;

    @Override
    public CourseVO getCourseInfoById(Integer courseId) {
        return courseMapper.getCourseInfoById(courseId);
    }

    @Override
    public List<ChooseCourseInfoVO> listChooseCourseInfo() {
        Integer userId = AccountHolder.getUser().getUserId();

        //获取选课单中该用户加入选课单的课程id
        List<Cart> carts = cartService.list(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, userId));
        List<Integer> courseIds = carts.stream().map(Cart::getCourseId).collect(Collectors.toList());

        //若没有将课程加入购物车，返回null
        if (carts.size() == 0){
            return null;
        }

        //获取课程
        List<Course> courses = this.listByIds(courseIds);
        List<ChooseCourseInfoVO> chooseCourseInfoVOS = BeanUtil.copyToList(courses, ChooseCourseInfoVO.class);

        //获取教师id及其他教师信息
        List<ChooseCourseInfoVO> chooseCourseInfoVOList = chooseCourseInfoVOS.stream().map(chooseCourseInfoVO -> {
            //获取教师id，角色，姓名和头像
            TeacherInfoVO teacherInfoVO = courseTeacherRelService.getTeacherByCourseId(chooseCourseInfoVO.getCourseId());

            chooseCourseInfoVO.setTeacherRole(teacherInfoVO.getTeacherRole());
            chooseCourseInfoVO.setTeacherUserId(chooseCourseInfoVO.getCartId());
            chooseCourseInfoVO.setUserAvatar(teacherInfoVO.getUserAvatar());
            chooseCourseInfoVO.setUserName(teacherInfoVO.getUserName());
            return chooseCourseInfoVO;
        }).collect(Collectors.toList());

        return chooseCourseInfoVOList;
    }

    @Override
    public void deleteChooseCourseInfo(List<String> ids) {
        if (ids.size() == 0){
            return;
        }
        List<Integer> idList = ids.stream().distinct().map(s -> Convert.toInt(s, -1)).collect(Collectors.toList());
        cartService.removeByIds(idList);
    }

}




