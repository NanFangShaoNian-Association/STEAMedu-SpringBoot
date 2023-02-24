package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: StudentInfoVO
 * @Author: 团子tz
 * @CreateTime: 2023/02/13 15:02
 * @Description: 学生信息响应实体
 */
@Data
@ApiModel("学生信息响应实体")
public class StudentInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 学生信息ID
     */
    @ApiModelProperty("学生信息ID")
    private Integer studentMessageId;

    /**
     * 学生名
     */
    @ApiModelProperty("学生名")
    private String studentMessageName;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String studentSex;

    /**
     * 学校
     */
    @ApiModelProperty("学校")
    private String school;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private Date birthday;

    /**
     * 联系方式
     */
    @ApiModelProperty("联系方式")
    private String phoneNumber;

    /**
     * 学生照片-真人图片
     */
    @ApiModelProperty("学生照片-真人图片")
    private String studentPhoto;

    /**
     * 年级
     */
    @ApiModelProperty("年级")
    private String grade;
}
