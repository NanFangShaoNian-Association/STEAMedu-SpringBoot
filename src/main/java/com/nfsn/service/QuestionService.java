package com.nfsn.service;

import com.nfsn.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.QuestionOptionVo;

import java.util.List;

/**
* @author 温格
* @description 针对表【question(题目表)】的数据库操作Service
* @createDate 2023-04-10 09:58:12
*/
public interface QuestionService extends IService<Question> {


    /**
     * 根据需求获取所有题目(null为所有题目，可以按类别搜索)
     * @param typeName
     * @return
     */
    List<Question> getQuestion(String typeName);


    /**
     * 查询对应选项
     * @param questionId
     * @return
     */
    QuestionOptionVo getQuestionToQuestionOption(Integer questionId);

}
