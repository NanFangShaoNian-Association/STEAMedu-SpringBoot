package com.nfsn.controller.common;

import com.nfsn.anno.NoNeedLogin;
import com.nfsn.constants.ResultCode;
import com.nfsn.exception.UserLoginException;
import com.nfsn.model.dto.LoginRequest;
import com.nfsn.model.vo.LoginVO;
import com.nfsn.service.impl.LoginServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: LoginController
 * @Author: 团子tz
 * @CreateTime: 2022/10/31 08:48
 * @Description: 三端角色登录注册接口
 */
@Api(value = "三端角色登录注册接口",tags = "三端角色登录注册接口")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Resource(name = "loginService")
    private LoginServiceImpl loginService;

    /**
     * 获取验证码
     *
     * @param phone 手机号
     */
    @ApiOperation("获取验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "phone", value = "用户手机号", dataType = "String", required = true)
    })
    @GetMapping("/getVerifyCode/{phone}")
    @NoNeedLogin
    public void getVerifyCode(@PathVariable("phone") String phone) {
        //校验非空
        if (!StringUtils.hasLength(phone)) {
            throw new UserLoginException(ResultCode.PARAM_IS_BLANK);
        }

        //逻辑判断
        loginService.getVerifyCode(phone);
    }

    /**
     * 用户手机号登录
     *
     * @param loginRequest
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    @NoNeedLogin
    public LoginVO userLogin(@RequestBody LoginRequest loginRequest) {
        LoginVO loginVO = new LoginVO();
        switch (loginRequest.getRoleId()) {
            case 0:
                loginVO = userLoginByPhone(loginRequest);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                throw new UserLoginException(ResultCode.PARAM_IS_BLANK);
        }
        return loginVO;
    }

    public LoginVO userLoginByPhone(@RequestBody LoginRequest loginRequest) {
        //校验必要参数非空
        if (!StringUtils.hasText(loginRequest.getCertificate()) &&
                !StringUtils.hasText(loginRequest.getVerifyCode()) &&
                !(loginRequest.getRoleId() == 0)) {
            throw new UserLoginException(ResultCode.PARAM_IS_BLANK);
        }

        return loginService.userLoginByPhone(loginRequest);
    }
}
