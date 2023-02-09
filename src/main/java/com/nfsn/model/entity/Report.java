package com.nfsn.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName report
 */
@TableName(value ="report")
@Data
public class Report implements Serializable {
    /**
     * 举报信息ID-主键
     */
    @TableId(value = "report_id")
    private Integer reportId;

    /**
     * 举报用户ID-用户外键
     */
    @TableField(value = "user_report_id")
    private Integer userReportId;

    /**
     * 被举报用户ID-用户外键
     */
    @TableField(value = "user_reported_id")
    private Integer userReportedId;

    /**
     * 举报理由
     */
    @TableField(value = "report_reason")
    private String reportReason;

    /**
     * 举报状态码-0:举报失败;1:举报成功;(默认为0)
     */
    @TableField(value = "report_status")
    private Integer reportStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}