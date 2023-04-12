package com.nfsn.service;

import com.nfsn.model.dto.SignInRequest;
import com.nfsn.model.entity.SignIn;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.SignInCourseStatusVO;
import com.nfsn.model.vo.SignInStatusVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
* @author 温格
* @description 针对表【sign_in(签到表)】的数据库操作Service
* @createDate 2023-04-10 09:36:23
*/
public interface SignInService extends IService<SignIn> {

    /**
     * 新增签到
     * @param signInRequest 签到实体
     */
    void addSignIn(SignInRequest signInRequest);

    /**
     * 删除签到
     * @param signInId
     */
    void delSignIn(Integer signInId);

    /**
     * 学生签到
     * @param signInId
     * @return 签到实体
     */
    SignInStatusVO updateSignInToStudent(Integer signInId);

    /**
     * 查看签到
     * @param signInId
     * @return
     */
    SignInStatusVO getSignInToStudent( Integer signInId);


    /**
     * 返回签到列表
     * @param courseId
     * @return
     */
    List<SignIn> getSignInAllToCourse(Integer courseId);

    /**
     * 老师获取单个签到情况
     * @param signInId
     * @return
     */
    SignInCourseStatusVO getSignInToCourse(Integer signInId);
}
