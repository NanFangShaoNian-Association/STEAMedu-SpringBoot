package com.nfsn.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author snail
 * @create 2023-04-12  12:25
 */
@Data
@ApiModel("选项响应实体")
public class OptionVo {
    /**
     * 题目选项ID
     */
    @ApiModelProperty("题目选项ID")
    private Integer questionOptionId;

    /**
     * 选项序号，可以是A、B、C、D、E等
     */
    @ApiModelProperty(" 选项序号，可以是A、B、C、D、E等")
    private String optionIndex;

    /**
     * 选项内容
     */
    @ApiModelProperty("选项内容")
    private String optionText;

}
