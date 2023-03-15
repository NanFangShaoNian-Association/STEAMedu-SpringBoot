package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.mapper.StudentMessageMapper;
import com.nfsn.model.entity.Friends;
import com.nfsn.model.entity.StudentMessage;
import com.nfsn.model.entity.User;
import com.nfsn.model.vo.AccountInfoVO;
import com.nfsn.model.vo.CourseInstitutionInfoVO;
import com.nfsn.model.vo.FriendsVO;
import com.nfsn.model.vo.PersonalInfoVO;
import com.nfsn.service.FriendsService;
import com.nfsn.service.StudentMessageService;
import com.nfsn.service.UserService;
import com.nfsn.mapper.UserMapper;
import com.nfsn.utils.AccountHolder;
import com.nfsn.utils.MinIOUtil;
import com.nfsn.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.nfsn.constants.MinIOConstants.*;

/**
* @author Tuanzi
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private MinIOUtil minIOUtil;

    @Resource
    private StudentMessageService studentMessageService;

    @Resource
    private StudentMessageMapper studentMessageMapper;

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
            //生成用户相关信息，相当于自动注册
            User newUser = generateUser(phone, ip);
            this.save(newUser);
            user = this.getOne(queryWrapper);

            // 在student_message表中生成一条user_id为刚才生成的user_id的一条数据
            StudentMessage studentMessage = new StudentMessage();
            studentMessage.setUserId(user.getUserId());
            studentMessage.setStudentMessageStatus(0);

            studentMessageMapper.insert(studentMessage);
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
    public PersonalInfoVO getRequestUserInfo(Integer requestUserId) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserId, requestUserId)
                .eq(User::getUserStatus, 0));//获取账号状态正常的数据

        PersonalInfoVO personalInfoVO = BeanUtil.copyProperties(user, PersonalInfoVO.class);
        return personalInfoVO;
    }

    @Override
    public FriendsVO searchUser(String target,Long ifFriend) {
        String phoneNumber = AccountHolder.getUser().getPhoneNumber();
        Integer userId = AccountHolder.getUser().getUserId();

        //搜索用户（手机号或者STEAM学号-即用户id），排除本人
        //预编译语句为：SELECT * FROM user WHERE ((phone_number <> ? AND user_id <> ?) AND (phone_number = ? OR user_id = ?))
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .and(userLambdaQueryWrapper -> userLambdaQueryWrapper
                        .ne(User::getPhoneNumber, phoneNumber)
                        .ne(User::getUserId, userId))
                .and(userLambdaQueryWrapper -> userLambdaQueryWrapper
                        .eq(User::getPhoneNumber, target)
                        .or()
                        .eq(User::getUserId, target)));

//        List<FriendsVO> friendsVOS = BeanUtil.copyToList(users, FriendsVO.class);
        FriendsVO friendsVO = BeanUtil.copyProperties(user, FriendsVO.class);

        if (target.equals(String.valueOf(userId)) || target.equals(phoneNumber)){

            friendsVO.setFriendCode(3);
        }else if (StringUtils.isEmpty(user)){
            friendsVO.setFriendCode(0);
        }else {
            if (ifFriend>0){
                friendsVO.setFriendCode(2);
            }else {
                friendsVO.setFriendCode(1);
            }
        }
        return friendsVO;
    }

    @Override
    public AccountInfoVO getAccountInfo() {
        Integer userId = AccountHolder.getUser().getUserId();
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserId, userId)
                .eq(User::getUserStatus, 0));//获取账号状态正常的数据

        AccountInfoVO accountInfoVO = BeanUtil.copyProperties(user, AccountInfoVO.class);
        accountInfoVO.setPhone(user.getPhoneNumber());
        return accountInfoVO;
    }

    /**
     * 计算年龄（未使用）
     * @param birth
     * @return
     */
    public int getAge(Date birth) {
        Calendar cal = Calendar.getInstance();
        int thisYear = cal.get(Calendar.YEAR);
        int thisMonth = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birth);
        int birthYear = cal.get(Calendar.YEAR);
        int birthMonth = cal.get(Calendar.MONTH);
        int birthdayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        int age = thisYear - birthYear;

        // 未足月
        if (thisMonth <= birthMonth) {
            // 当月
            if (thisMonth == birthMonth) {
                // 未足日
                if (dayOfMonth < birthdayOfMonth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 用户头像上传
     *
     * @param file 头像文件
     * @return 返回图片的url
     */
    @Override
    public String uploadAvatar(MultipartFile file) {
        try {
            // 创建一个User对象
            User user = new User();

            // 从线程上下文中获取当前用户ID并记录日志
            log.info(String.valueOf(AccountHolder.getUser().getUserId()));
            user.setUserId(AccountHolder.getUser().getUserId());

            // 使用MinIO工具上传头像文件到指定的存储桶、路径，设置过期时间和单位（天）
            String avatarPath = minIOUtil.uploadFile(file, BUCKET_NAME, AVATAR_SAVE_PATH, AVATAR_SAVE_DURATION, TimeUnit.DAYS);

            // 设置上传后的头像URL
            user.setUserAvatar(avatarPath);

            // 使用userMapper根据用户ID更新头像URL
            userMapper.updateById(user);

            // 返回上传的头像URL
            return avatarPath;
        } catch (Exception e) {
            // 如果发生异常，打印堆栈跟踪
            e.printStackTrace();
        }

        // 如果发生异常，返回null
        return null;
    }

    /**
     * 上传用户真实照片
     *
     * @param file 照片文件
     * @return 返回图片的url
     */
    @Override
    public String uploadPhoto(MultipartFile file) {
        try {
            // 使用MinIO工具上传文件到指定的存储桶、路径，设置过期时间和单位（天）
            String photoUrl = minIOUtil.uploadFile(file, BUCKET_NAME, PHOTO_SAVE_PATH, PHOTO_SAVE_DURATION, TimeUnit.DAYS);

            // 从线程上下文中获取当前用户ID
            Integer currentUserId = AccountHolder.getUser().getUserId();

            // 使用当前用户ID更新学生照片URL
            studentMessageService.updateStudentPhotoByUserId(currentUserId, photoUrl);

            // 返回上传的照片URL
            return photoUrl;

        } catch (Exception e) {
            // 如果发生异常，打印堆栈跟踪
            e.printStackTrace();
        }

        // 如果发生异常，返回null
        return null;
    }
}