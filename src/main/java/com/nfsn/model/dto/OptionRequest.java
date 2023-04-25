package com.nfsn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author snail
 * @create 2023-04-13  10:12
 */
@Data
@ApiModel("选项内容实体")
public class OptionRequest  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 题目ID
     */
    @ApiModelProperty("题目ID")
    private Integer questionId;

    /**
     * 题目序号
     */
    @ApiModelProperty("题目序号")
    private Integer serialNumber;

    /**
     * 选项序号，可以是A、B、C、D、E等
     */
    @ApiModelProperty(" 选项序号，可以是A、B、C、D、E等")
    private List<String> optionIndexList;
}
