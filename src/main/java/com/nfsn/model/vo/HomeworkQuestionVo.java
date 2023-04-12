package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-12  12:37
 */
@Data
@ApiModel("作业选项响应实体")
public class HomeworkQuestionVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 题目ID，主键自增
     */
    @ApiModelProperty("题目ID，主键自增")
    private Integer questionId;

    /**
     * 题目
     */
    @ApiModelProperty("题目")
    private String questionText;

    /**
     * 题目类型（1:单选，2:多选）
     */
    @ApiModelProperty("题目类型（1:单选，2:多选）")
    private Integer questionType;

    /**
     * 图片题目时的图片地址，文字题目为空
     */
    @ApiModelProperty("图片题目时的图片地址，文字题目为空")
    private String questionImageUrl;

    /**
     * 选项列表
     */
    @ApiModelProperty("选项列表")
    private List<OptionVo> optionVoList;

}
