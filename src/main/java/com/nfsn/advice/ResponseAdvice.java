package com.nfsn.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfsn.anno.RawResource;
import com.nfsn.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author snail
 * @create 2023-03-07  0:10
 */
@RestControllerAdvice(basePackages = {"com.nfsn.controller"})
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

        @Autowired
        private ObjectMapper objectMapper;

        /**
         * 是否开启功能 true：是
         */
        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            return true;
        }

        /**
         * 处理返回结果
         */
        @Override
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

            //判断是否存在该注解，存在则无需封装返回的数据
            if (returnType.hasMethodAnnotation(RawResource.class)) {
                log.info("存在RawResource，无需封装");
                return body;
            }

            //处理字符串类型数据，如果Controller返回String的话，SpringBoot是直接返回.
            if (body instanceof String) {
                try {
                    log.info("为字符串数据，无需封装");
                    return objectMapper.writeValueAsString(Result.success(body));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            //防止异常类的重复封装
            if (body instanceof Result) {
                return body;
            }

            //这里处理成功结果，异常结果有全局异常机制处理
            return Result.success(body);
        }
}
