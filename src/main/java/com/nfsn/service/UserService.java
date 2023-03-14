package com.nfsn.service;

import com.nfsn.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.AccountInfoVO;
import com.nfsn.model.vo.CourseInstitutionInfoVO;
import com.nfsn.model.vo.FriendsVO;
import com.nfsn.model.vo.PersonalInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    PersonalInfoVO getUserInfo();

    /**
     * 获取已知对象id的用户信息
     * @param requestUserId
     * @return
     */
    PersonalInfoVO getRequestUserInfo(Integer requestUserId);



    FriendsVO searchUser(String target, Long ifFriend);

    AccountInfoVO getAccountInfo();

    String uploadAvatar(MultipartFile file);

    String uploadPhoto(MultipartFile file);
}
