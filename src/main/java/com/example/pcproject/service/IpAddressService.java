package com.example.pcproject.service;

import com.example.pcproject.models.entity.IpUser;

import java.util.List;

public interface IpAddressService {

    String getIp();

    List<IpUser> getRegistrationIp();
}
