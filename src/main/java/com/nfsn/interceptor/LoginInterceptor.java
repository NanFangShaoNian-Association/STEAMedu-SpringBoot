package com.nfsn.interceptor;

import com.nfsn.anno.NoNeedLogin;
import com.nfsn.common.RedisData;
import com.nfsn.constants.AccountRole;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.UserLoginException;
import com.nfsn.model.entity.User;
import com.nfsn.service.UserService;
import com.nfsn.utils.AccountHolder;
import com.nfsn.utils.CacheClient;
import com.nfsn.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.nfsn.constants.RedisConstants.LOGIN_USER_KEY;
import static com.nfsn.constants.RedisConstants.LOGIN_USER_TTL;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;

    @Resource
    private CacheClient cacheClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("LoginInterceptor已拦截");
        //是不是映射到方法上
        boolean isHandlerMethod = handler instanceof HandlerMethod;
        if (!isHandlerMethod) {
//            System.out.println("不是方法上");
            return true;
        }
        //不需要登录的注解
        boolean isNoNeedLogin = ((HandlerMethod) handler).getMethodAnnotation(NoNeedLogin.class) != null;
        if (isNoNeedLogin) {
            log.info("当前方法:{}无需登录", ((HandlerMethod) handler).getMethod().getName());
            return true;
        }

//        ((HandlerMethod) handler).getMethodAnnotation(BasicErrorController.class)!=null;
        //需要登录验证
        String token = request.getHeader("token");

        if (null == token) {
            throw new UserLoginException(ResultCode.USER_TOKEN_IS_BLANK);
//            System.out.println("无token");
        } else {
            // 此处做token及其身份验证
            return verifyToken(token);
        }
    }

    /**
     * 使用token进行身份验证
     *
     * @param token
     * @return
     */
    private Boolean verifyToken(String token) {
//        if (!"token".equals(token)) {
//            throw new UserLoginException(ResultCode.USER_TOKEN_IS_INVALID);
//        }
//        log.info("成功");
        Claims claims = null;
        try {
            claims = TokenUtil.parseJwt(token);
        } catch (Exception e) {
            log.info("token有误");
            throw new UserLoginException(ResultCode.USER_TOKEN_IS_INVALID);
        }
        //验证逻辑
        if (null != claims) {
            Integer id = Integer.valueOf(claims.getId());
            //获取角色对象
            String role = String.valueOf(claims.get("role"));
            if (AccountRole.USER.getName().equals(role)) {
                User user = userService.getById(id);
                //查询redis中是否存在对应的token
                log.info("查询redis中是否存在对应的token");
                String redisToken = String.valueOf(cacheClient.queryWithPassThrough(LOGIN_USER_KEY, user.getPhoneNumber(), RedisData.class,s -> null, LOGIN_USER_TTL, TimeUnit.SECONDS).getData());
                //查询token是否存在且相等，则将user保存到本地线程
                if (StringUtils.hasLength(redisToken) && redisToken.equals(token) && user != null) {
                    // 将用户放在threadLocal中
                    log.info("用户已放入threadLocal中");
                    AccountHolder.saveUser(user);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //响应结束 threadLocal移除对象
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AccountHolder.removeUser();//移除对象
    }
}