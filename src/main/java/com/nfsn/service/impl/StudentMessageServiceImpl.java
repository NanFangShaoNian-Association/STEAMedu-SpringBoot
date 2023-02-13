package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.dto.StudentInfoRequest;
import com.nfsn.model.entity.StudentMessage;
import com.nfsn.model.vo.StudentInfoVO;
import com.nfsn.service.StudentMessageService;
import com.nfsn.mapper.StudentMessageMapper;
import com.nfsn.utils.AccountHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【student_message】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class StudentMessageServiceImpl extends ServiceImpl<StudentMessageMapper, StudentMessage>
    implements StudentMessageService{

    @Override
    public StudentInfoVO getStudentInfo(Integer studentInfoId) {
        Integer userId = AccountHolder.getUser().getUserId();

        StudentMessage studentMessage = this.getOne(new LambdaQueryWrapper<StudentMessage>()
                .eq(StudentMessage::getUserId,userId)
                .eq(StudentMessage::getStudentMessageId, studentInfoId), false);//不抛出异常

        StudentInfoVO studentInfoVO = BeanUtil.copyProperties(studentMessage, StudentInfoVO.class);
        return studentInfoVO;
    }

    @Override
    public void updateStudentInfo(StudentInfoRequest studentInfoRequest) {
        if (!checkStudentInfo(studentInfoRequest)){
            //todo:抛出学生信息不合法异常
            return;
        }
        Integer userId = AccountHolder.getUser().getUserId();

        StudentMessage studentMessage = BeanUtil.copyProperties(studentInfoRequest, StudentMessage.class);
        studentMessage.setUserId(userId);

        //根据用户id和学生信息id是否一致来判断是否是新增和更新
        this.saveOrUpdate(studentMessage,new LambdaUpdateWrapper<StudentMessage>()
                .eq(StudentMessage::getUserId,studentMessage.getUserId())
                .eq(StudentMessage::getStudentMessageId,studentMessage.getStudentMessageId()));
    }

    //检查学生信息的合法性，合法返回true，否则返回false
    public boolean checkStudentInfo(StudentInfoRequest studentInfoRequest) {
        return true;
    }
}




