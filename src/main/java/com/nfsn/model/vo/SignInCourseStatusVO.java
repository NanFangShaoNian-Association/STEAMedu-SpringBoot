package com.nfsn.model.vo;

import com.nfsn.model.entity.SignIn;
import com.nfsn.model.entity.SignInStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-11  14:53
 */
@Data
public class SignInCourseStatusVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty("签到信息")
    private SignIn signIn;

//    @ApiModelProperty("学生签到信息集合")
//    private List<SignInStatus> signInStatusList;

    @ApiModelProperty("总人数")
    private Long allSignInCount;

    @ApiModelProperty("已签到人数")
    private Long isSignInCount;

    /**
     * 事件类型
     */
    private String evenType = "签到";
}
