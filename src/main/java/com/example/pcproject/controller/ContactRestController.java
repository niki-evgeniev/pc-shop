package com.example.pcproject.controller;

import com.example.pcproject.Service.ContactUsService;
import com.example.pcproject.models.DTO.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactRestController {

private final ContactUsService contactUsService;

    public ContactRestController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @PostMapping("/api/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()){
            contactUsService.addMessages(messageDTO);
        }

        return ResponseEntity.ok("Message received successfully!");
    }
}


