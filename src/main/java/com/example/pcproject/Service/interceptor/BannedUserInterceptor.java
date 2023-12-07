package com.example.pcproject.Service.interceptor;


import com.example.pcproject.Service.BlackListService;
import com.example.pcproject.Service.IpAddressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Locale;
import java.util.Map;

@Component
public class BannedUserInterceptor implements HandlerInterceptor {

    private final BlackListService blackListService;
    private final ThymeleafViewResolver thymeleafViewResolver;
    private final IpAddressService ipAddressService;

    public BannedUserInterceptor(BlackListService blackListService, ThymeleafViewResolver thymeleafViewResolver, IpAddressService ipAddressService) {
        this.blackListService = blackListService;
        this.thymeleafViewResolver = thymeleafViewResolver;
        this.ipAddressService = ipAddressService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = ipAddressService.getIp();

        if (blackListService.isBannedIp(ip)) {
            View blockV = thymeleafViewResolver.resolveViewName("bannedUser", Locale.getDefault());
            if (blockV != null) {
                blockV.render(Map.of(), request, response);
            }
            return false;
        }

        return true;
    }
}
