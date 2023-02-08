package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName choose
 */
@TableName(value ="choose")
@Data
public class Choose implements Serializable {
    /**
     * 选课订单ID-主键
     */
    @TableId(value = "choose_id", type = IdType.AUTO)
    private Integer choose_id;

    /**
     * 订单编号
     */
    @TableField(value = "choose_number")
    private Integer choose_number;

    /**
     * 用户ID-用户外键
     */
    @TableField(value = "user_id")
    private Integer user_id;

    /**
     * 课程ID-课程外键
     */
    @TableField(value = "course_id")
    private Integer course_id;

    /**
     * 付款时间
     */
    @TableField(value = "pay_time")
    private Date pay_time;

    /**
     * 提交时间
     */
    @TableField(value = "choose_comit_time")
    private Date choose_comit_time;

    /**
     * 付款金额
     */
    @TableField(value = "pay_money")
    private Integer pay_money;

    /**
     * 支付方式-0:微信支付;1:支付宝支付;2银行卡支付;...(默认为0)
     */
    @TableField(value = "pay_way")
    private Integer pay_way;

    /**
     * 机构处理状态-0:未处理;1:已处理;(默认为0)
     */
    @TableField(value = "choose_handle_status")
    private Integer choose_handle_status;

    /**
     * 付款状态-0:未付款;1:已付款;2:已退款;(默认为0)
     */
    @TableField(value = "pay_status")
    private Integer pay_status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}