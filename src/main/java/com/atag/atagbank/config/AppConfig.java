package com.atag.atagbank.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.atag.atagbank.controller")
@EnableJpaRepositories("com.atag.atagbank.repository")
public class AppConfig implements ApplicationContextAware, WebMvcConfigurer {
    private ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/public/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/public/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/public/images/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/public/fonts/");
        registry.addResourceHandler("/scss/**").addResourceLocations("classpath:/public/scss/");
        registry.addResourceHandler("/personal/css/**").addResourceLocations("classpath:/public/personalPage/css/");
        registry.addResourceHandler("/personal/js/**").addResourceLocations("classpath:/public/personalPage/js/");
        registry.addResourceHandler("/personal/images/**").addResourceLocations("classpath:/public/personalPage/images/");
        registry.addResourceHandler("/personal/scss/**").addResourceLocations("classpath:/public/personalPage/scss/");
        registry.addResourceHandler("/login/css/**").addResourceLocations("classpath:/public/login/css/");
        registry.addResourceHandler("/login/js/**").addResourceLocations("classpath:/public/login/js/");
    }
}
