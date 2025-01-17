package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.BaseInfoException;
import com.nfsn.model.dto.StudentInfoRequest;
import com.nfsn.model.entity.StudentMessage;
import com.nfsn.model.entity.User;
import com.nfsn.model.vo.PendingPaymentStudentInfoVO;
import com.nfsn.model.vo.StudentInfoVO;
import com.nfsn.service.StudentMessageService;
import com.nfsn.mapper.StudentMessageMapper;
import com.nfsn.utils.AccountHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Tuanzi
* @description 针对表【student_message】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class StudentMessageServiceImpl extends ServiceImpl<StudentMessageMapper, StudentMessage>
    implements StudentMessageService{

    @Resource
    private StudentMessageMapper studentMessageMapper;

    @Override
    public Integer getStudentMessageId(Integer userId) {
        StudentMessage studentMessage = this.getOne(new LambdaQueryWrapper<StudentMessage>()
                .eq(StudentMessage::getUserId, userId));
        return studentMessage.getStudentMessageId();
    }

    @Override
    public StudentInfoVO getStudentInfo() {
        Integer userId = AccountHolder.getUser().getUserId();

        StudentMessage studentMessage = this.getOne(new LambdaQueryWrapper<StudentMessage>()
                .eq(StudentMessage::getUserId,userId), false);//不抛出异常

        StudentInfoVO studentInfoVO = BeanUtil.copyProperties(studentMessage, StudentInfoVO.class);
        return studentInfoVO;
    }

    @Override
    public StudentMessage getStudentFull() {

        Integer userId = AccountHolder.getUser().getUserId();
        LambdaQueryWrapper<StudentMessage> queryWrapper = new LambdaQueryWrapper<StudentMessage>()
                .eq(StudentMessage::getUserId, userId)
                .isNotNull(StudentMessage::getStudentMessageName)
                .isNotNull(StudentMessage::getStudentSex)
                .isNotNull(StudentMessage::getSchool)
                .isNotNull(StudentMessage::getBirthday)
                .isNotNull(StudentMessage::getPhoneNumber)
                .isNotNull(StudentMessage::getStudentPhoto)
                .isNotNull(StudentMessage::getGrade);

        return this.getOne(queryWrapper);

    }

    @Override
    public void updateStudentInfo(StudentInfoRequest studentInfoRequest) {

        Integer userId = AccountHolder.getUser().getUserId();
        //限制输入超过5的汉字
        if (studentInfoRequest.getStudentMessageName() == null || studentInfoRequest.getStudentMessageName().length()>5 ){
            throw new BaseInfoException(ResultCode.USER_NAME_EXCEED);
        }

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

    /**
     * 根据用户ID更新学生的照片URL。
     *
     * @param userId   用户ID
     * @param photoUrl 新的照片URL
     */
    @Override
    public void updateStudentPhotoByUserId(Integer userId, String photoUrl) {
        // 创建一个查询条件，基于"user_id"字段
        QueryWrapper<StudentMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        // 使用查询条件从studentMessageMapper中查找单个学生信息记录
        StudentMessage studentMessage = studentMessageMapper.selectOne(queryWrapper);

        // 如果找到了对应的学生信息记录
        if (studentMessage != null) {
            // 设置学生照片的新URL
            studentMessage.setStudentPhoto(photoUrl);

            // 使用studentMessageMapper更新数据库中的学生信息记录
            studentMessageMapper.updateById(studentMessage);
        }
    }

    @Override
    public PendingPaymentStudentInfoVO getStudentInfoById(Integer userId) {
        // 查询学生信息
        StudentMessage studentMessage = this.getOne(new LambdaQueryWrapper<StudentMessage>()
                .eq(StudentMessage::getUserId, userId));

        // 创建待支付学生信息对象并设置属性值
        PendingPaymentStudentInfoVO pendingPaymentStudentInfoVO = new PendingPaymentStudentInfoVO();
        pendingPaymentStudentInfoVO.setStudentName(studentMessage.getStudentMessageName());
        pendingPaymentStudentInfoVO.setGrade(studentMessage.getGrade());
        pendingPaymentStudentInfoVO.setPhoneNumber(studentMessage.getPhoneNumber());

        // 返回待支付学生信息对象
        return pendingPaymentStudentInfoVO;
    }

}