package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.NotificationStatus;
import com.nfsn.service.NotificationStatusService;
import com.nfsn.mapper.NotificationStatusMapper;
import org.springframework.stereotype.Service;

/**
* @author 温格
* @description 针对表【notification_status(通知状态表)】的数据库操作Service实现
* @createDate 2023-04-10 10:22:50
*/
@Service
public class NotificationStatusServiceImpl extends ServiceImpl<NotificationStatusMapper, NotificationStatus>
    implements NotificationStatusService{

}




