package com.nfsn.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: RawResource
 * @Author: 团子tz
 * @CreateTime: 2022/11/26 11:14
 * @Description: 原资源响应的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RawResource {
}
