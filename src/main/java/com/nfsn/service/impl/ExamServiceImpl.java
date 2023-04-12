package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Exam;
import com.nfsn.service.ExamService;
import com.nfsn.mapper.ExamMapper;
import org.springframework.stereotype.Service;

/**
* @author 温格
* @description 针对表【exam】的数据库操作Service实现
* @createDate 2023-04-10 09:52:02
*/
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam>
    implements ExamService{

}




