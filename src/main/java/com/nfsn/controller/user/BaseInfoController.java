package com.nfsn.controller.user;

import com.nfsn.anno.NoNeedLogin;

import com.nfsn.common.Result;
import com.nfsn.constants.ResultCode;
import com.nfsn.model.entity.User;
import com.nfsn.config.AppUpdateConfig;
import com.nfsn.model.vo.AccountInfoVO;
import com.nfsn.model.dto.StudentInfoRequest;
import com.nfsn.model.vo.*;
import com.nfsn.service.*;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private CartService cartService;

    @Resource
    private FriendsService friendsService;

    @Resource
    private StudentMessageService studentMessageService;

    @Resource
    private CourseService courseService;

    @Resource
    private AppUpdateConfig appUpdateConfig;

    @ApiOperation("获取个人信息")
    @GetMapping("/getPersonalInfo")
    public Result getPersonalInfo() {
        PersonalInfoVO userInfo = userService.getUserInfo();
        User userFull = userService.getUserFull();
        if (userFull == null){
            return new  Result(ResultCode.USER_NOT_FULL,userInfo);
        }
        return new Result(ResultCode.SUCCESS,userInfo);
    }

    @ApiOperation("获取学生信息")
    @GetMapping("/getStudentInfo")
    public StudentInfoVO getStudentInfo() {
        return studentMessageService.getStudentInfo();
    }

    @ApiOperation("新增、更新学生信息")
    @PostMapping("/updateStudentInfo")
    public void updateStudentInfo(@RequestBody StudentInfoRequest studentInfoRequest) {
        studentMessageService.updateStudentInfo(studentInfoRequest);
    }

    @ApiOperation(value = "上传头像", notes = "上传用户头像。本接口需要使用POSTMAN测试，swagger无法测试。")
    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        return userService.uploadAvatar(file);
    }

    @ApiOperation(value = "上传用户真实照片", notes = "上传用户真实照片。本接口需要使用POSTMAN测试，swagger无法测试。")
    @PostMapping("/uploadPhoto")
    public String uploadPhoto(@RequestParam("Photo") MultipartFile file) {

        if (file.isEmpty()) {
            return "文件不能为空";
        }
        return userService.uploadPhoto(file);
    }

    @ApiOperation("获取选课单课程列表")
    @GetMapping("/getCartList")
    public List<CartVO> listCourseInfo() {
        return cartService.getCartList();
    }

    @ApiOperation("删除选课单课程，需要删除的id列表")
    @DeleteMapping("/deleteCartList")
    public void deleteCourseInfo(List<String> ids) {
        cartService.deleteCartList(ids);
    }

    @ApiOperation("账号与安全-手机、微信、QQ和邮箱")
    @GetMapping("/getAccountInfo")
    public AccountInfoVO getAccountInfo() {
        return userService.getAccountInfo();
    }

    /**
     * 检查更新
     *
     * @param currentVersion 当前的版本号
     * @return
     */
    @ApiOperation("检查更新")
    @GetMapping("/checkForUpdates")
    public AppUpdateInfoVO checkForUpdates(@RequestParam("currentVersion") String currentVersion) {
        // 创建一个新的 AppUpdateInfoVO 实例
        AppUpdateInfoVO appUpdateInfoVO = new AppUpdateInfoVO();
        // 从配置文件中获取最新版本号，并将其设置到 appUpdateInfoVO 实例的 latestVersion 属性中
        String latestVersion = appUpdateConfig.getLatestVersion();
        appUpdateInfoVO.setLatestVersion(latestVersion);
        // 从配置文件中获取下载链接，并将其设置到 appUpdateInfoVO 实例的 downloadUrl 属性中
        appUpdateInfoVO.setDownloadUrl(appUpdateConfig.getDownloadUrl());
        // 返回填充了最新版本号和下载链接的 appUpdateInfoVO 实例
        return appUpdateInfoVO;
    }

    @ApiOperation("获取消息通知")
    @GetMapping("/getNotifications")
    public List<NotificationInfoVO> getNotifications() {
        return friendsService.getNotifications();
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
