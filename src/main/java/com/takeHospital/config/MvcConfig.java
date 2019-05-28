package com.takeHospital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
* Класс конфигурации для проекта и раздачи файлов
* @author Евгений Попов
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /*
    * Настройка раздачи статики
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
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
