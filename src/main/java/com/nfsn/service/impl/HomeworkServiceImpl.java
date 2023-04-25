package com.nfsn.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.BaseInfoException;
import com.nfsn.mapper.HomeworkSubmitMapper;
import com.nfsn.model.dto.HomeworkRequest;
import com.nfsn.model.dto.HomeworkSubmitRequest;
import com.nfsn.model.dto.OptionRequest;
import com.nfsn.model.entity.*;
import com.nfsn.model.vo.QuestionVo;
import com.nfsn.model.vo.HomeworkToEvenVO;
import com.nfsn.model.vo.HomeworkVO;
import com.nfsn.model.vo.OptionVo;
import com.nfsn.service.*;
import com.nfsn.mapper.HomeworkMapper;
import com.nfsn.utils.AccountHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 温格
* @description 针对表【homework(作业表)】的数据库操作Service实现
* @createDate 2023-04-10 09:18:59
*/
@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework>
    implements HomeworkService{

    @Resource
    private HomeworkQuestionService homeworkQuestionService;

    @Resource
    private HomeworkSubmitService homeworkSubmitService;

    @Resource
    private CourseService courseService;

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionOptionService questionOptionService;

    @Resource
    private StudentMessageService studentMessageService;

    @Resource
    private HomeworkSubmitMapper homeworkSubmitMapper;

    @Override
    public void addHomeworkToDraft(HomeworkRequest homeworkRequest) {
        Integer userId = AccountHolder.getUser().getUserId();
        Homework homework  = new Homework();
        BeanUtils.copyProperties(homeworkRequest,homework);
        homework.setCreateTime(new Date());
        homework.setStatus(0);
        homework.setCreatorId(userId);
        this.save(homework);
        Integer homeworkId = homework.getHomeworkId();

        //作业选项关联表操作
        List<Integer> ids = homeworkRequest.getQuestionIds();
        List<HomeworkQuestion> homeworkQuestionList = new ArrayList<>();
        for (Integer questionId : ids ){
            HomeworkQuestion homeworkQuestion = new HomeworkQuestion();
            homeworkQuestion.setHomeworkId(homeworkId);
            homeworkQuestion.setQuestionId(questionId);
            homeworkQuestionList.add(homeworkQuestion);
        }
        homeworkQuestionService.saveBatch(homeworkQuestionList);
    }

    @Override
    public void putHomework(Integer homeworkId) {
        Homework homework = this.getOne(new LambdaQueryWrapper<Homework>()
                .eq(Homework::getHomeworkId, homeworkId));
        if (homework.getStatus() == 0){

            //修改作业状态
            this.update(new LambdaUpdateWrapper<Homework>()
                    .eq(Homework::getHomeworkId,homeworkId)
                    .set(Homework::getStatus,1));
            //查询作业的课程id
            Integer courseId = homework.getCourseId();

            //通过课程id查询学生id
            List<Integer> studentMessageIds = courseService.getStudentMessageIds(courseId);
            List<HomeworkSubmit> homeworkSubmitList = new ArrayList<>();
            for (Integer studentMessageId : studentMessageIds){
                HomeworkSubmit homeworkSubmit = new HomeworkSubmit();
                homeworkSubmit.setHomeworkId(homeworkId);
                homeworkSubmit.setStudentId(studentMessageId);
                homeworkSubmitList.add(homeworkSubmit);
            }
            homeworkSubmitService.saveBatch(homeworkSubmitList);
        }
    }

    @Override
    public void updateHomeworkTime(Integer homeworkId, Date newTime) {
        this.update(new LambdaUpdateWrapper<Homework>()
                .eq(Homework::getHomeworkId,homeworkId)
                .set(Homework::getDeadline,newTime));
    }

    @Override
    public HomeworkVO getHomeworkToMy(Integer homeworkId) {
        Homework homeworkOne = this.getOne(new LambdaUpdateWrapper<Homework>()
                .eq(Homework::getHomeworkId, homeworkId));

        if (homeworkOne ==null){
            throw new BaseInfoException(ResultCode.PARAM_NOT_EXISTED);
        }

        //构建作业响应类
        HomeworkVO homeworkVO = new HomeworkVO();
        //将作业信息复制进去
        BeanUtils.copyProperties(homeworkOne,homeworkVO);
        //获取对应选项id
        List<HomeworkQuestion> list = homeworkQuestionService.list(new LambdaUpdateWrapper<HomeworkQuestion>()
                .eq(HomeworkQuestion::getHomeworkId, homeworkId));
        List<Integer> questionIds = list.stream().map(HomeworkQuestion::getQuestionId).collect(Collectors.toList());
        List<QuestionVo> QuestionVoList = new ArrayList<>();

        for (Integer questionId : questionIds){
            QuestionVo homeworkQuestionVo = new QuestionVo();
            Question question = questionService.getOne(new LambdaUpdateWrapper<Question>().eq(Question::getQuestionId, questionId));
            BeanUtils.copyProperties(question,homeworkQuestionVo);
            //这里的questionOptionList就已经包含所有的选项了，那为什么还需要插入呢？
            List<QuestionOption> questionOptionList = questionOptionService.list(new LambdaQueryWrapper<QuestionOption>()
                    .eq(QuestionOption::getQuestionId, question.getQuestionId()));
            List<OptionVo> optionVoList = new ArrayList<>();
//            将题目选项列表插入题目类
            for (QuestionOption questionOptionOne : questionOptionList){
                OptionVo optionVo = new OptionVo();
                BeanUtils.copyProperties(questionOptionOne,optionVo);
                optionVoList.add(optionVo);
            }
            homeworkQuestionVo.setOptionVoList(optionVoList);
            QuestionVoList.add(homeworkQuestionVo);
        }
        //将题目列表插入作业
        homeworkVO.setHomeworkQuestionVoList(QuestionVoList);
        return homeworkVO;
    }

    @Override
    public Double updateHomeworkSubmit(HomeworkSubmitRequest homeworkSubmitRequest) {
        //创建返回实体
        Double score = 0.0;

        //将答案转化为json字符串
        String toJSONStringHomeworkSubmitAnswer = JSON.toJSONString(homeworkSubmitRequest.getOptionRequestList());
        homeworkSubmitService.update(new LambdaUpdateWrapper<HomeworkSubmit>()
                .eq(HomeworkSubmit::getHomeworkSubmitId,homeworkSubmitRequest.getHomeworkSubmitId())
                .set(HomeworkSubmit::getSubmitTime,new Date())
                .set(HomeworkSubmit::getHomeworkSubmitStatus,homeworkSubmitRequest.getHomeworkSubmitStatus())
                .set(HomeworkSubmit::getHomeworkSubmitAnswer,toJSONStringHomeworkSubmitAnswer));

        if (homeworkSubmitRequest.getHomeworkSubmitStatus() == 1){
             score = correct(toJSONStringHomeworkSubmitAnswer);
            homeworkSubmitService.update(new LambdaUpdateWrapper<HomeworkSubmit>().eq(HomeworkSubmit::getHomeworkSubmitId,homeworkSubmitRequest.getHomeworkSubmitId())
                    .set(HomeworkSubmit::getHomeworkSubmitScore,score));
            return score;
        }
        return score;
    }

    @Override
    public List<HomeworkToEvenVO> getAllHomework() {
        Integer userId = AccountHolder.getUser().getUserId();
        Integer studentMessageId = studentMessageService.getStudentMessageId(userId);

        List<HomeworkToEvenVO> homeworkToEvenVOList = new ArrayList<>();

        List<HomeworkSubmit> homeworkSubmitList = homeworkSubmitService.list(new LambdaQueryWrapper<HomeworkSubmit>()
                .eq(HomeworkSubmit::getStudentId, studentMessageId));
        if (homeworkSubmitList.size()==0){
            return homeworkToEvenVOList;
        }
        List<Integer> homeworkIds = homeworkSubmitList.stream().map(HomeworkSubmit::getHomeworkId).collect(Collectors.toList());
        List<Homework> homeworkList = this.list(new LambdaQueryWrapper<Homework>()
                .in(Homework::getHomeworkId, homeworkIds));
        List<Integer> courseIds = homeworkList.stream().map(Homework::getCourseId).collect(Collectors.toList());
        List<Course> courseList = courseService.list(new LambdaQueryWrapper<Course>().in(Course::getCourseId, courseIds));

        for (int i = 0; i < homeworkSubmitList.size(); i++){
            HomeworkToEvenVO homeworkToEvenVO = new HomeworkToEvenVO();
            homeworkToEvenVO.setHomeworkId(homeworkList.get(i).getHomeworkId());
            homeworkToEvenVO.setHomeworkTitle(homeworkList.get(i).getHomeworkTitle());
            for (Course course : courseList) {
                if (course.getCourseId().equals(homeworkList.get(i).getCourseId())) {
                    homeworkToEvenVO.setCourseName(course.getCourseName());
                }
            }
            homeworkToEvenVO.setDeadline(homeworkList.get(i).getDeadline());
            homeworkToEvenVOList.add(homeworkToEvenVO);
        }
        return homeworkToEvenVOList;
    }

    //批改分数
    @Override
    public Double correct(String answerJsonString){
        //json字符串转化为list集合
        List<OptionRequest> optionRequestList = JSON.parseArray(answerJsonString, OptionRequest.class);
        //获取总题目数量
        double optionNumber = optionRequestList.size();
        //对的题目数量
        long isCorrectCount = 0;
        //获取单个题目的对错情况
        for (OptionRequest optionRequest : optionRequestList ){
            Integer questionId = optionRequest.getQuestionId();
            //获取所有选项的对错对比，如果和选项数目一致则表示该题正确
            long count1 = questionOptionService.count(new LambdaQueryWrapper<QuestionOption>()
                    .eq(QuestionOption::getQuestionId, questionId)
                    .eq(QuestionOption::getIsCorrect, 1)
                    .in(QuestionOption::getOptionIndex, optionRequest.getOptionIndexList())
                    );
            if (count1 == optionRequest.getOptionIndexList().size() ){
                isCorrectCount ++;
            }
        }
        //计算分数
        DecimalFormat format2 = new DecimalFormat("0.0000");
        String format1 = format2.format(isCorrectCount / optionNumber);
        return Double.parseDouble(format1)*100;
    }
}




