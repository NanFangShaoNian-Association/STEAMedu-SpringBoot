package com.nfsn.service;

import com.nfsn.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.CourseInstitutionInfoVO;

/**
* @author Tuanzi
* @description 针对表【user】的数据库操作Service
* @createDate 2023-02-09 16:30:53
*/
public interface UserService extends IService<User> {
    /**
     * 根据用户手机号查询用户
     * @param phone 手机号
     * @return 用户信息
     */
    User getUserByPhone(String phone,String ip);

    CourseInstitutionInfoVO getInstitutionByUserId(Integer userId);
}
