package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Examination;
import com.nfsn.service.ExaminationService;
import com.nfsn.mapper.ExaminationMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【examination】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class ExaminationServiceImpl extends ServiceImpl<ExaminationMapper, Examination>
    implements ExaminationService{

}




