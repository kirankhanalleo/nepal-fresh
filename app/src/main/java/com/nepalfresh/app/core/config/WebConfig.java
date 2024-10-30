package com.nepalfresh.app.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    String baseDir = "files:/home/cosmotech/epalika_files/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/notices/**")
                .addResourceLocations(baseDir + "notices/");
        registry.addResourceHandler("/news/**")
                .addResourceLocations(baseDir + "news/");
        registry.addResourceHandler("/ward-info/**")
                .addResourceLocations(baseDir + "ward-info/");
        registry.addResourceHandler("/tourism-area/**")
                .addResourceLocations(baseDir + "tourism-area/");
        registry.addResourceHandler("/admin/**")
                .addResourceLocations(baseDir + "admin/");
        registry.addResourceHandler("/municipal-info/**")
                .addResourceLocations(baseDir + "municipal-info/");
        registry.addResourceHandler("/laws/**")
                .addResourceLocations(baseDir + "laws/");
        registry.addResourceHandler("/users/**")
                .addResourceLocations(baseDir + "users/");
    }
}
