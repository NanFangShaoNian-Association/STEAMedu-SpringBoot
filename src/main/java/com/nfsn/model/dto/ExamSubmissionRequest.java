package com.nfsn.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-14  16:38
 */
@Data
@ApiModel("考试提交请求传输实体")
public class ExamSubmissionRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考试提交id
     */
    @ApiModelProperty("考试提交id")
    private Integer submissionId;

    /**
     * 选项内容集合
     */
    @ApiModelProperty("选项内容集合")
    List<OptionRequest> optionRequestList;

}
