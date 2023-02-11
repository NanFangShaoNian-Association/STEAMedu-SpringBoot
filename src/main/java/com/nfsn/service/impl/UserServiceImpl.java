package com.nfsn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.User;
import com.nfsn.model.vo.CourseInstitutionInfoVO;
import com.nfsn.service.UserService;
import com.nfsn.mapper.UserMapper;
import com.nfsn.utils.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author Tuanzi
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户手机号查询用户
     * @param phone 手机号
     * @return 用户信息
     */
    @Override
    public User getUserByPhone(String phone,String ip) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhoneNumber, phone);
        User user = this.getOne(queryWrapper);

        if (user == null) {
//            生成用户相关信息，相当于自动注册
            this.save(generateUser(phone,ip));
            user = this.getOne(queryWrapper);
        }

        return user;
    }

    private User generateUser(String phone,String ip){
        Date currentTime = new Date();
        User user = new User();
        user.setUserName(getRandomName());
        user.setUserRole(0);
        user.setUserAvatar(getDefaultAvatar());
        user.setPhoneNumber(phone);
        user.setUserIntroduction(getDefaultIntroduction());
        user.setLoginLastTime(currentTime);
        user.setLoginLastTimeIp(ip);
        user.setUserRegistTime(currentTime);
        user.setUserStatus(0);
        return user;
    }

    private String getRandomName(){
        return "ns_" + RandomUtils.getRandom(RandomUtils.ALL_LITTLE_LETTER,13);
    }

    private String getDefaultAvatar(){
        return "https://upload-bbs.mihoyo.com/upload/2021/04/26/275717452/3101bce2626a6c808e975e7d4514958e_4697878850710672732.jpg";
    }

    private String getDefaultGender(){
        return "男";
    }

    private String getDefaultIntroduction(){
        return "您还没有简介，请添加简介！";
    }

    @Override
    public CourseInstitutionInfoVO getInstitutionByUserId(Integer userId) {
        return userMapper.getInstitutionByUserId(userId);
    }
}




