package com.example.pcproject.service;

import com.example.pcproject.models.DTO.ReCaptchaResponseDTO;

import java.util.Optional;

public interface ReCaptchaService {

    Optional<ReCaptchaResponseDTO> verify(String token);
}
