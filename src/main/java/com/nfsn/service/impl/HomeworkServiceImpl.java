package com.nfsn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.mapper.HomeworkSubmitMapper;
import com.nfsn.model.dto.HomeworkRequest;
import com.nfsn.model.dto.HomeworkSubmitRequest;
import com.nfsn.model.entity.*;
import com.nfsn.model.vo.HomeworkQuestionVo;
import com.nfsn.model.vo.HomeworkVO;
import com.nfsn.model.vo.OptionVo;
import com.nfsn.service.*;
import com.nfsn.mapper.HomeworkMapper;
import com.nfsn.utils.AccountHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

        //构建作业响应类
        HomeworkVO homeworkVO = new HomeworkVO();
        //将作业信息复制进去
        BeanUtils.copyProperties(homeworkOne,homeworkVO);
        //获取对应选项id
        List<HomeworkQuestion> list = homeworkQuestionService.list(new LambdaUpdateWrapper<HomeworkQuestion>()
                .eq(HomeworkQuestion::getHomeworkId, homeworkId));
        List<Integer> questionIds = list.stream().map(HomeworkQuestion::getQuestionId).collect(Collectors.toList());
        List<HomeworkQuestionVo>  homeworkQuestionVoList = new ArrayList<>();

        for (Integer questionId : questionIds){
            HomeworkQuestionVo homeworkQuestionVo = new HomeworkQuestionVo();
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
            homeworkQuestionVoList.add(homeworkQuestionVo);
        }
        //将题目列表插入作业
        homeworkVO.setHomeworkQuestionVoList(homeworkQuestionVoList);
        return homeworkVO;
    }

    @Override
    public HomeworkSubmit updateHomeworkSubmit(HomeworkSubmitRequest homeworkSubmitRequest) {

        HomeworkSubmit homeworkSubmit1 = homeworkSubmitMapper.selectByHomeworkId(homeworkSubmitRequest.getHomeworkSubmitId());
        System.out.println("homeworkSubmit1::" + homeworkSubmit1);

//
//        HomeworkSubmit homeworkSubmitServiceOne = homeworkSubmitService.getOne(new LambdaQueryWrapper<HomeworkSubmit>()
//                .eq(HomeworkSubmit::getHomeworkSubmitId, homeworkSubmitRequest.getHomeworkSubmitId()));
//
//        HomeworkSubmit homeworkSubmit = new HomeworkSubmit();
//        BeanUtils.copyProperties(homeworkSubmitRequest,homeworkSubmitServiceOne);
//        homeworkSubmitServiceOne.setSubmitTime(new Date());
//        homeworkSubmitService.updateById(homeworkSubmitServiceOne);

        return null;

//        homeworkSubmitService.update(new LambdaUpdateWrapper<HomeworkSubmit>()
//                .eq(HomeworkSubmit::getHomeworkSubmitId,homeworkSubmitRequest.getHomeworkSubmitId())
//                .set(HomeworkSubmit::getHomeworkSubmitAnswer,homeworkSubmitRequest.getHomeworkSubmitAnswer())
//                .set(HomeworkSubmit::getHomeworkSubmitStatus,homeworkSubmitRequest.getHomeworkSubmitStatus())
//                .set(HomeworkSubmit::getSubmitTime,new Date()));

//        HomeworkSubmit homeworkSubmit = new HomeworkSubmit();
//        BeanUtils.copyProperties(homeworkSubmitRequest,homeworkSubmit);
//        homeworkSubmit.setStudentId(studentMessageId);
//        homeworkSubmit.setReviewTime(new Date());
//        homeworkSubmitService.updateById(homeworkSubmit);
    }
}




