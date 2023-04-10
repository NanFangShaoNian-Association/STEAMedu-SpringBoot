package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Question;
import com.nfsn.service.QuestionService;
import com.nfsn.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 温格
* @description 针对表【question(题目表)】的数据库操作Service实现
* @createDate 2023-04-10 09:58:12
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




