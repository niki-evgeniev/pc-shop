package com.example.pcproject.Service.impl;

import com.example.pcproject.Service.IpAddressService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

@Service
public class IpAddressServiceImpl implements IpAddressService {
    @Override
    public String getIp() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getDetails() instanceof WebAuthenticationDetails details) {
            String ipAddress = details.getRemoteAddress();
            System.out.println("IP who visit site is : " + ipAddress);
            return ipAddress;
        }
        return null;
    }


}
