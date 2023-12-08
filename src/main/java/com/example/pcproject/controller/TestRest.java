package com.example.pcproject.controller;

import com.example.pcproject.Service.TestRestService;
import com.example.pcproject.models.DTO.GetIp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TestRest {

    private final TestRestService testRestService;

    public TestRest(TestRestService testRestService) {
        this.testRestService = testRestService;

    }

    @GetMapping("/viewIp")
    public ResponseEntity<List<GetIp>> getIpAddress(){
        List<GetIp> allIp = testRestService.getAllIp();
        return ResponseEntity.ok(allIp);
    }

}
