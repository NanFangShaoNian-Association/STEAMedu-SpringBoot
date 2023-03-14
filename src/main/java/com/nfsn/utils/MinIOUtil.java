package com.nfsn.utils;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: MinIOUtil
 * @Description: MinIO工具类
 * @Author: atnibamaitay
 * @CreateTime: 2023-03-12 16:34
 * @Version: 1.0
 **/
@Slf4j
@Component
public class MinIOUtil {

    @Autowired
    private MinioClient minioClient;

    /**
     * 上传文件到Minio服务
     * @param file 上传的文件
     * @param bucketName 文件存储的桶
     * @param folder 文件存储的目录
     * @param duration 文件的有效期
     * @param timeUnit 有效期单位
     * @return 文件的访问URL
     * @throws Exception 抛出异常
     */
    public String uploadFile(MultipartFile file, String bucketName, String folder, Integer duration, TimeUnit timeUnit) throws Exception {
        // 获取文件扩展名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // 使用UUID生成唯一文件名
        String objectName = folder + UUID.randomUUID().toString() + "." + extension;
        // 获取上传文件的输入流
        InputStream inputStream = file.getInputStream();
        // 获取文件大小
        long size = file.getSize();
        // 获取文件类型
        String contentType = file.getContentType();

        // 上传文件到Minio服务
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName) // 指定上传的桶
                .object(objectName) // 指定上传的对象名称
                .stream(inputStream, size, -1) // 指定上传的文件流及文件大小，-1表示不限制分块大小
                .contentType(contentType) // 指定文件类型
                .build());

        // 获取上传后的文件访问URL，并设置URL的有效期
        String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET) // 指定访问方式为GET
                .bucket(bucketName) // 指定访问的桶
                .object(objectName) // 指定访问的对象名称
                .expiry(duration, timeUnit) // 指定URL的有效期
                .build());

        // 返回上传后的文件访问URL
        return url;
    }


}