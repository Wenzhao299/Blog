package com.blog.utils;

import java.io.File;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OSSClientUtil {

    //阿里云OSS地址，这里看根据你的oss选择（选用自己的）
    protected static String endpoint = "";
    //阿里云OSS账号（选用自己的）
    protected static String accessKeyId  = "";
    //阿里云OSS密钥（选用自己的）
    protected static String accessKeySecret  = "";
    //阿里云OSS上的存储块bucket名字（选用自己的）
    protected static String bucketName  = "";

    private OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    //阿里云文件目录
    private static String fileDir = "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";

    //上传图片并返回图片路径
    public String fileUpload(File file, String username) {
        String uuid = UUID.randomUUID().toString();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, username + fileDir + uuid + file.getName().substring(file.getName().lastIndexOf(".")), file);
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        return "https://" + bucketName + "." + endpoint + "/" + username + fileDir + uuid + file.getName().substring(file.getName().lastIndexOf("."));
    }

}
