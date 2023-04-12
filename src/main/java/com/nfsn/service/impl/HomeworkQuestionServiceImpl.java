package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.HomeworkQuestion;
import com.nfsn.service.HomeworkQuestionService;
import com.nfsn.mapper.HomeworkQuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 温格
* @description 针对表【homework_question(作业题目联系表)】的数据库操作Service实现
* @createDate 2023-04-10 09:30:47
*/
@Service
public class HomeworkQuestionServiceImpl extends ServiceImpl<HomeworkQuestionMapper, HomeworkQuestion>
    implements HomeworkQuestionService{

}




