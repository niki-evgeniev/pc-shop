package com.example.pcproject.Service;

import com.example.pcproject.models.DTO.ReCaptchaResponseDTO;

import java.util.Optional;

public interface ReCaptchaService {

    Optional<ReCaptchaResponseDTO> verify(String token);
}
