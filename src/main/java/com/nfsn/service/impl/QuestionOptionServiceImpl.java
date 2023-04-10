package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.QuestionOption;
import com.nfsn.service.QuestionOptionService;
import com.nfsn.mapper.QuestionOptionMapper;
import org.springframework.stereotype.Service;

/**
* @author 温格
* @description 针对表【question_option(题目选项表)】的数据库操作Service实现
* @createDate 2023-04-10 10:19:02
*/
@Service
public class QuestionOptionServiceImpl extends ServiceImpl<QuestionOptionMapper, QuestionOption>
    implements QuestionOptionService{

}




