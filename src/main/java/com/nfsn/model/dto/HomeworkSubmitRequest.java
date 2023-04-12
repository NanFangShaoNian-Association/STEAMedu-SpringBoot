package com.nfsn.model.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author snail
 * @create 2023-04-12  20:14
 */
@Data
@ApiModel("提交作业请求传输实体")
public class HomeworkSubmitRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 提交作业 ID
     */
    @ApiModelProperty("提交作业 ID")
    private Integer homeworkSubmitId;

    /**
     * 作业 ID，外键关联 homework 表
     */
    @ApiModelProperty("作业 ID")
    private Integer homeworkId;

    /**
     * 作业答案，json存储
     */
    @ApiModelProperty("作业答案，json存储")
    private JSONArray homeworkSubmitAnswer;


    /**
     * 作业状态：0 - 未完成，1 - 已完成，2 - 保存
     */
    @ApiModelProperty("作业状态：0 - 未完成，1 - 已完成(提交)，2 - 保存")
    private Integer homeworkSubmitStatus;

}
