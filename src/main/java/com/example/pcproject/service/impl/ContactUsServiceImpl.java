package com.example.pcproject.service.impl;

import com.example.pcproject.repository.ContactUsRepository;
import com.example.pcproject.service.ContactUsService;
import com.example.pcproject.models.DTO.MessageDTO;
import com.example.pcproject.models.entity.ContactUs;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ContactUsServiceImpl implements ContactUsService {
    private final ModelMapper modelMapper;
    private final ContactUsRepository contactUsRepository;

    public ContactUsServiceImpl(ModelMapper modelMapper, ContactUsRepository contactUsRepository) {
        this.modelMapper = modelMapper;
        this.contactUsRepository = contactUsRepository;
    }

    @Override
    public void addMessages(MessageDTO messageDTO) {

        ContactUs map = modelMapper.map(messageDTO, ContactUs.class);
        map.setMessages(messageDTO.getMessage());
        System.out.println();
    }
}
