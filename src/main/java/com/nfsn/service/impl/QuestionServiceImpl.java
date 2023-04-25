package com.nfsn.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.BaseInfoException;
import com.nfsn.model.entity.Question;
import com.nfsn.model.entity.QuestionOption;
import com.nfsn.model.vo.QuestionOptionVo;
import com.nfsn.service.QuestionOptionService;
import com.nfsn.service.QuestionService;
import com.nfsn.mapper.QuestionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 温格
* @description 针对表【question(题目表)】的数据库操作Service实现
* @createDate 2023-04-10 09:58:12
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

    @Resource
    private QuestionOptionService questionOptionService;

    @Override
    public List<Question> getQuestion(String typeName) {
        if (typeName == null){
            return this.list();
        }

        return this.list(new LambdaQueryWrapper<Question>().eq(Question::getQuestionCategory, typeName));
    }

    @Override
    public QuestionOptionVo getQuestionToQuestionOption(Integer questionId) {

        QuestionOptionVo questionOptionVo = new QuestionOptionVo();
        Question question = this.getOne(new LambdaQueryWrapper<Question>().eq(Question::getQuestionId, questionId));
        if (question == null){
            throw new BaseInfoException(ResultCode.PARAM_NOT_EXISTED);
        }

        List<QuestionOption> questionOptionList = questionOptionService.list(new LambdaQueryWrapper<QuestionOption>().eq(QuestionOption::getQuestionId, questionId));
        //插入数据
        BeanUtils.copyProperties(question,questionOptionVo);
        questionOptionVo.setQuestionOptionList(questionOptionList);
        return questionOptionVo;
    }
}




