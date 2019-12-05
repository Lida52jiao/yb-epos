package com.yjkj.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfigurer extends WebMvcConfigurerAdapter {
    @Bean
    public SnowflakeIdWorker getSnowflakeIdWorker() {
        return new SnowflakeIdWorker(3,3);
    }
    @Override  
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("test.html");
        registry.addViewController("/order").setViewName("order.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
    
    @Override  
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);  
        configurer.setUseSuffixPatternMatch(false);  
    }
}