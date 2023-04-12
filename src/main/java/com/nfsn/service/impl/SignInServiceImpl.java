package com.nfsn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.BaseInfoException;
import com.nfsn.model.dto.SignInRequest;
import com.nfsn.model.entity.CourseStudent;
import com.nfsn.model.entity.SignIn;
import com.nfsn.model.entity.SignInStatus;
import com.nfsn.model.vo.SignInCourseStatusVO;
import com.nfsn.model.vo.SignInStatusVO;
import com.nfsn.service.CourseStudentService;
import com.nfsn.service.SignInService;
import com.nfsn.mapper.SignInMapper;
import com.nfsn.service.SignInStatusService;
import com.nfsn.service.StudentMessageService;
import com.nfsn.utils.AccountHolder;
import com.sun.org.apache.bcel.internal.generic.NEW;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 温格
* @description 针对表【sign_in(签到表)】的数据库操作Service实现
* @createDate 2023-04-10 09:36:23
*/
@Service
public class SignInServiceImpl extends ServiceImpl<SignInMapper, SignIn>
    implements SignInService{

    @Resource
    private CourseStudentService courseStudentService;

    @Resource
    private SignInStatusService signInStatusService;

    @Resource
    private StudentMessageService studentMessageService;

    @Override
    public void addSignIn(SignInRequest signInRequest) {
        Integer userId = AccountHolder.getUser().getUserId();
        SignIn signIn = new SignIn();
        signIn.setCourseId(signInRequest.getCourseId());
        signIn.setUserId(userId);
        signIn.setDeadline(signInRequest.getDeadline());
        boolean save = this.save(signIn);
        Integer signInId = signIn.getSignInId();
        if (save){
            //查询课程的所有学生
            List<CourseStudent> courseStudents = courseStudentService.list(new LambdaQueryWrapper<CourseStudent>()
                    .eq(CourseStudent::getCourseId, signInRequest.getCourseId()));
            //查询所有对应学生的id
            List<Integer> StudentMessageIds = courseStudents.stream().map(CourseStudent::getStudentMessageId).collect(Collectors.toList());
            List<SignInStatus> signInStatusList = new ArrayList<>();
            for (Integer studentMessageId : StudentMessageIds){
                SignInStatus signInStatus = new SignInStatus();
                signInStatus.setSignInId(signInId);
                signInStatus.setStudentMessageId(studentMessageId);
                signInStatus.setSignInStatus(0);
                signInStatusList.add(signInStatus);
            }
            signInStatusService.saveBatch(signInStatusList);
        }
    }

    @Override
    public void delSignIn(Integer signInId) {
        this.removeById(signInId);
    }

    @Override
    public SignInStatusVO updateSignInToStudent(Integer signInId) {
        Integer userId = AccountHolder.getUser().getUserId();
        Integer studentMessageId = studentMessageService.getStudentMessageId(userId);
        SignInStatus signInStatusServiceOne = signInStatusService.getOne(new LambdaQueryWrapper<SignInStatus>()
                .eq(SignInStatus::getSignInId, signInId)
                .eq(SignInStatus::getStudentMessageId, studentMessageId));
        //查询当前签到是否存在
        SignIn one = this.getOne(new LambdaQueryWrapper<SignIn>().eq(SignIn::getSignInId, signInId));
        //构建返回实体
        SignInStatusVO signInStatusVO  = new SignInStatusVO();
        BeanUtils.copyProperties(signInStatusServiceOne,signInStatusVO);

        if (one == null){
            throw new BaseInfoException(ResultCode.NOTIFICATION_SIGN_DEL_SIGN);
        }
        if (signInStatusServiceOne.getSignInStatus() == 0){
            signInStatusService.update(new LambdaUpdateWrapper<SignInStatus>()
                    .eq(SignInStatus::getSignInId, signInId)
                    .eq(SignInStatus::getStudentMessageId, userId)
                    .set(SignInStatus::getSignInStatus, 1)
                    .set(SignInStatus::getSignInTime, new Date()));
            signInStatusVO.setSignInStatus(1);
            return signInStatusVO;
        }
        return signInStatusVO;
    }

    @Override
    public SignInStatusVO getSignInToStudent(Integer signInId) {
        Integer userId = AccountHolder.getUser().getUserId();
        Integer studentMessageId = studentMessageService.getStudentMessageId(userId);
        SignInStatus signInStatusServiceOne = signInStatusService.getOne(new LambdaQueryWrapper<SignInStatus>()
                .eq(SignInStatus::getSignInId, signInId)
                .eq(SignInStatus::getStudentMessageId, studentMessageId));
        if (signInStatusServiceOne == null){
            throw new BaseInfoException(ResultCode.PARAM_IS_INVALID);
        }
        SignInStatusVO signInStatusVO  = new SignInStatusVO();
        BeanUtils.copyProperties(signInStatusServiceOne,signInStatusVO);
        return signInStatusVO;

    }

    @Override
    public List<SignIn> getSignInAllToCourse(Integer courseId) {
        Integer userId = AccountHolder.getUser().getUserId();
        List<SignIn> list = this.list(new LambdaQueryWrapper<SignIn>()
                .eq(SignIn::getCourseId, courseId)
                .eq(SignIn::getUserId, userId)
                .orderByDesc(SignIn::getDeadline));
        return list;

    }

    @Override
    public SignInCourseStatusVO getSignInToCourse(Integer signInId) {

        //签到信息
        SignIn signIn = this.getOne(new LambdaQueryWrapper<SignIn>().eq(SignIn::getSignInId, signInId));

        //签到人数
        List<SignInStatus> signInStatusList = signInStatusService.list(new LambdaQueryWrapper<SignInStatus>().eq(SignInStatus::getSignInId, signInId));
        long allSignInCount = signInStatusList.size();
        long isSignInCount = signInStatusService.count(new LambdaQueryWrapper<SignInStatus>()
                .eq(SignInStatus::getSignInId, signInId).eq(SignInStatus::getSignInStatus,1));

        SignInCourseStatusVO signInCourseStatusVO  = new SignInCourseStatusVO();
        signInCourseStatusVO.setSignIn(signIn);
        signInCourseStatusVO.setAllSignInCount(allSignInCount);
        signInCourseStatusVO.setIsSignInCount(isSignInCount);
        return signInCourseStatusVO;
    }


}




