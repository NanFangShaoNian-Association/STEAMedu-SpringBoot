package com.nfsn.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snail
 * @create 2023-04-10  14:20
 */
@Data
@ApiModel("签到信息传输实体")
public class SignInRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 签到所属的课程的唯一标识符
     */
    @ApiModelProperty("签到所属的课程的唯一标识符")
    private Integer courseId;

    /**
     * 签到的截止时间
     */
    @ApiModelProperty("签到的截止时间")
    private Date deadline;


}
