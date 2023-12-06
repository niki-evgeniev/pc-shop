package com.example.pcproject.Service;

import com.example.pcproject.models.entity.IpUser;

import java.util.List;
import java.util.Optional;

public interface IpAddressService {

    String getIp();

    List<IpUser> getRegistrationIp();
}
