package com.example.pcproject.Service;

import com.example.pcproject.models.DTO.RegisterUserDTO;
import com.example.pcproject.models.DTO.ViewProfileInfoDTO;

public interface UserService {

    boolean registerUser(RegisterUserDTO registerUserDTO);

    ViewProfileInfoDTO getProfileDetails(String username);
}
