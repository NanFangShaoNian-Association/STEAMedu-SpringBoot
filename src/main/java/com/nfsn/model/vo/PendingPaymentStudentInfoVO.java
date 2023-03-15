package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("待支付时显示的学生信息传输实体")
public class PendingPaymentStudentInfoVO {

    @ApiModelProperty("学生名")
    private String studentName;

    @ApiModelProperty("年级")
    private String grade;

    @ApiModelProperty("联系方式")
    private String phoneNumber;
}