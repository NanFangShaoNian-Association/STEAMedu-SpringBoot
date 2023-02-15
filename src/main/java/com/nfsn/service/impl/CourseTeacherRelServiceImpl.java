package com.nfsn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Cart;
import com.nfsn.model.entity.CourseTeacherRel;
import com.nfsn.model.entity.User;
import com.nfsn.model.vo.TeacherInfoVO;
import com.nfsn.service.CourseTeacherRelService;
import com.nfsn.mapper.CourseTeacherRelMapper;
import com.nfsn.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
* @author Tuanzi
* @description 针对表【course_teacher_rel】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class CourseTeacherRelServiceImpl extends ServiceImpl<CourseTeacherRelMapper, CourseTeacherRel>
    implements CourseTeacherRelService{

    @Resource
    private CourseTeacherRelMapper courseTeacherRelMapper;

    @Resource
    private UserService userService;

    /**
     * 通过课程ID查询老师信息
     *
     * @param courseId 课程ID
     * @return 老师信息列表
     */
    public List<TeacherInfoVO> selectTeacherInfoByCourseId(Integer courseId) {
        return courseTeacherRelMapper.selectTeacherInfoByCourseId(courseId);
    }

    @Override
    public TeacherInfoVO getTeacherByCourseId(Integer courseId) {
        CourseTeacherRel teacherRel = this.getOne(new LambdaQueryWrapper<CourseTeacherRel>()
                .eq(CourseTeacherRel::getCourseId, courseId)
                .eq(CourseTeacherRel::getTeacherRole,0));//授课老师

        //获取角色需要为老师的信息
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserId, teacherRel.getTeacherUserId()));
//                .eq(User::getUserRole, 2));

        TeacherInfoVO teacherInfoVO = new TeacherInfoVO();
        teacherInfoVO.setTeacherRole(teacherRel.getTeacherRole());
        teacherInfoVO.setUserAvatar(user.getUserAvatar());
        teacherInfoVO.setUserName(user.getUserName());
        return teacherInfoVO;
    }

}




