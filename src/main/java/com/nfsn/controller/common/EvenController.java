package com.nfsn.controller.common;

import com.nfsn.model.dto.*;
import com.nfsn.model.entity.Exam;
import com.nfsn.model.entity.Question;
import com.nfsn.model.entity.SignIn;
import com.nfsn.model.vo.*;
import com.nfsn.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-10  14:11
 */
@RestController
@RequestMapping("/even")
@Api(value = "事件相关接口",tags = "事件相关接口")
@ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class)
})public class EvenController {

    @Resource
    private SignInService signInService;

    @Resource
    private NotificationService notificationService;

    @Resource
    private QuestionService questionService;

    @Resource
    private HomeworkService homeworkService;

    @Resource
    private ExamService examService;


    //查看所有事件（可选择单个类型或者所有事件）

    @ApiOperation("查看所有事件")
    @GetMapping("/getAllEven")
    public EvenVo  getAllEven(){
        List<SignInToEvenVo> allSignIn = signInService.getAllEvenSignIn();
        List<NotificationToEvenVO> notificationToEvenVOList = notificationService.getAllNotification();
        List<HomeworkToEvenVO> homeworkToEvenVOList = homeworkService.getAllHomework();
        List<ExamToEvenVo> examToEvenVoList = examService.getAllExam();
        EvenVo evenVo = new EvenVo();
        evenVo.setSignInVoList(allSignIn);
        evenVo.setNotificationToEvenVOList(notificationToEvenVOList);
        evenVo.setHomeworkToEvenVOList(homeworkToEvenVOList);
        evenVo.setExamToEvenVoList(examToEvenVoList);
        return evenVo;
    }
    //签到模块

    @ApiOperation("创建签到")
    @PostMapping("/addSignIn")
    public void addSignIn(@RequestBody SignInRequest signInRequest){
        signInService.addSignIn(signInRequest);
    }

    @ApiOperation("删除签到")
    @DeleteMapping("/delSignIn/{signInId}")
    public void addSignIn(@PathVariable Integer signInId){
        signInService.delSignIn(signInId);
    }

    @ApiOperation("学生签到")
    @PutMapping("/updateStudentToSignIn/{signInId}")
    public SignInStatusVO updateSignInToStudent(@PathVariable Integer signInId){
        return signInService.updateSignInToStudent(signInId);
    }

    @ApiOperation("学生查看指定签到")
    @GetMapping("/getSignInToStudent/{signInId}")
    public SignInStatusVO getSignInToStudent(@PathVariable Integer signInId){
        return signInService.getSignInToStudent(signInId);
    }

    @ApiOperation("老师查看课程所有签到")
    @GetMapping("/getSignInAllToCourse/{courseId}")
    public List<SignIn> getSignInAllToCourse(@PathVariable Integer courseId){
        return  signInService.getSignInAllToCourse(courseId);
    }

    @ApiOperation("老师查看单个签到")
    @GetMapping("/getSignInToCourse/{signInId}")
    public SignInCourseStatusVO getSignInToCourse(@PathVariable Integer signInId){
        return signInService.getSignInToCourse(signInId);
    }
    //通知模块

    @ApiOperation("发布通知")
    @PostMapping("/addNotification")
    public void addNotification(@RequestBody NotificationRequest notificationRequest){
        notificationService.addNotification(notificationRequest);
    }

    @ApiOperation("删除通知")
    @DeleteMapping("/delNotification/{notificationId}")
    public void delNotification(@PathVariable Integer notificationId){
        notificationService.delNotification(notificationId);
    }

    @ApiOperation("查看(确定)指定通知")
    @PostMapping("/getNotification/{notificationId}")
    public NotificationVo getNotification(@PathVariable Integer notificationId){
        return notificationService.getNotification(notificationId);
    }

    //题目模块

    @ApiOperation("根据需求获取题目(null为所有题目，可以按类别搜索)")
    @GetMapping("/getQuestion")
    public List<Question> getQuestion(String typeName){
       return questionService.getQuestion(typeName);
    }

    @ApiOperation("根据题目id查询题目信息和选项")
    @GetMapping("/getQuestionOne/{questionId}")
    public QuestionOptionVo getQuestionToQuestionOption(@PathVariable Integer questionId){
        return questionService.getQuestionToQuestionOption(questionId);
    }

    //作业模块

    @ApiOperation("新增作业草稿（未发布）")
    @PostMapping("/addHomeworkToDraft")
    public void addHomeworkToDraft(@RequestBody HomeworkRequest homeworkRequest){
        homeworkService.addHomeworkToDraft(homeworkRequest);
    }

    @ApiOperation("发布作业")
    @PutMapping("/putHomework/{homeworkId}")
    public void putHomework(@PathVariable Integer homeworkId){
        homeworkService.putHomework(homeworkId);
    }

    @ApiOperation("修改作业的截止时间")
    @PutMapping("/updateHomeworkTime/{homeworkId}")
    public void updateHomeworkTime(@PathVariable Integer homeworkId ,@RequestBody Date newTime){
        homeworkService.updateHomeworkTime(homeworkId,newTime);
    }

    @ApiOperation("获取指定作业数据")
    @GetMapping("/getHomeworkToMy/{homeworkId}")
    public HomeworkVO getHomeworkToMy(@PathVariable Integer homeworkId){
        return homeworkService.getHomeworkToMy(homeworkId);
    }

    @ApiOperation("提交作业")
    @PutMapping("/updateHomeworkSubmit")
    public Double updateHomeworkSubmit(@RequestBody HomeworkSubmitRequest homeworkSubmitRequest){
        return homeworkService.updateHomeworkSubmit(homeworkSubmitRequest);
    }

    //考试模块

    @ApiOperation("新增考试")
    @PostMapping("/addExam")
    public void addExam(@RequestBody ExamRequest examRequest){
        examService.addExam(examRequest);
    }

    @ApiOperation("发布考试")
    @PutMapping("/putExam/{examId}")
    public void putExam(@PathVariable Integer examId){
        examService.putExam(examId);
    }

    @ApiOperation("获取指定考试数据")
    @GetMapping("/getExamToMy/{examId}")
    public ExamVo getExamToMy(@PathVariable Integer examId){
        return examService.getExamToMy(examId);
    }

    @ApiOperation("提交考试")
    @PutMapping("/updateExamSubmit")
    public Double updateExamSubmit(@RequestBody ExamSubmissionRequest examSubmissionRequest){
        return examService.updateExamSubmit(examSubmissionRequest);
    }


}
