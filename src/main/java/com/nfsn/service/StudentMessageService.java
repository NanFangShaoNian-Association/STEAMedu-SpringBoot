package com.nfsn.service;

import com.nfsn.model.dto.StudentInfoRequest;
import com.nfsn.model.entity.StudentMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.PendingPaymentStudentInfoVO;
import com.nfsn.model.vo.StudentInfoVO;

/**
* @author Tuanzi
* @description 针对表【student_message】的数据库操作Service
* @createDate 2023-02-09 16:30:53
*/
public interface StudentMessageService extends IService<StudentMessage> {

    /**
     * 通过用户id获取学生id
     * @param userId
     * @return
     */
    Integer getStudentMessageId(Integer userId);

    StudentInfoVO getStudentInfo();

    /**
     * 获取角色是否完整
     * @return
     */
    StudentMessage getStudentFull();

    void updateStudentInfo(StudentInfoRequest studentInfoRequest);

    void updateStudentPhotoByUserId(Integer userId, String photoUrl);

    PendingPaymentStudentInfoVO getStudentInfoById(Integer userId);
}
