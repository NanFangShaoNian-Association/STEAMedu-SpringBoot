package com.nfsn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("应用更新信息")
public class AppUpdateInfoVO {

    @ApiModelProperty(value = "新版本号", example = "1.0.0")
    private String latestVersion;

    @ApiModelProperty(value = "下载链接", example = "https://example.com/app/download/app_v2.0.0.apk")
    private String downloadUrl;
}
