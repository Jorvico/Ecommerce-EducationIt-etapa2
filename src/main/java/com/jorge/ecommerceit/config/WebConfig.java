package com.jorge.ecommerceit.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imgFolderPath = "file:" + System.getProperty("user.dir") + "/img/";
        registry.addResourceHandler("/img/**")
                .addResourceLocations(imgFolderPath)
                .setCacheControl(CacheControl.noCache());

    }
}
