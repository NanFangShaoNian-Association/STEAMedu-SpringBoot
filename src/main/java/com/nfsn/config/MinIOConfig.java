package com.nfsn.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MinIOConfig
 * @Description: MinIO配置类
 * @Author: atnibamaitay
 * @CreateTime: 2023-03-12 15:11
 * @Version: 1.0
 **/
@Configuration
public class MinIOConfig {

    //使用@Value注解，将配置文件中的minio.endpoint属性值注入到endpoint变量中
    @Value("${minio.endpoint}")
    private String endpoint;

    //使用@Value注解，将配置文件中的minio.access-key属性值注入到accessKey变量中
    @Value("${minio.access-key}")
    private String accessKey;

    //使用@Value注解，将配置文件中的minio.secret-key属性值注入到secretKey变量中
    @Value("${minio.secret-key}")
    private String secretKey;

    //创建一个名为minioClient的Bean，返回值为MinioClient类型
    @Bean
    public MinioClient minioClient() {
        //使用MinioClient.builder()方法创建一个MinioClient对象，指定endpoint、accessKey和secretKey
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}