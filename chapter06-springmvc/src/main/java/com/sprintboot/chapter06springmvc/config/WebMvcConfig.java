package com.sprintboot.chapter06springmvc.config;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 静态资源配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源路径 css,js,img等
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 视图
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        // mapper.xml
        registry.addResourceHandler("/mapper/**").addResourceLocations("classpath:/mapper/");
    }

    /**
     * 视图控制器配置
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 设置默认跳转视图为 index
        registry.addViewController("/").setViewName("/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /**
     * 视图解析器配置  控制controller String返回的页面    视图跳转控制
     */
    public void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);
    }
}