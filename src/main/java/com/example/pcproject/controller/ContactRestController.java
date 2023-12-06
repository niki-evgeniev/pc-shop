package com.example.pcproject.controller;

import com.example.pcproject.models.DTO.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ContactRestController {

//    @PostMapping("/api/send")
//    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {
//
//        System.out.println("HEREEEEEEEEEEEEEEEEEE");
//        return ResponseEntity.ok("Message received successfully!");
//    }

    @PostMapping("/api/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {

        System.out.println("HEREEEEEEEEEEEEEEEEEE");

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("https://www.abv.bg/"))
                .build();
    }
}


