package com.example.pcproject.Service.impl;

import com.example.pcproject.Service.BlackListService;
import org.springframework.stereotype.Service;

@Service
public class BlackListServiceImpl implements BlackListService {

    @Override
    public boolean isBannedIp(String ip) {

        return false;
    }
}
