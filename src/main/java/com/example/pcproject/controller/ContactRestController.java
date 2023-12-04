package com.example.pcproject.controller;

import com.example.pcproject.models.bindingModels.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactRestController {

    @PostMapping("/api/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {

        System.out.println("HEREEEEEEEEEEEEEEEEEE");
        return ResponseEntity.ok("Message received successfully!");
    }
}
