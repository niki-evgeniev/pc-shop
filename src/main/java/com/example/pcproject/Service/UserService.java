package com.example.pcproject.Service;

import com.example.pcproject.models.DTO.EditViewProfileDTO;
import com.example.pcproject.models.DTO.RegisterUserDTO;
import com.example.pcproject.models.DTO.ViewProfileInfoDTO;

public interface UserService {

    boolean registerUser(RegisterUserDTO registerUserDTO);

    ViewProfileInfoDTO getUserDetails(String username);

    EditViewProfileDTO getUserEditDetails(Long id);
}
