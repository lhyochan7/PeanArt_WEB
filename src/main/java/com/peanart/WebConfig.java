package com.peanart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String uploadImagesPath;

    @Value("${spring.servlet.multipart.location}")
    String path;

    public WebConfig(@Value("${spring.servlet.multipart.location}") String uploadImagesPath) {
        this.uploadImagesPath = uploadImagesPath;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry, String folderName) {
        registry.addResourceHandler("/imagePath/**")
                .addResourceLocations("file:///" + path + "/" + folderName + "/");
    }

}
