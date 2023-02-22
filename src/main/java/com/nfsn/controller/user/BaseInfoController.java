package com.nfsn.controller.user;

import cn.hutool.core.convert.Convert;
import com.nfsn.model.vo.AccountInfoVO;
import com.nfsn.model.dto.StudentInfoRequest;
import com.nfsn.model.vo.*;
import com.nfsn.service.CourseService;
import com.nfsn.service.FriendsService;
import com.nfsn.service.StudentMessageService;
import com.nfsn.service.UserService;
import io.swagger.annotations.*;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: BaseInfoController
 * @Author: 团子tz
 * @CreateTime: 2023/02/12 17:17
 * @Description: 账号基本信息Controller
 */
@Api("账号基本信息模块")
@RestController
@RequestMapping("/users")
@ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class)
})
public class BaseInfoController {

    @Resource
    private UserService userService;

    @Resource
    private FriendsService friendsService;

    @Resource
    private StudentMessageService studentMessageService;

    @Resource
    private CourseService courseService;

    @ApiOperation("获取个人信息")
    @GetMapping("/getPersonalInfo")
    public PersonalInfoVO getPersonalInfo() {
        return userService.getUserInfo();
    }

    @ApiOperation("获取学生信息")
    @GetMapping("/getStudentInfo")
    public StudentInfoVO getStudentInfo(String studentId) {
        Integer studentInfoId = Convert.toInt(studentId, null);
        return studentMessageService.getStudentInfo(studentInfoId);
    }

    @ApiOperation("新增、更新学生信息")
    @PostMapping("/updateStudentInfo")
    public void updateStudentInfo(@RequestBody StudentInfoRequest studentInfoRequest) {
        studentMessageService.updateStudentInfo(studentInfoRequest);
    }

    @ApiOperation("获取选课单课程列表")
    @GetMapping("/listCourseInfo")
    public List<ChooseCourseInfoVO> listCourseInfo() {
        return courseService.listChooseCourseInfo();
    }

    @ApiOperation("删除选课单课程，需要删除的id列表")
    @DeleteMapping("/deleteCourseInfo")
    public void deleteCourseInfo(List<String> ids) {
        courseService.deleteChooseCourseInfo(ids);
    }

    @ApiOperation("账号与安全-手机、微信、QQ和邮箱")
    @GetMapping("/getAccountInfo")
    public AccountInfoVO getAccountInfo() {
        return userService.getAccountInfo();
    }

    @ApiOperation("检查更新")
    @GetMapping("/checkForUpdates")
    public void checkForUpdates() {
        //todo:点击会检查版本是否是最新版的，如果是，则会出现提示“已经是最新版本
        return;
    }

    @ApiOperation("获取消息通知")
    @GetMapping("/getNotifications")
    public List<NotificationInfoVO> getNotifications() {
        return friendsService.getNotifications();
//        return null;
    }

    @ApiOperation("帮助与反馈")
    @GetMapping("/helpAndFeedback")
    public String helpAndFeedback() {
        return "此功能正在开发中";
    }

    @ApiOperation("给我们好评")
    @GetMapping("/giveEvaluation")
    public String giveEvaluation() {
        return "此功能正在开发中";
    }
}
