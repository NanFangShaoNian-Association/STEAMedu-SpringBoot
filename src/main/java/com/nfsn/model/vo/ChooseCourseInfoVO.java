package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: CourseInfoVO
 * @Author: 团子tz
 * @CreateTime: 2023/02/15 13:03
 * @Description: 选课单信息响应实体
 */
@Data
@ApiModel("选课单信息响应实体")
public class ChooseCourseInfoVO {
    /**
     * 购物车id-主键
     */
    @ApiModelProperty("购物车id")
    private Integer cartId;

    /**
     * 课程ID
     */
    @ApiModelProperty("课程ID")
    private Integer courseId;

    /**
     * 课程名
     */
    @ApiModelProperty("课程名")
    private String courseName;

    /**
     * 课程封面
     */
    @ApiModelProperty("课程封面")
    private String courseCover;

    /**
     * 课程开始时间
     */
    @ApiModelProperty("课程开始时间")
    private String courseStartTime;

    /**
     * 课时
     */
    @ApiModelProperty("课时")
    private Integer courseSectionNumber;

    /**
     * 老师ID-主键
     */
    @ApiModelProperty("老师ID")
    private Integer teacherUserId;

    /**
     * 头像-默认（固定默认头像）
     */
    @ApiModelProperty("头像")
    private String userAvatar;

    /**
     * 老师在这门课中的角色-0表示授课老师，1表示助教
     */
    @ApiModelProperty("老师在这门课中的角色-0表示授课老师，1表示助教")
    private Integer teacherRole;

    /**
     * 用户名-默认值(应该是随机生成)
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 上课地点
     */
    @ApiModelProperty("上课地点")
    private String coursePosition;

    //todo:距离，vo里加固定值

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private Integer coursePrice;
}
