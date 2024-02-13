package com.example.pcproject.appConfiguration;

import com.example.pcproject.service.interceptor.BannedUserInterceptor;
import com.example.pcproject.service.interceptor.CheckRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final BannedUserInterceptor bannedUserInterceptor;
    private final CheckRequestInterceptor checkRequestInterceptor;

    public InterceptorConfiguration(BannedUserInterceptor bannedUserInterceptor, CheckRequestInterceptor checkRequestInterceptor) {
        this.bannedUserInterceptor = bannedUserInterceptor;
        this.checkRequestInterceptor = checkRequestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(bannedUserInterceptor);
//        registry.addInterceptor(checkRequestInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
