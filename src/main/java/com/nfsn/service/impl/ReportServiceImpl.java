package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Report;
import com.nfsn.service.ReportService;
import com.nfsn.mapper.ReportMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【report】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report>
    implements ReportService{

}




