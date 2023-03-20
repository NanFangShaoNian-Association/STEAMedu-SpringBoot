package com.nfsn.constants;

import lombok.Getter;

/**
 * @ClassName: AccountRole
 * @Author: 团子tz
 * @CreateTime: 2022/11/17 19:25
 * @Description: 账户角色枚举类
 */
@Getter
public enum AccountRole {
    USER(0, "user", "用户"),
    MERCHANT(1, "merchant", "商家"),
    PLATFORM_ADMINISTRATOR(2, "platformAdministrator", "平台管理员");

    /**
     * 编号
     */
    private Integer number;
    /**
     * 名字
     */
    private String name;
    /**
     * 角色描述
     */
    private String description;

    AccountRole(Integer number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
    }
}
