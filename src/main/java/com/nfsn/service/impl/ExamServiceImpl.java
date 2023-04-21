package com.nfsn.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.BaseInfoException;
import com.nfsn.model.dto.ExamRequest;
import com.nfsn.model.dto.ExamSubmissionRequest;
import com.nfsn.model.entity.*;
import com.nfsn.model.vo.*;
import com.nfsn.service.*;
import com.nfsn.mapper.ExamMapper;
import com.nfsn.utils.AccountHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 温格
* @description 针对表【exam】的数据库操作Service实现
* @createDate 2023-04-10 09:52:02
*/
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam>
    implements ExamService{

    @Resource
    private ExamQuestionService examQuestionService;

    @Resource
    private ExamSubmissionService examSubmissionService;

    @Resource
    private CourseService courseService;

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionOptionService questionOptionService;

    @Resource
    private StudentMessageService studentMessageService;

    @Resource
    private HomeworkService homeworkService;

    @Override
    public void addExam(ExamRequest examRequest) {
        Integer userId = AccountHolder.getUser().getUserId();
        Exam exam = new Exam();
        BeanUtils.copyProperties(examRequest,exam);
        exam.setTeacherId(userId);
        exam.setExamCreateTime(new Date());
        this.save(exam);
        Integer examId = exam.getExamId();

        //考试选项关联表操作
        List<Integer> ids = examRequest.getQuestionIds();
        List<ExamQuestion> examQuestionList = new ArrayList<>();
        for (Integer questionId : ids ){
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExamId(examId);
            examQuestion.setQuestionId(questionId);
            examQuestionList.add(examQuestion);
        }
        examQuestionService.saveBatch(examQuestionList);

    }

    @Override
    public void putExam(Integer examId) {
        List<ExamSubmission> list = examSubmissionService.list(new LambdaQueryWrapper<ExamSubmission>().eq(ExamSubmission::getExamId, examId));
        if (list.size()!=0){
            throw new BaseInfoException(ResultCode.NOTIFICATION_EXAM_HAS_PUT);
        }
        Exam exam = this.getOne(new LambdaQueryWrapper<Exam>().eq(Exam::getExamId, examId));
        Integer courseId = exam.getCourseId();

        //查询学生id
        List<Integer> studentMessageIds = courseService.getStudentMessageIds(courseId);

        List<ExamSubmission> examSubmissionList = new ArrayList<>();
        for (Integer studentMessageId : studentMessageIds){
            ExamSubmission examSubmission = new ExamSubmission();
            examSubmission.setExamId(examId);
            examSubmission.setStudentMessageId(studentMessageId);
            examSubmissionList.add(examSubmission);
        }
        examSubmissionService.saveBatch(examSubmissionList);
    }

    @Override
    public ExamVo getExamToMy(Integer examId) {
        Exam examOne = this.getOne(new LambdaQueryWrapper<Exam>().eq(Exam::getExamId, examId));

        if (examOne == null){
            throw new BaseInfoException(ResultCode.PARAM_NOT_EXISTED);
        }

        ExamVo examVo = new ExamVo();
        BeanUtils.copyProperties(examOne, examVo);
        //获取对于选项id
        List<ExamQuestion> examQuestionList = examQuestionService.list(new LambdaQueryWrapper<ExamQuestion>()
                .eq(ExamQuestion::getExamId, examId));
        List<Integer> questionIds = examQuestionList.stream().map(ExamQuestion::getQuestionId).collect(Collectors.toList());
        List<QuestionVo> questionVoList = new ArrayList<>();

        for (Integer questionId : questionIds) {
            QuestionVo QuestionVo = new QuestionVo();
            Question question = questionService.getOne(new LambdaUpdateWrapper<Question>().eq(Question::getQuestionId, questionId));
            BeanUtils.copyProperties(question, QuestionVo);
            //这里的questionOptionList就已经包含所有的选项了，那为什么还需要插入呢？
            List<QuestionOption> questionOptionList = questionOptionService.list(new LambdaQueryWrapper<QuestionOption>()
                    .eq(QuestionOption::getQuestionId, question.getQuestionId()));
            List<OptionVo> optionVoList = new ArrayList<>();
//            将题目选项列表插入题目类
            for (QuestionOption questionOptionOne : questionOptionList) {
                OptionVo optionVo = new OptionVo();
                BeanUtils.copyProperties(questionOptionOne, optionVo);
                optionVoList.add(optionVo);
            }
            QuestionVo.setOptionVoList(optionVoList);
            questionVoList.add(QuestionVo);
        }
        //将题目列表插入考试
        examVo.setQuestionVoList(questionVoList);
        return examVo;
    }

    @Override
    public Double updateExamSubmit(ExamSubmissionRequest examSubmissionRequest) {
        //将答案转化为json字符串
        String toJSONStringExamSubmitAnswer = JSON.toJSONString(examSubmissionRequest.getOptionRequestList());
        Double score = homeworkService.correct(toJSONStringExamSubmitAnswer);
        examSubmissionService.update(new LambdaUpdateWrapper<ExamSubmission>()
                .eq(ExamSubmission::getSubmissionId,examSubmissionRequest.getSubmissionId())
                .set(ExamSubmission::getSubmissionTime,new Date())
                .set(ExamSubmission::getStudentAnswer,toJSONStringExamSubmitAnswer)
                .set(ExamSubmission::getStudentScore,score));
        return score;

    }

    @Override
    public List<ExamToEvenVo> getAllExam() {

        Integer userId = AccountHolder.getUser().getUserId();
        Integer studentMessageId = studentMessageService.getStudentMessageId(userId);

        //创建返回实体
        List<ExamToEvenVo> examToEvenVoList = new ArrayList<>();

        List<ExamSubmission> examSubmissionList = examSubmissionService.list(new LambdaQueryWrapper<ExamSubmission>().eq(ExamSubmission::getStudentMessageId, studentMessageId));
        if (examSubmissionList.size()==0){
            return examToEvenVoList;
        }
        //查询考试人员id
        List<Integer> examIds = examSubmissionList.stream().map(ExamSubmission::getExamId).collect(Collectors.toList());
        List<Exam> examList = this.list(new LambdaQueryWrapper<Exam>().in(Exam::getExamId, examIds));
        List<Integer> courseIds = examList.stream().map(Exam::getCourseId).collect(Collectors.toList());
        List<Course> courseList = courseService.list(new LambdaQueryWrapper<Course>().in(Course::getCourseId, courseIds));


        for (int i = 0; i < examSubmissionList.size(); i++){
            ExamToEvenVo examToEvenVo =  new ExamToEvenVo();
            examToEvenVo.setExamId(examList.get(i).getExamId());
            examToEvenVo.setExamName(examList.get(i).getExamName());
            for (Course course : courseList) {
                if (course.getCourseId().equals(examList.get(i).getCourseId())) {
                    examToEvenVo.setCourseName(course.getCourseName());
                }
            }
            examToEvenVo.setExamStartTime(examList.get(i).getExamStartTime());
            examToEvenVo.setExamEndTime(examList.get(i).getExamEndTime());
            examToEvenVoList.add(examToEvenVo);
        }
        return examToEvenVoList;
    }
}




