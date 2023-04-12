package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nfsn.model.entity.QuestionOption;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-12  9:14
 */
@Data
public class QuestionOptionVo implements Serializable {
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
     * 题目类别（java、python、数学、语文等）
     */
    @ApiModelProperty("题目类别（java、python、数学、语文等）")
    private String questionCategory;

    /**
     * 图片题目时的图片地址，文字题目为空
     */
    @ApiModelProperty("图片题目时的图片地址，文字题目为空")
    private String questionImageUrl;

    /**
     * 题目解析
     */
    @ApiModelProperty("题目解析")
    private String questionAnalysis;

    /**
     * 题目选项
     */
    @ApiModelProperty("选项")
    List<QuestionOption> questionOptionList;

}
