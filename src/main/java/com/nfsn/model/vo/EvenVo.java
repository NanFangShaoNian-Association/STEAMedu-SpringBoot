package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-13  14:19
 */
@Data
@ApiModel("事件响应实体")
public class EvenVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 签到集合
     */
    @ApiModelProperty("签到集合")
    List<SignInToEvenVo> signInVoList;

    /**
     * 通知集合
     */
    @ApiModelProperty("通知集合")
    List<NotificationToEvenVO> notificationToEvenVOList;

    /**
     * 作业集合
     */
    @ApiModelProperty("作业集合")
    List<HomeworkToEvenVO> homeworkToEvenVOList;

    /**
     * 考试集合
     */
    @ApiModelProperty("考试集合")
    List<ExamToEvenVo> examToEvenVoList;

}
