package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nfsn.model.entity.SignIn;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snail
 * @create 2023-04-13  13:51
 */
@Data
public class SignInToEvenVo implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 签到的唯一标识符
     */
    @ApiModelProperty("签到的唯一标识符")
    private Integer signInId;

    /**
     * 课程名称
     */
    @ApiModelProperty("课程名称")
    private String courseName;

    /**
     * 签到的截止时间
     */
    @ApiModelProperty("事件类型")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date deadline;

    /**
     * 签到状态，0表示未签到，1表示已签到
     */
    @ApiModelProperty("签到状态，0表示未签到，1表示已签到")
    private Integer signInStatus;

    /**
     * 事件类型
     */
    @ApiModelProperty("事件类型")
    private String evenType = "签到";


}
