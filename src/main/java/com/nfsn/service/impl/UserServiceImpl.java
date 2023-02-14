package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.User;
import com.nfsn.model.vo.CourseInstitutionInfoVO;
import com.nfsn.model.vo.FriendsVO;
import com.nfsn.model.vo.PersonalInfoVO;
import com.nfsn.service.UserService;
import com.nfsn.mapper.UserMapper;
import com.nfsn.utils.AccountHolder;
import com.nfsn.utils.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Override
    public PersonalInfoVO getUserInfo() {
        Integer userId = AccountHolder.getUser().getUserId();

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserId, userId)
                .eq(User::getUserStatus, 0));//获取账号状态正常的数据

        PersonalInfoVO personalInfoVO = BeanUtil.copyProperties(user, PersonalInfoVO.class);
        return personalInfoVO;
    }

    @Override
    public List<FriendsVO> searchUser(String target) {
        String phoneNumber = AccountHolder.getUser().getPhoneNumber();
        Integer userId = AccountHolder.getUser().getUserId();

        //搜索用户（手机号或者STEAM学号-即用户id），排除本人
        //预编译语句为：SELECT * FROM user WHERE ((phone_number <> ? AND user_id <> ?) AND (phone_number = ? OR user_id = ?))
        List<User> users = this.list(new LambdaQueryWrapper<User>()
                .and(userLambdaQueryWrapper -> userLambdaQueryWrapper
                        .ne(User::getPhoneNumber,phoneNumber)
                        .ne(User::getUserId,userId))
                .and(userLambdaQueryWrapper -> userLambdaQueryWrapper
                        .eq(User::getPhoneNumber, target)
                        .or()
                        .eq(User::getUserId, target)));

        List<FriendsVO> friendsVOS = BeanUtil.copyToList(users, FriendsVO.class);
        return friendsVOS;
    }
}




