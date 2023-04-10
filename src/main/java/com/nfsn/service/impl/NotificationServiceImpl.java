package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Notification;
import com.nfsn.service.NotificationService;
import com.nfsn.mapper.NotificationMapper;
import org.springframework.stereotype.Service;

/**
* @author 温格
* @description 针对表【notification(通知表)】的数据库操作Service实现
* @createDate 2023-04-10 10:21:51
*/
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
    implements NotificationService{

}




