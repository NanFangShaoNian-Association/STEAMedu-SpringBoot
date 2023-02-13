package com.nfsn.controller.user;

import cn.hutool.core.convert.Convert;
import com.nfsn.model.dto.CourseInfoRequest;
import com.nfsn.model.dto.StudentInfoRequest;
import com.nfsn.model.vo.PersonalInfoVO;
import com.nfsn.model.vo.StudentInfoVO;
import com.nfsn.service.StudentMessageService;
import com.nfsn.service.UserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private StudentMessageService studentMessageService;

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
    @GetMapping("/updateStudentInfo")
    public void updateStudentInfo(StudentInfoRequest studentInfoRequest) {
        studentMessageService.updateStudentInfo(studentInfoRequest);
    }

    //todo：选课单、消息通知、账号与安全
    @ApiOperation("检查更新")
    @GetMapping("/checkForUpdates")
    public void checkForUpdates() {
        //todo:点击会检查版本是否是最新版的，如果是，则会出现提示“已经是最新版本
        return;
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
