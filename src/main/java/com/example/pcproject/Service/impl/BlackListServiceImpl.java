package com.example.pcproject.Service.impl;

import com.example.pcproject.Repository.IpUserRepository;
import com.example.pcproject.Service.BlackListService;
import com.example.pcproject.models.entity.IpUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlackListServiceImpl implements BlackListService {

    private final IpUserRepository ipUserRepository;

    public BlackListServiceImpl(IpUserRepository ipUserRepository) {
        this.ipUserRepository = ipUserRepository;
    }

    @Override
    public boolean isBannedIp(String ip) {
        Optional<IpUser> visitorIp = ipUserRepository.findByIp(ip);
        if (visitorIp.isPresent()) {
            return visitorIp.get().isBanned();
        }
        return false;
    }
}
