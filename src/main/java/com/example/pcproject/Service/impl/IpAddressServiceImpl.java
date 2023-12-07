package com.example.pcproject.Service.impl;

import com.example.pcproject.Service.IpAddressService;
import com.example.pcproject.models.entity.IpUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IpAddressServiceImpl implements IpAddressService {
    @Override
    public String getIp() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getDetails() instanceof WebAuthenticationDetails details) {

            return details.getRemoteAddress();
        }
        return null;
    }

    @Override
    public List<IpUser> getRegistrationIp() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getDetails() instanceof WebAuthenticationDetails details) {
            String ipAddress = details.getRemoteAddress();
            System.out.println("IP who visit site is : " + ipAddress);
            List<IpUser> listOfIp = new ArrayList<>();
            IpUser ipUser = new IpUser();
            ipUser.setIp(ipAddress);
            listOfIp.add(ipUser);
            return listOfIp;
        }

        return null;
    }


}
