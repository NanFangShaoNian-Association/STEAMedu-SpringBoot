package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.nfsn.common.RedisData;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.UserLoginException;
import com.nfsn.model.dto.LoginRequest;
import com.nfsn.model.entity.User;
import com.nfsn.model.vo.LoginVO;
import com.nfsn.service.UserService;
import com.nfsn.utils.CacheClient;
import com.nfsn.utils.PhoneRegexUtils;
import com.nfsn.utils.RandomUtils;
import com.nfsn.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nfsn.constants.RedisConstants.*;

/**
 * @ClassName: LoginServiceImpl
 * @Author: 团子tz
 * @CreateTime: 2022/11/16 21:13
 * @Description: 想想此类的作用，写在这里吧。
 */
@Service("loginService")
@Slf4j
public class LoginServiceImpl {

    @Resource
    private CacheClient cacheClient;

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

//    @Resource(name = "aliyunCode")
//    private SMSUtils aliyunCode;

    /**
     * 根据手机号获取验证码
     * @param phone 手机号
     */
    public void getVerifyCode(String phone) {
        //校验手机号的合理性
        if (!PhoneRegexUtils.isPhoneLegal(phone)) {
            //手机号不是中国【包括香港】的
            throw new UserLoginException(ResultCode.PARAM_IS_INVALID);
        }

        //生成随机验证码
        String verifyCode = RandomUtils.getRandom(RandomUtils.ALL_NUMBER, 6);
        //固定验证码
//        String verifyCode = "A1b2c3";
        //存储5分钟
        cacheClient.setWithLogicalExpire(LOGIN_CODE_KEY+phone,verifyCode,LOGIN_CODE_TTL, TimeUnit.MINUTES);
        log.info("目标手机号：{}，验证码：{}存储成功",phone,verifyCode);

        try {
            //发送逻辑
//            aliyunCode.sendCode(phone,verifyCode);
            log.info("目标手机号：{}，验证码：{}发送成功",phone,verifyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 用户手机号登录(不需要密码登录)
     * @param loginRequest
     * @return
     */
    public LoginVO userLoginByPhone(LoginRequest loginRequest) {
        //校验验证码
        String verifyCode = (String) cacheClient.queryWithPassThrough(LOGIN_CODE_KEY, loginRequest.getCertificate(), RedisData.class,
                s -> null, LOGIN_CODE_TTL, TimeUnit.MINUTES).getData();

        //校验验证码是否相等
        if (!StringUtils.hasText(loginRequest.getVerifyCode())||
                !StringUtils.hasText(verifyCode)||
                !verifyCode.equalsIgnoreCase(loginRequest.getVerifyCode())){
            throw new UserLoginException(ResultCode.USER_VERIFY_ERROR);
        }

        //todo:校验用户手机号是否存在，获取ip
        User user = userService.getUserByPhone(loginRequest.getCertificate(),"127.0.0.1");

        //生成登录token
        Map<String, Object> map = MapUtil.of("role", "user");
        String token = TokenUtil.createJwtToken(String.valueOf(user.getUserId()), user.getUserName(), map);

        //存入redis
        //存储36000s=10小时
        cacheClient.setWithLogicalExpire(LOGIN_USER_KEY + loginRequest.getCertificate(),token,LOGIN_USER_TTL, TimeUnit.SECONDS);
        log.info("目标手机号：{}，token：{}存储成功",loginRequest.getCertificate(),token);

        //删除缓存中的验证码
        stringRedisTemplate.delete(LOGIN_CODE_KEY + loginRequest.getCertificate());

        //封装token返回
        LoginVO loginVO = BeanUtil.copyProperties(user, LoginVO.class);
        loginVO.setId(user.getUserId());
        loginVO.setToken(token);
        loginVO.setRoleId(0);
        loginVO.setAvatar(user.getUserAvatar());
        loginVO.setPhone(user.getPhoneNumber());
        loginVO.setIntroduction(user.getPhoneNumber());
        return loginVO;
    }

}
