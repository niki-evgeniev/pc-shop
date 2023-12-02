package com.example.pcproject.Service.impl;

import com.example.pcproject.AppConfiguration.ReCaptchaConfiguration;
import com.example.pcproject.Service.ReCaptchaService;
import com.example.pcproject.models.bindingModels.ReCaptchaResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ReCaptchaServiceImpl implements ReCaptchaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReCaptchaServiceImpl.class);
    private final ReCaptchaConfiguration reCaptchaConfiguration;

    private final WebClient webClient;

    public ReCaptchaServiceImpl(ReCaptchaConfiguration reCaptchaConfiguration, WebClient webClient) {
        this.reCaptchaConfiguration = reCaptchaConfiguration;
        this.webClient = webClient;
    }

    @Override
    public Optional<ReCaptchaResponseDTO> verify(String token) {
        ReCaptchaResponseDTO response = webClient.post().uri(this::buildReCaptchaURI)
                .body(BodyInserters.fromFormData("secret", reCaptchaConfiguration.getSecret())
                        .with("response", token))
                .retrieve()
                .bodyToMono(ReCaptchaResponseDTO.class)
                .doOnError(t -> LOGGER.error("Error ReCaptcha", t))
                .onErrorComplete()
                .block();

        return Optional.ofNullable(response);
    }

    private URI buildReCaptchaURI(UriBuilder uriBuilder) {
        return uriBuilder.scheme("https")
                .host("www.google.com")
                .path("/recaptcha/api/siteverify")
                .build();
    }
}
