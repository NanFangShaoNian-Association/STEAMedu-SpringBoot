package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AdvantageVO
 * @Author: atnibamaitay
 * @CreateTime: 2023-02-10 22:10
 * @Description: 教师传输实体
 */
@Data
@ApiModel("教师传输实体")
public class TeacherInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 头像-默认（固定默认头像）
     */
    @ApiModelProperty("头像-默认（固定默认头像）")
    private String userAvatar;

    /**
     * 用户名-默认值(应该是随机生成)
     */
    @ApiModelProperty("用户名-默认值(应该是随机生成)")
    private String userName;

    /**
     * 老师在这门课中的角色-0表示授课老师，1表示助教
     */
    @ApiModelProperty("老师在这门课中的角色-0表示授课老师，1表示助教")
    private Integer teacherRole;
}
