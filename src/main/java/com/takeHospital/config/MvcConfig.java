package com.takeHospital.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath; //выдергивает из настроек путь где хранятся данные

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/img/**")
                .addResourceLocations("file:/" + uploadPath + "/");
        //сохранение изображения в папке на сервере img
        registry
                .addResourceHandler("/static/**")   //раздает всю иеархию
                .addResourceLocations("classpath:/static/");             //при обращении к ресурсу файлы будут искаться
        //не по всей системе а от корня проекта дерева ресурсов
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/page/for_other/login");
    }
}
