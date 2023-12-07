package com.example.pcproject.Service.impl;

import com.example.pcproject.Repository.IpUserRepository;
import com.example.pcproject.Service.TestRestService;
import com.example.pcproject.models.DTO.GetIp;
import com.example.pcproject.models.entity.IpUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TestRestImpl implements TestRestService {

    private final IpUserRepository ipUserRepository;
    private final ModelMapper modelMapper;

    public TestRestImpl(IpUserRepository ipUserRepository, ModelMapper modelMapper) {
        this.ipUserRepository = ipUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GetIp> getAllIp() {

        List<IpUser> all = ipUserRepository.findAll();
        List<GetIp> allIp = new ArrayList<>();

        for (IpUser ipUser : all) {
            allIp.add(modelMapper.map(ipUser,GetIp.class));
        }

        return allIp;
    }
}
