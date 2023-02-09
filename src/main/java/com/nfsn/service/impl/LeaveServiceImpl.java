package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Leave;
import com.nfsn.service.LeaveService;
import com.nfsn.mapper.LeaveMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【leave】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave>
    implements LeaveService{

}




