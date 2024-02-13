package com.example.pcproject.Service;

import com.example.pcproject.models.DTO.Profile.EditViewProfileDTO;
import com.example.pcproject.models.DTO.user.RegisterUserDTO;
import com.example.pcproject.models.DTO.Profile.ViewProfileInfoDTO;

public interface UserService {

    boolean registerUser(RegisterUserDTO registerUserDTO);

    ViewProfileInfoDTO getUserDetails(String username);

    EditViewProfileDTO getUserEditDetails(Long id);
}
