package com.nfsn.service;

import com.nfsn.model.dto.NotificationRequest;
import com.nfsn.model.entity.Notification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.NotificationToEvenVO;
import com.nfsn.model.vo.NotificationVo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
* @author 温格
* @description 针对表【notification(通知表)】的数据库操作Service
* @createDate 2023-04-10 10:21:51
*/
public interface NotificationService extends IService<Notification> {

    /**
     * 发布签到
     * @param notificationRequest
     */
    void addNotification(NotificationRequest notificationRequest);


    /**
     * 删除指定通知（老师）
     * @param notificationId
     */
    void delNotification(Integer notificationId);

    /**
     * 获取通知
     * @param notificationId
     * @return
     */
    NotificationVo getNotification(Integer notificationId);


    /**
     * 获取所有事件通知
     * @return
     */
    List<NotificationToEvenVO> getAllNotification();



}
