package com.mc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Value("${img.request-path}")
    private String imgReqPath; // 请求地址
    @Value("${img.local-path}")
    private String imgLocPath; // 本地存放资源目录的绝对路径

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File logoDir = new File(imgLocPath);
        boolean flag = false;
        if (!logoDir.exists())
            flag = logoDir.mkdirs();
        if (flag)
            System.out.println("已成功创建资源img目录：" + imgLocPath);

//        System.out.println("getAbsolutePath = " +logoDir.getAbsolutePath());
//        System.out.println("getPath = "+logoDir.getPath());

        registry.addResourceHandler(imgReqPath)
                .addResourceLocations("file:" + logoDir.getAbsolutePath() + File.separator);
    }
}
