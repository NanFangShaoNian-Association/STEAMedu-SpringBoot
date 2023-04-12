package com.nfsn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.BaseInfoException;
import com.nfsn.model.dto.NotificationRequest;
import com.nfsn.model.entity.CourseStudent;
import com.nfsn.model.entity.Notification;
import com.nfsn.model.entity.NotificationStatus;
import com.nfsn.model.entity.SignInStatus;
import com.nfsn.model.vo.NotificationVo;
import com.nfsn.service.*;
import com.nfsn.mapper.NotificationMapper;
import com.nfsn.utils.AccountHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 温格
* @description 针对表【notification(通知表)】的数据库操作Service实现
* @createDate 2023-04-10 10:21:51
*/
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
    implements NotificationService{

    @Resource
    private CourseStudentService courseStudentService;

    @Resource
    private NotificationStatusService notificationStatusService;

    @Resource
    private StudentMessageService studentMessageService;

    @Resource
    private CourseService courseService;

    @Resource
    private UserService userService;

    @Override
    public void addNotification(NotificationRequest notificationRequest) {

        Integer userId = AccountHolder.getUser().getUserId();
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationRequest,notification);
        notification.setPublishTime(new Date());
        notification.setUserId(userId);
        boolean save = this.save(notification);
        Integer notificationId = notification.getNotificationId();

        if (save){
            //将通知发放给课程同学
            List<CourseStudent> courseStudents = courseStudentService.list(new LambdaQueryWrapper<CourseStudent>()
                    .eq(CourseStudent::getCourseId, notification.getCourseId()));
            //查询所有对应学生的id
            List<Integer> studentMessageIds = courseStudents.stream().map(CourseStudent::getStudentMessageId).collect(Collectors.toList());
            List<NotificationStatus> notificationStatusList = new ArrayList<>();
            for(Integer studentMessageId : studentMessageIds){
                NotificationStatus notificationStatus = new NotificationStatus();
                notificationStatus.setNotificationId(notificationId);
                notificationStatus.setStudentMessageId(studentMessageId);
                notificationStatus.setStatus(0);
                notificationStatusList.add(notificationStatus);
            }
            notificationStatusService.saveBatch(notificationStatusList);
        }
    }

    @Override
    public void delNotification(Integer notificationId) {


        boolean delNotificationRemove = this.remove(new LambdaQueryWrapper<Notification>()
                .eq(Notification::getNotificationId, notificationId));
        if (!delNotificationRemove){
            throw new BaseInfoException(ResultCode.PARAM_IS_INVALID);
        }

        if (delNotificationRemove){
            notificationStatusService.remove(new LambdaQueryWrapper<NotificationStatus>().eq(NotificationStatus::getNotificationId,notificationId));
        }
    }

    @Override
    public NotificationVo getNotification(Integer notificationId) {
        Integer userId = AccountHolder.getUser().getUserId();
        Integer studentMessageId = studentMessageService.getStudentMessageId(userId);
        NotificationStatus notificationStatusOne = notificationStatusService.getOne(new LambdaQueryWrapper<NotificationStatus>()
                .eq(NotificationStatus::getNotificationId, notificationId)
                .eq(NotificationStatus::getStudentMessageId, studentMessageId));
        if (notificationStatusOne.getStatus() == 0){
            notificationStatusService.update(new LambdaUpdateWrapper<NotificationStatus>()
                    .eq(NotificationStatus::getNotificationId,notificationId)
                    .eq(NotificationStatus::getStudentMessageId,studentMessageId)
                    .set(NotificationStatus::getStatus,1));
        }
        //查询通知信息
        Notification notificationOne = this.getOne(new LambdaQueryWrapper<Notification>().eq(Notification::getNotificationId, notificationId));
        //查询课程信息,获取名字
        String courseName = courseService.getById(notificationOne.getCourseId()).getCourseName();
        //查询发布人，获取名字
        String userName = userService.getById(userId).getUserName();


        //复制通知信息
        NotificationVo notificationVo = new NotificationVo();
        BeanUtils.copyProperties(notificationOne,notificationVo);
        //补充消息体信息
        notificationVo.setCourseName(courseName);
        notificationVo.setUserName(userName);

        return notificationVo;


    }
}




