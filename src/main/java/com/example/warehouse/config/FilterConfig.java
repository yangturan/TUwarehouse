package com.example.warehouse.config;

import com.example.warehouse.Filter.DengluFilter;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@Configuration
public class FilterConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Bean
    public FilterRegistrationBean makeFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        DengluFilter dengluFilter=new DengluFilter();
        dengluFilter.setStringRedisTemplate(stringRedisTemplate);
        filterRegistrationBean.setFilter(dengluFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
