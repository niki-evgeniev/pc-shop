package com.example.pcproject.service;

import com.example.pcproject.models.DTO.profile.EditViewProfileDTO;
import com.example.pcproject.models.DTO.user.RegisterUserDTO;
import com.example.pcproject.models.DTO.profile.ViewProfileInfoDTO;

public interface UserService {

    boolean registerUser(RegisterUserDTO registerUserDTO);

    ViewProfileInfoDTO getUserDetails(String username);

    EditViewProfileDTO getUserEditDetails(Long id);
}
